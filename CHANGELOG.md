# toolarium-dependency-check-util

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

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
