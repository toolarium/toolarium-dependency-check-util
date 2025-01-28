[![License](https://img.shields.io/github/license/toolarium/toolarium-dependency-check-util)](https://github.com/toolarium/toolarium-dependency-check-util/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.toolarium/toolarium-dependency-check-util/1.0.2)](https://search.maven.org/artifact/com.github.toolarium/toolarium-dependency-check-util/1.0.2/jar)
[![javadoc](https://javadoc.io/badge2/com.github.toolarium/toolarium-dependency-check-util/javadoc.svg)](https://javadoc.io/doc/com.github.toolarium/toolarium-dependency-check-util)

# toolarium-dependency-check-util

The [OWASP Dependency-Check](https://owasp.org/www-project-dependency-check/) is a great tool but on command line it don't illustrate important information.
This java libraray takes a OWASP Dependency-Check json report and summarize the result.

The library is integrated and used by the [common gradle build](https://github.com/toolarium/common-gradle-build).


## Built With

* [cb](https://github.com/toolarium/common-build) - The toolarium common build

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/toolarium/toolarium-dependency-check-util/tags). 


### Gradle:

```groovy
dependencies {
    implementation "com.github.toolarium:toolarium-dependency-check-util:1.0.2"
}
```

### Maven:

```xml
<dependency>
    <groupId>com.github.toolarium</groupId>
    <artifactId>toolarium-dependency-check-util</artifactId>
    <version>1.0.2</version>
</dependency>
```