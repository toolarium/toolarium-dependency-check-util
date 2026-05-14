/*
 * Cvssv4ReportTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.toolarium.common.util.TextUtil;
import com.github.toolarium.dependency.check.formatter.IDependencyCheckFormatter.DependencyFilter;
import com.github.toolarium.dependency.check.formatter.impl.VulnerabilityReportDependecyCheckFormatter;
import com.github.toolarium.dependency.check.model.DependecyCheckResult;
import com.github.toolarium.dependency.check.model.Dependency;
import com.github.toolarium.dependency.check.report.Vulnerability.ScoreType;
import com.github.toolarium.dependency.check.report.VulnerabilityReport;
import com.github.toolarium.dependency.check.report.format.VulnerabilityReportFormatterFactory;
import com.github.toolarium.dependency.check.report.format.impl.StringVulnerabilityReportFormatter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Test CVSSv4 report formatting.
 *
 * @author patrick
 */
public class Cvssv4ReportTest {
    /** the json file */
    public static final String FULL_REPORT_CVSSV4 = "dependency-check-report-cvssv4.json";

    private static final Logger LOG = LoggerFactory.getLogger(Cvssv4ReportTest.class);


    /**
     * Test reading a report containing CVSSv4 vulnerabilities.
     *
     * @throws IOException In case of a file error
     */
    @Test
    public void readCvssv4Report() throws IOException {
        DependecyCheckResult result = DependencyCheckUtil.getInstance().readFile(
                Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, FULL_REPORT_CVSSV4).toFile());
        assertNotNull(result);
        assertNotNull(result.getProjectInfo());
        assertNotNull(result.getDependencies());
        assertFalse(result.getDependencies().isEmpty());

        // find the log4j dependency with cvssv4
        Dependency log4jDep = null;
        for (Dependency d : result.getDependencies()) {
            if (d.getVulnerabilities() != null && !d.getVulnerabilities().isEmpty()) {
                for (com.github.toolarium.dependency.check.model.vulnerability.Vulnerability v : d.getVulnerabilities()) {
                    if (v.getCvssv4() != null) {
                        log4jDep = d;
                        break;
                    }
                }
            }
            if (log4jDep != null) {
                break;
            }
        }

        assertNotNull(log4jDep, "Should find a dependency with CVSSv4 data");
        assertNotNull(log4jDep.getVulnerabilities());

        // verify cvssv4 fields are parsed
        com.github.toolarium.dependency.check.model.vulnerability.Vulnerability vuln = log4jDep.getVulnerabilities().get(0);
        assertNotNull(vuln.getCvssv4());
        assertNotNull(vuln.getCvssv4().getBaseScore());
        assertNotNull(vuln.getCvssv4().getBaseSeverity());
        assertNotNull(vuln.getCvssv4().getAttackVector());
        assertNotNull(vuln.getCvssv4().getVersion());
        assertEquals("4.0", vuln.getCvssv4().getVersion());

        // verify cvssv3 is also present
        assertNotNull(vuln.getCvssv3());
        assertNotNull(vuln.getCvssv3().getBaseScore());
    }


    /**
     * Test that CVSSv4 score is preferred over CVSSv3 in vulnerability report.
     *
     * @throws IOException In case of a file error
     */
    @Test
    public void verifyCvssv4ScorePreference() throws IOException {
        DependecyCheckResult result = DependencyCheckUtil.getInstance().readFile(
                Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, FULL_REPORT_CVSSV4).toFile());
        assertNotNull(result);

        VulnerabilityReport vulnerabilityReport = new VulnerabilityReportDependecyCheckFormatter().format(result, DependencyFilter.ALL);
        assertNotNull(vulnerabilityReport);

        // the most critical vulnerability should use CVSSv4 score type
        assertNotNull(vulnerabilityReport.getMostCrititcalVulnerability());
        assertEquals(ScoreType.CVSS_V4, vulnerabilityReport.getMostCrititcalVulnerability().getScoreType());

        // CVSSv4 score for CVE-2026-34479 is 6.9 (vs CVSSv3 7.5)
        assertEquals(6.9d, vulnerabilityReport.getMostCrititcalVulnerability().getScore());
        assertEquals("MEDIUM", vulnerabilityReport.getMostCrititcalVulnerability().getSeverity());
        assertEquals("NVD", vulnerabilityReport.getMostCrititcalVulnerability().getSource());
        assertEquals("2.25.4", vulnerabilityReport.getMostCrititcalVulnerability().getEndExcludingVersion());
    }


    /**
     * Test formatting a report containing CVSSv4 vulnerabilities.
     *
     * @throws IOException In case of a file error
     */
    @Test
    public void formatCvssv4Report() throws IOException {
        DependecyCheckResult result = DependencyCheckUtil.getInstance().readFile(
                Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, FULL_REPORT_CVSSV4).toFile());
        assertNotNull(result);

        StringVulnerabilityReportFormatter f = VulnerabilityReportFormatterFactory.getInstance().getStringFormatter();

        List<String> formatted = DependencyCheckUtil.getInstance().formatVulneabilityReport(result, f, DependencyFilter.ALL);
        assertFalse(formatted.isEmpty());

        // verify output contains the CVE and fix version
        String output = String.join("", formatted);
        assertTrue(output.contains("CVE-2026-34479"));
        assertTrue(output.contains("CVE-2026-34477"));
        assertTrue(output.contains("2.25.4"));
        assertTrue(output.contains("MEDIUM"));

        for (String s : formatted) {
            LOG.debug(TextUtil.NL + s);
        }
    }


    /**
     * Test simplify with CVSSv4 report.
     *
     * @throws IOException In case of a file error
     */
    @Test
    public void simplifyCvssv4Report() throws IOException {
        DependecyCheckResult result = DependencyCheckUtil.getInstance().readFile(
                Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, FULL_REPORT_CVSSV4).toFile());
        assertNotNull(result);

        DependecyCheckResult simplified = DependencyCheckUtil.getInstance().simplify(result);
        assertNotNull(simplified);
        assertNotNull(simplified.getDependencies());
        assertFalse(simplified.getDependencies().isEmpty());

        // simplified should strip file paths, hashes, evidence
        for (Dependency d : simplified.getDependencies()) {
            assertEquals(null, d.getFilePath());
            assertEquals(null, d.getMd5());
            assertEquals(null, d.getSha1());
            assertEquals(null, d.getSha256());
            assertEquals(null, d.getEvidenceCollected());
        }
    }


    /**
     * Test filter with CVSSv4 report.
     *
     * @throws IOException In case of a file error
     */
    @Test
    public void filterCvssv4Report() throws IOException {
        DependecyCheckResult result = DependencyCheckUtil.getInstance().readFile(
                Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, FULL_REPORT_CVSSV4).toFile());
        assertNotNull(result);

        DependecyCheckResult filtered = DependencyCheckUtil.getInstance().filter(
                DependencyCheckUtil.getInstance().simplify(result));
        assertNotNull(filtered);
        assertNotNull(filtered.getDependencies());

        // filtered should only contain dependencies with vulnerabilities
        for (Dependency d : filtered.getDependencies()) {
            assertNotNull(d.getVulnerabilities());
            assertFalse(d.getVulnerabilities().isEmpty());
            assertNotNull(d.getVulnerabilityIds());
            assertFalse(d.getVulnerabilityIds().isEmpty());
        }
    }
}
