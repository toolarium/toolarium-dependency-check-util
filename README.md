[![License](https://img.shields.io/github/license/toolarium/toolarium-dependency-check-util)](https://github.com/toolarium/toolarium-dependency-check-util/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.toolarium/toolarium-dependency-check-util/1.1.0)](https://search.maven.org/artifact/com.github.toolarium/toolarium-dependency-check-util/1.1.0/jar)
[![javadoc](https://javadoc.io/badge2/com.github.toolarium/toolarium-dependency-check-util/javadoc.svg)](https://javadoc.io/doc/com.github.toolarium/toolarium-dependency-check-util)

# toolarium-dependency-check-util

The [OWASP Dependency-Check](https://owasp.org/www-project-dependency-check/) is a great tool but on command line it does not illustrate important information concisely.
This Java library reads an OWASP Dependency-Check JSON report, filters and simplifies it, and renders a compact vulnerability summary. It supports CVSS v2, v3, and v4 scoring (v4 preferred over v3 over v2).

The library is integrated and used by the [common gradle build](https://github.com/toolarium/common-gradle-build).


## Dependency

### Gradle

```groovy
dependencies {
    implementation "com.github.toolarium:toolarium-dependency-check-util:1.1.1"
}
```

### Maven

```xml
<dependency>
    <groupId>com.github.toolarium</groupId>
    <artifactId>toolarium-dependency-check-util</artifactId>
    <version>1.1.1</version>
</dependency>
```


## Command-Line Usage

```
dependency-check-util [options] <file>
```

| Option | Description |
|---|---|
| `<file>` | Path to the dependency-check JSON report file. |
| `--no-header` | Suppress the header (project name, version, date). |
| `--direct` | Show only direct dependencies (equivalent to `--filter DIRECT`). |
| `--filter <ALL\|DIRECT>` | Dependency scope filter. `ALL` shows all transitive dependencies (default). `DIRECT` shows only dependencies directly included by the project. |
| `--configuration <cfg>` | Comma-separated Gradle configuration names to include (e.g. `runtimeClasspath,api`). Omit to show all configurations. |
| `--simplify` | Output a simplified JSON report instead of the formatted vulnerability summary. |

### Example Output

```
Dependency-check report: dependency-check-report.json
Project: root project 'my-project', Version: 1.0.0-SNAPSHOT
 > modelGenerator:
   + org.apache.logging.log4j:log4j-api:2.24.3 (confidence:HIGH)
     - CVE       CVE-2026-34479, CVE-2026-34477
     - Severity  MEDIUM, 6.9, NVD -> fixed by 2.25.4
     - Included  poi-ooxml:5.5.1, poi:5.5.1
     - Reason    The Log4j1XmlLayout from the Apache Log4j 1-to-Log4j 2 bridge fails to escape
                 characters forbidden by the XML 1.0 standard...
     - Vul.-Ref  https://nvd.nist.gov/vuln/search#...
```


## Java API

All functionality is available through the thread-safe singleton `DependencyCheckUtil.getInstance()`.

### Reading and Writing Reports

```java
// Read a JSON report from disk
DependecyCheckResult result = DependencyCheckUtil.getInstance().readFile("dependency-check-report.json");

// Write a (simplified) result back to disk
DependencyCheckUtil.getInstance().writeFile("simplified-report.json", result);
```

### Simplifying and Filtering

`simplify()` strips noise fields (file paths, hashes, evidence) to produce a leaner model.
`filter()` keeps only dependencies that have project references, non-LOW-confidence vulnerability IDs, and actual CVEs.

```java
DependecyCheckResult simplified = DependencyCheckUtil.getInstance().simplify(result);
DependecyCheckResult filtered   = DependencyCheckUtil.getInstance().filter(simplified);
```

#### Whitelist (suppression)

Use `DependencyCheckFilter` to suppress known/accepted vulnerabilities by artifact coordinate.
Both `groupId:name:version` and `pkg:maven/groupId/name@version` formats are accepted.

```java
DependencyCheckFilter filter = new DependencyCheckFilter();
filter.addWhitelist("com.fasterxml.jackson.core:jackson-databind:2.15.3");
filter.addWhitelist("pkg:maven/org.apache.logging.log4j/log4j-core@2.20.0");

DependecyCheckResult filtered = DependencyCheckUtil.getInstance().filter(result, filter);
```

### Converting to a VulnerabilityReport

`toVulnerabilityReport()` converts the raw model into a structured `VulnerabilityReport` keyed by Gradle configuration name.
The `DependencyFilter` controls whether all transitive dependencies (`ALL`) or only direct ones (`DIRECT`) are included.

```java
VulnerabilityReport report = DependencyCheckUtil.getInstance()
        .toVulnerabilityReport(result, DependencyFilter.ALL);

// Inspect results
System.out.println(report.getProjectArtifact().toArtifactId()); // com.example:my-app:1.0.0
System.out.println(report.getReportDate());

Set<String> configs = report.getVulnerabilityConfigurations();  // e.g. [runtimeClasspath, annotationProcessor]
List<Dependency> deps = report.getVulnerabilities("runtimeClasspath");

Vulnerability worst = report.getMostCrititcalVulnerability();   // highest CVSS score across all configs
Vulnerability worstInConfig = report.getMostCrititcalVulnerability("runtimeClasspath");
```

### Formatting

Formatters produce one output item per Gradle configuration that contains vulnerabilities.

#### Plain text

```java
StringVulnerabilityReportFormatter formatter =
        VulnerabilityReportFormatterFactory.getInstance().getStringFormatter();

// Optional tuning
formatter.setEnableConfidence(true);   // show confidence level (default: true)
formatter.setEnableReason(true);       // show CVE description (default: true)
formatter.setEnableVulnerabilityUrl(true); // show NVD URL (default: true)
formatter.setEnableDependencyUrl(false);   // show OSS Index URL (default: false)
formatter.setBreakUrl(false);          // break long URLs onto a new line (default: false)
formatter.setMaxTextLen(120);          // wrap description at N chars (default: 120)

List<String> lines = DependencyCheckUtil.getInstance()
        .formatVulneabilityReport(result, formatter, DependencyFilter.ALL);
lines.forEach(System.out::println);
```

#### ANSI-coloured text (terminal output)

```java
AnsiStringVulnerabilityReportFormatter coloured =
        VulnerabilityReportFormatterFactory.getInstance().getStringFormatter(AnsiColor.ON);

// Customise individual field colours (foreground / background)
coloured.setCveColor("WHITE", "RED");
coloured.setSeverityColor("YELLOW", null);
coloured.setArtifactColor("CYAN", null);

List<String> lines = DependencyCheckUtil.getInstance()
        .formatVulneabilityReport(result, coloured, DependencyFilter.ALL);
```

#### Filter by Gradle configuration

```java
// Only report vulnerabilities in runtime-relevant configurations
List<String> lines = DependencyCheckUtil.getInstance()
        .formatRuntimeRelevantVulneabilityReport(result, formatter, DependencyFilter.ALL);

// Or specify configurations explicitly
List<String> lines = DependencyCheckUtil.getInstance()
        .formatVulneabilityReport(result, formatter, DependencyFilter.DIRECT,
                "runtimeClasspath", "implementation");

// Format from a pre-built VulnerabilityReport
List<String> lines = DependencyCheckUtil.getInstance()
        .formatVulneabilityReport(report, formatter, "runtimeClasspath");
```

The built-in runtime filter covers: `api`, `implementation`, `runtimeOnly`, `runtimeClasspath`.

### Serialising to JSON

```java
String json = DependencyCheckUtil.getInstance().toJsonString(result);
```

### Complete Example

```java
import com.github.toolarium.dependency.check.DependencyCheckUtil;
import com.github.toolarium.dependency.check.formatter.IDependencyCheckFormatter.DependencyFilter;
import com.github.toolarium.dependency.check.model.DependecyCheckResult;
import com.github.toolarium.dependency.check.report.DependencyCheckFilter;
import com.github.toolarium.dependency.check.report.VulnerabilityReport;
import com.github.toolarium.dependency.check.report.format.VulnerabilityReportFormatterFactory;

DependecyCheckResult result = DependencyCheckUtil.getInstance()
        .readFile("dependency-check-report.json");

// Suppress an accepted vulnerability
DependencyCheckFilter whitelist = new DependencyCheckFilter();
whitelist.addWhitelist("com.fasterxml.jackson.core:jackson-databind:2.15.3");

DependecyCheckResult filtered = DependencyCheckUtil.getInstance().filter(result, whitelist);

// Get structured report
VulnerabilityReport report = DependencyCheckUtil.getInstance()
        .toVulnerabilityReport(filtered, DependencyFilter.ALL);

if (report.getMostCrititcalVulnerability() != null) {
    System.out.printf("Highest CVE: %s (score: %.1f)%n",
            report.getMostCrititcalVulnerability().getCve(),
            report.getMostCrititcalVulnerability().getScore());
}

// Print formatted summary for runtime configurations
DependencyCheckUtil.getInstance()
        .formatRuntimeRelevantVulneabilityReport(
                report,
                VulnerabilityReportFormatterFactory.getInstance().getStringFormatter())
        .forEach(System.out::println);
```


## Built With

* [cb](https://github.com/toolarium/common-build) - The toolarium common build

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/toolarium/toolarium-dependency-check-util/tags).
