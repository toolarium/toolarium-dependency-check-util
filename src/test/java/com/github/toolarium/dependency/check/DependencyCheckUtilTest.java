/*
 * DependencyCheckUtilTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.toolarium.common.util.TextUtil;
import com.github.toolarium.dependency.check.model.DependecyCheckResult;
import com.github.toolarium.dependency.check.report.format.VulnerabilityReportFormatterFactory;
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
        
        LOG.debug("" + DependencyCheckUtil.getInstance().toJsonString(simplifiedDependecyCheckResult));

        
        List<String> result = DependencyCheckUtil.getInstance().formatVulneabilityReport(dependecyCheckResult, VulnerabilityReportFormatterFactory.getInstance().getStringFormatter(), "annotationProcessor");
        LOG.debug(TextUtil.NL + result);
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
}
