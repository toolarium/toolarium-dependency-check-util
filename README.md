[![License](https://img.shields.io/github/license/toolarium/toolarium-dependency-check-util)](https://github.com/toolarium/toolarium-dependency-check-util/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.toolarium/toolarium-dependency-check-util/1.1.0)](https://search.maven.org/artifact/com.github.toolarium/toolarium-dependency-check-util/1.1.0/jar)
[![javadoc](https://javadoc.io/badge2/com.github.toolarium/toolarium-dependency-check-util/javadoc.svg)](https://javadoc.io/doc/com.github.toolarium/toolarium-dependency-check-util)

# toolarium-dependency-check-util

The [OWASP Dependency-Check](https://owasp.org/www-project-dependency-check/) is a great tool but on command line it don't illustrate important information.
This java library takes a OWASP Dependency-Check json report and summarize the result. It supports CVSS v2, v3 and v4 scoring.

The library is integrated and used by the [common gradle build](https://github.com/toolarium/common-gradle-build).


## Command-Line Usage

```bash
dependency-check-util [options] <file>
```

| Option | Description |
|---|---|
| `<file>` | The dependency-check report JSON file. |
| `--no-header` | Suppress the header information. |
| `--direct` | Show only direct dependencies. |
| `--filter <ALL\|DIRECT>` | Set the dependency filter (default: ALL). |
| `--configuration <config1,config2>` | Filter by configuration (e.g. runtimeClasspath). |
| `--simplify` | Output simplified JSON. |

### Example Output

```
Dependency-check report: dependency-check-report.json
Project: root project 'my-project', Version: 1.0.0-SNAPSHOT
 > modelGenerator:
   + org.apache.logging.log4j:log4j-api:2.24.3 (confidence:HIGH)
     - CVE       CVE-2026-34479, CVE-2026-34477
     - Severity  MEDIUM, 6.9, NVD -> fixed by 2.25.4
     - Included  poi-ooxml:5.5.1, poi:5.5.1
     - Reason    The Log4j1XmlLayout from the Apache Log4j 1-to-Log4j 2 bridge fails to escape characters
                 forbidden by the XML 1.0 standard...
     - Vul.-Ref  https://nvd.nist.gov/vuln/search#...
```


## Built With

* [cb](https://github.com/toolarium/common-build) - The toolarium common build

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/toolarium/toolarium-dependency-check-util/tags).


### Gradle:

```groovy
dependencies {
    implementation "com.github.toolarium:toolarium-dependency-check-util:1.1.0"
}
```

### Maven:

```xml
<dependency>
    <groupId>com.github.toolarium</groupId>
    <artifactId>toolarium-dependency-check-util</artifactId>
    <version>1.1.0</version>
</dependency>
```
