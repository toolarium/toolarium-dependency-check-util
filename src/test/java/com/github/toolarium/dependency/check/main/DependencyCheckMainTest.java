/*
 * DependencyCheckMainTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.main;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.toolarium.dependency.check.Cvssv4ReportTest;
import com.github.toolarium.dependency.check.DependencyCheckUtilTest;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


/**
 * Test the {@link DependencyCheckMain}.
 *
 * @author patrick
 */
public class DependencyCheckMainTest {


    /**
     * Test no arguments shows help.
     */
    @Test
    public void testNoArguments() {
        String output = run();
        assertTrue(output.contains("Could not find the dependency-check report file"));
        assertTrue(output.contains("Usage:"));
    }


    /**
     * Test file not found.
     */
    @Test
    public void testFileNotFound() {
        String output = run("nonexistent.json");
        assertTrue(output.contains("File not found"));
    }


    /**
     * Test report with no vulnerabilities.
     */
    @Test
    public void testReportNoVulnerabilities() {
        String file = Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, DependencyCheckUtilTest.SAMPLE).toString();
        String output = run(file);
        assertTrue(output.contains("No vulnerabilities found"));
    }


    /**
     * Test report with vulnerabilities.
     */
    @Test
    public void testReportWithVulnerabilities() {
        String file = Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, DependencyCheckUtilTest.FULL_REPORT_1_VULNERABLE).toString();
        String output = run(file);
        assertTrue(output.contains("Dependency-check report:"));
        assertTrue(output.contains("CVE-2023-35116"));
    }


    /**
     * Test --no-header suppresses header.
     */
    @Test
    public void testNoHeader() {
        String file = Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, DependencyCheckUtilTest.FULL_REPORT_1_VULNERABLE).toString();
        String output = run("--no-header", file);
        assertFalse(output.contains("Dependency-check report:"));
        assertTrue(output.contains("CVE-2023-35116"));
    }


    /**
     * Test --direct filter.
     */
    @Test
    public void testDirectFilter() {
        String file = Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, DependencyCheckUtilTest.FULL_REPORT_1_VULNERABLE).toString();
        String output = run("--direct", file);
        assertTrue(output.contains("No vulnerabilities found"));
    }


    /**
     * Test --filter argument.
     */
    @Test
    public void testFilterArgument() {
        String file = Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, DependencyCheckUtilTest.FULL_REPORT_MULTIPLE_VULNERABILITIES).toString();
        String output = run("--filter", "ALL", file);
        assertTrue(output.contains("CVE-"));
    }


    /**
     * Test --configuration filter.
     */
    @Test
    public void testConfigurationFilter() {
        String file = Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, DependencyCheckUtilTest.FULL_REPORT_MULTIPLE_VULNERABILITIES).toString();
        String output = run("--configuration", "annotationProcessor", file);
        assertTrue(output.contains("annotationProcessor"));
    }


    /**
     * Test --simplify outputs JSON.
     */
    @Test
    public void testSimplify() {
        String file = Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, DependencyCheckUtilTest.FULL_REPORT_1_VULNERABLE).toString();
        String output = run("--simplify", file);
        assertTrue(output.contains("reportSchema"));
        assertTrue(output.contains("dependencies"));
    }


    /**
     * Test CVSSv4 report via main.
     */
    @Test
    public void testCvssv4Report() {
        String file = Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, Cvssv4ReportTest.FULL_REPORT_CVSSV4).toString();
        String output = run(file);
        assertTrue(output.contains("CVE-2026-34479"));
        assertTrue(output.contains("2.25.4"));
    }


    /**
     * Test multiple arguments combined.
     */
    @Test
    public void testCombinedArguments() {
        String file = Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, Cvssv4ReportTest.FULL_REPORT_CVSSV4).toString();
        String output = run("--no-header", "--configuration", "modelGenerator", file);
        assertFalse(output.contains("Dependency-check report:"));
        assertTrue(output.contains("modelGenerator"));
    }


    /**
     * Run the main class with captured output.
     *
     * @param args the arguments
     * @return the captured output
     */
    private String run(String... args) {
        TestDependencyCheckMain app = new TestDependencyCheckMain();
        app.parseArguments(args);
        assertDoesNotThrow(() -> app.execute());
        return app.getOutput();
    }


    /**
     * Testable subclass that captures output.
     */
    static class TestDependencyCheckMain extends DependencyCheckMain {
        private final List<String> lines = new ArrayList<>();


        /**
         * @see com.github.toolarium.dependency.check.main.DependencyCheckMain#logToConsole(java.lang.String)
         */
        @Override
        protected void logToConsole(String message) {
            lines.add(message);
        }


        /**
         * Get the captured output.
         *
         * @return the output
         */
        public String getOutput() {
            return String.join("\n", lines);
        }
    }
}
