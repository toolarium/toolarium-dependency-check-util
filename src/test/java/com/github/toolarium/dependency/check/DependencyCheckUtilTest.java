/*
 * DependencyCheckUtilTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.toolarium.ansi.AnsiColor;
import com.github.toolarium.common.util.TextUtil;
import com.github.toolarium.dependency.check.model.DependecyCheckResult;
import com.github.toolarium.dependency.check.report.VulnerabilityReport;
import com.github.toolarium.dependency.check.report.format.VulnerabilityReportFormatterFactory;
import com.github.toolarium.dependency.check.report.format.impl.AnsiStringVulnerabilityReportFormatter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Test the dependency check utility
 *  
 * @author patrick
 */
public class DependencyCheckUtilTest {
    /** Defines the resource */
    public static final String TEST_RESOURCE_PATH = "src/test/resources";
    
    /** the json file */
    public static final String SAMPLE = "sample.json";

    /** the json file */
    public static final String FULL_REPORT_1_VULNERABLE = "dependency-check-report-1v.json";
    
    /** the json file */
    public static final String FULL_REPORT_MULTIPLE_VULNERABILITIES = "dependency-check-report-several.json";

    /** the json file */
    public static final String FULL_REPORT_MULTIPLE_VULNERABILITIES_CVSSV2 = "dependency-check-report-several2.json";

    private static final String RUNTIME_RELEVANT_CONFIGURATION = "api, implementation, runtimeOnly, runtimeClasspath";
    private static final Logger LOG = LoggerFactory.getLogger(DependencyCheckUtilTest.class);

    
    /**
     * Test
     * 
     * @throws IOException In case of a file error
     */
    @Test
    public void readAndSimplifyFile() throws IOException {
        DependecyCheckResult dependecyCheckResult = DependencyCheckUtil.getInstance().readFile(Paths.get(TEST_RESOURCE_PATH, SAMPLE).toFile());
        assertNotNull(dependecyCheckResult);
        
        DependecyCheckResult simplifiedDependecyCheckResult = DependencyCheckUtil.getInstance().filter(DependencyCheckUtil.getInstance().simplify(dependecyCheckResult));
        assertNotNull(simplifiedDependecyCheckResult);
        
        LOG.debug("" + dependecyCheckResult.getReportSchema());
        LOG.debug("" + dependecyCheckResult.getScanInfo());
        LOG.debug("" + dependecyCheckResult.getProjectInfo());
        
        LOG.debug("" + DependencyCheckUtil.getInstance().toJsonString(simplifiedDependecyCheckResult));
        DependencyCheckUtil.getInstance().writeFile("build/" + SAMPLE, simplifiedDependecyCheckResult);
    }


    /**
     * Test
     * 
     * @throws IOException In case of a file error
     */
    @Test
    public void readAndSimplifyFileFullReport() throws IOException {
        DependecyCheckResult dependecyCheckResult = DependencyCheckUtil.getInstance().readFile(Paths.get(TEST_RESOURCE_PATH, FULL_REPORT_1_VULNERABLE).toFile());
        assertNotNull(dependecyCheckResult);
        
        DependecyCheckResult simplifiedDependecyCheckResult = DependencyCheckUtil.getInstance().filter(DependencyCheckUtil.getInstance().simplify(dependecyCheckResult));
        assertNotNull(simplifiedDependecyCheckResult);
        
        LOG.debug("" + DependencyCheckUtil.getInstance().toJsonString(simplifiedDependecyCheckResult));
        DependencyCheckUtil.getInstance().writeFile("build/" + FULL_REPORT_1_VULNERABLE, simplifiedDependecyCheckResult);
    }

    
    /**
     * Test
     * 
     * @throws IOException In case of a file error
     */
    @Test
    public void formatMoreComplexCase() throws IOException {
        DependecyCheckResult dependecyCheckResult = DependencyCheckUtil.getInstance().readFile(Paths.get(TEST_RESOURCE_PATH, FULL_REPORT_MULTIPLE_VULNERABILITIES).toFile());
        assertNotNull(dependecyCheckResult);

        DependecyCheckResult simplifiedDependecyCheckResult = DependencyCheckUtil.getInstance().filter(DependencyCheckUtil.getInstance().simplify(dependecyCheckResult));
        assertNotNull(simplifiedDependecyCheckResult);
        //LOG.debug("" + DependencyCheckUtil.getInstance().toJsonString(simplifiedDependecyCheckResult));
        
        List<String> result = DependencyCheckUtil.getInstance().formatVulneabilityReport(simplifiedDependecyCheckResult, VulnerabilityReportFormatterFactory.getInstance().getStringFormatter(), "annotationProcessor");
        assertFalse(result.isEmpty());
        LOG.debug(TextUtil.NL + result);
        
        logVolunerabilities(simplifiedDependecyCheckResult, RUNTIME_RELEVANT_CONFIGURATION);
    }


