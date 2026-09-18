# toolarium-dependency-check-util

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [ 1.1.1 ] - 2026-09-18
### Added
- Whitelist suppression via `DependencyCheckFilter.isWhitelisted()`, applied in `DependencyCheckUtil.filter(result, filter)`.
- 71 tests: 4 new test classes (`JSONUtilTest`, `DependencyTest`, `DependencyCheckFilterTest`, `VulnerabilityReportEdgeCasesTest`) and extensions to 4 existing ones.

### Changed
- Updated Jackson 2.22.2, toolarium-common 1.1.0, toolarium-ansi 0.9.1.
- Thread safety: `ObjectMapper` eager-init, explicit `UTF-8` charset, `HashMap` for local-only dependency map.
- `Dependency.setProjectReferenceList()` makes a defensive copy to prevent `UnsupportedOperationException` and NPE.

### Fixed
- Resource leaks in `readFile`/`writeFile` — try-with-resources.
- Exception cause chaining in `JSONUtil.read`/`write`.
- NPE guards for null `projectInfo`, null filter args, null/empty vulnerability lists.
- Bounds check for malformed project references (missing colon) in formatter.
- Graceful error message for invalid `--filter` values on the command line.
- O(1) deduplication in `Dependency.addProjectReferenceList` via transient `HashSet`.

## [ 1.1.0 ] - 2026-05-14
### Added
- Added CVSSv4 model and scoring support.
- Added DependencyCheckMain command-line entry point.
- Added unit tests for DependencyCheckMain and CVSSv4 report processing.

### Changed
- Updated Jackson libraries from 2.17.1 to 2.21.3.

## [ 1.0.2 ] - 2024-06-29
### Changed
- Added exploitabilityScore, impactScore and scoreType on Vulnerability.
- Added cvssv2 score.
- Set default colors in AnsiStringVulnerabilityReportFormatter.

### Fixed
- Propagating project information in case of filtering result.
- Detecting volunerability in result by configuration.

## [ 1.0.1 ] - 2024-06-29
### Changed
- Updated libraries.

## [ 1.0.0 ] - 2024-01-08
### Added
- The support of getMostCrititcalVulnerability on Dependency and VulnerabilityReport.

### Changed
- Enhanced configuration possibility for VulnerabilityReport.

## [ 0.9.0 ] - 2024-01-07
### Changed
- Refactoring reporting.

## [ 0.8.0 ] - 2024-01-02
### Added
- Setup initial version.