    /**
     * Test
     * 
     * @throws IOException In case of a file error
     */
    @Test
    public void formatMoreComplexCase2() throws IOException {
        DependecyCheckResult dependecyCheckResult = DependencyCheckUtil.getInstance().readFile(Paths.get(TEST_RESOURCE_PATH, FULL_REPORT_MULTIPLE_VULNERABILITIES_CVSSV2).toFile());
        assertNotNull(dependecyCheckResult);

        DependecyCheckResult simplifiedDependecyCheckResult = DependencyCheckUtil.getInstance().filter(DependencyCheckUtil.getInstance().simplify(dependecyCheckResult));
        assertNotNull(simplifiedDependecyCheckResult);
        //LOG.debug("" + DependencyCheckUtil.getInstance().toJsonString(simplifiedDependecyCheckResult));

        List<String> result = DependencyCheckUtil.getInstance().formatVulneabilityReport(simplifiedDependecyCheckResult, VulnerabilityReportFormatterFactory.getInstance().getStringFormatter(), "annotationProcessor");
        assertTrue(result.isEmpty());
        result = DependencyCheckUtil.getInstance().formatVulneabilityReport(simplifiedDependecyCheckResult, VulnerabilityReportFormatterFactory.getInstance().getStringFormatter(), "modelGenerator");
        assertFalse(result.isEmpty());
        //LOG.debug(TextUtil.NL + result);

        result = DependencyCheckUtil.getInstance().formatVulneabilityReport(simplifiedDependecyCheckResult, VulnerabilityReportFormatterFactory.getInstance().getStringFormatter(), RUNTIME_RELEVANT_CONFIGURATION);
        assertTrue(result.isEmpty());

        logVolunerabilities(simplifiedDependecyCheckResult, "modelGenerator");
    }

    
    
    /**
     * Test
     * 
     * @throws IOException In case of a file error
     */
    @Test
    public void readAndConvertReport() throws IOException {
        DependecyCheckResult dependecyCheckResult = DependencyCheckUtil.getInstance().readFile(Paths.get(TEST_RESOURCE_PATH, FULL_REPORT_1_VULNERABLE).toFile());
        assertNotNull(dependecyCheckResult);
        
        LOG.debug(TextUtil.NL + DependencyCheckUtil.getInstance().formatVulneabilityReport(dependecyCheckResult, VulnerabilityReportFormatterFactory.getInstance().getStringFormatter(), new String[] {"annotationProcessor"}));
    }


    /**
     * Log vulnerability
     * 
     * @param configuration the configuration
     * @param dependecyCheckResult the result to log
     */
    protected void logVolunerabilities(DependecyCheckResult dependecyCheckResult, String configuration) {
        String[] filter = configuration.split(",");
        for (int i = 0; i < filter.length; i++) {
            filter[i] = filter[i].trim();
        }
       
        AnsiStringVulnerabilityReportFormatter f = VulnerabilityReportFormatterFactory.getInstance().getStringFormatter(AnsiColor.ON);
        VulnerabilityReport vulnerabilityReport = DependencyCheckUtil.getInstance().toVulnerabilityReport(dependecyCheckResult);
        for (String s : DependencyCheckUtil.getInstance().formatVulneabilityReport(vulnerabilityReport, f, filter)) {
            LOG.debug(TextUtil.NL + s);
        }
    }
}
