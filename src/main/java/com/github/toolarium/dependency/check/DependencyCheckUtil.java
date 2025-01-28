/*
 * DependencyCheckUtil.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check;

import com.github.toolarium.dependency.check.formatter.DependencyCheckFormatterFactory;
import com.github.toolarium.dependency.check.model.DependecyCheckResult;
import com.github.toolarium.dependency.check.model.Dependency;
import com.github.toolarium.dependency.check.model.vulnerability.VulnerabilityId;
import com.github.toolarium.dependency.check.report.VulnerabilityReport;
import com.github.toolarium.dependency.check.report.format.IVulnerabilityReportFormatter;
import com.github.toolarium.dependency.check.util.JSONUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Dependency check reader util
 *  
 * @author patrick
 */
public final class DependencyCheckUtil {
    private static final String[] RUNTIME_FILTER = new String[] {"api", "implementation", "runtimeOnly", "runtimeClasspath"}; 

    
    /**
     * Private class, the only instance of the singelton which will be created by accessing the holder class.
     *
     * @author patrick
     */
    private static class HOLDER {
        static final DependencyCheckUtil INSTANCE = new DependencyCheckUtil();
    }


    /**
     * Constructor
     */
    private DependencyCheckUtil() {
        // NOP
    }

    
    /**
     * Get the instance
     *
     * @return the instance
     */
    public static DependencyCheckUtil getInstance() {
        return HOLDER.INSTANCE;
    }

    
    /**
     * Read a dependency check result
     *
     * @param filename the file name
     * @return the result
     * @throws IOException In case of a file read error
     */
    public DependecyCheckResult readFile(String filename) throws IOException {
        return readFile(new File(filename));
    }

    
    /**
     * Read a dependency check result
     *
     * @param file the file
     * @return the result
     * @throws IOException In case of a file read error
     */
    public DependecyCheckResult readFile(File file) throws IOException {
        DependecyCheckResult result = JSONUtil.getInstance().read(DependecyCheckResult.class, new FileInputStream(file));
        return result;
    }

    
    /**
     * Read a dependency check result
     *
     * @param filename the file name
     * @param dependecyCheckResult the dependency check result to write
     * @throws IOException In case of a file read error
     */
    public void writeFile(String filename, DependecyCheckResult dependecyCheckResult) throws IOException {
        writeFile(new File(filename), dependecyCheckResult);
    }


    /**
     * Read a dependency check result
     *
     * @param file the file 
     * @param dependecyCheckResult the dependency check result to write
     * @throws IOException In case of a file read error
     */
    public void writeFile(File file, DependecyCheckResult dependecyCheckResult) throws IOException {
        JSONUtil.getInstance().write(dependecyCheckResult, new FileOutputStream(file));
    }


    /**
     * Simplify a dependecy check result and remove attributes.
     *
     * @param dependecyCheckResult the dependency check result
     * @return the filtered and simplified dependency check result
     */
    public DependecyCheckResult simplify(DependecyCheckResult dependecyCheckResult) {
        DependecyCheckResult simplifiedDependecyCheckResult = new DependecyCheckResult();
        List<Dependency> dependencies = new ArrayList<Dependency>();
        simplifiedDependecyCheckResult.setReportSchema(dependecyCheckResult.getReportSchema());
        simplifiedDependecyCheckResult.setScanInfo(dependecyCheckResult.getScanInfo());
        simplifiedDependecyCheckResult.setProjectInfo(dependecyCheckResult.getProjectInfo());
        simplifiedDependecyCheckResult.setDependencies(dependencies);
        for (Dependency d : dependecyCheckResult.getDependencies()) {
            d.setIsVirtual(null);
            d.setFilePath(null);
            d.setMd5(null);
            d.setSha1(null);
            d.setSha256(null);
            //d.setDescription(null);
            //d.setLicense(null);
            d.setEvidenceCollected(null);
            dependencies.add(d);
        }

        return simplifiedDependecyCheckResult;
    }

    
    /**
     * Filter out not relevant findings
     *
     * @param dependecyCheckResult the dependency check result
     * @return the filtered and simplified dependency check result
     */
    public DependecyCheckResult filter(DependecyCheckResult dependecyCheckResult) {
        DependecyCheckResult simplifiedDependecyCheckResult = new DependecyCheckResult();
        List<Dependency> dependencies = new ArrayList<Dependency>();
        simplifiedDependecyCheckResult.setProjectInfo(dependecyCheckResult.getProjectInfo());
        simplifiedDependecyCheckResult.setDependencies(dependencies);
        for (Dependency d : dependecyCheckResult.getDependencies()) {
            if (d.getProjectReferences() != null && !d.getProjectReferences().isEmpty()) {
                if (d.getVulnerabilityIds() != null && !d.getVulnerabilityIds().isEmpty()) {
                    List<VulnerabilityId> relevantIds = new ArrayList<VulnerabilityId>();
                    for (VulnerabilityId id : d.getVulnerabilityIds()) {
                        if (id.getConfidence() == null || id.getConfidence().isBlank() || "LOW".equalsIgnoreCase(id.getConfidence())) {
                            // ignore result
                        } else {
                            relevantIds.add(id);
                        }
                    }
    
                    if (!relevantIds.isEmpty() && d.getVulnerabilities() != null && !d.getVulnerabilities().isEmpty()) {
                        d.setVulnerabilityIds(relevantIds);
                        dependencies.add(d);
                    }
                }
            }
        }

        return simplifiedDependecyCheckResult;
    }


    /**
     * Format a dependency check result into a vulnerability report 
     *
     * @param dependecyCheckResult the dependency check result to write
     * @return the vulnerability report
     * @throws IOException In case of a file read error
     */
    public VulnerabilityReport toVulnerabilityReport(DependecyCheckResult dependecyCheckResult) {
        return DependencyCheckFormatterFactory.getInstance().getVulnerabilityReportDependecyCheckFormatter().format(dependecyCheckResult);
    }

    
    /**
     * Format a runtime relevant vulnerability report 
     *
     * @param <T> the generic type
     * @param dependecyCheckResult the dependency check result to write
     * @param formatter the formatter
     * @return the string representation of the object
     * @throws IOException In case of a file read error
     */
    public <T> List<T> formatRuntimeRelevantVulneabilityReport(DependecyCheckResult dependecyCheckResult, IVulnerabilityReportFormatter<T> formatter) {
        return formatVulneabilityReport(toVulnerabilityReport(dependecyCheckResult), formatter, RUNTIME_FILTER);
    }    

    
    /**
     * Format a runtime relevant vulnerability report 
     *
     * @param <T> the generic type
     * @param vulnerabilityReport the vulnerability report
     * @param formatter the formatter
     * @return the string representation of the object
     * @throws IOException In case of a file read error
     */
    public <T> List<T> formatRuntimeRelevantVulneabilityReport(VulnerabilityReport vulnerabilityReport, IVulnerabilityReportFormatter<T> formatter) {
        return formatVulneabilityReport(vulnerabilityReport, formatter, RUNTIME_FILTER);
    }    

    
    /**
     * Format a dependency check result 
     *
     * @param <T> the generic type
     * @param dependecyCheckResult the dependency check result to write
     * @param formatter the formatter
     * @return the string representation of the object
     * @throws IOException In case of a file read error
     */
    public <T> List<T> formatVulneabilityReport(DependecyCheckResult dependecyCheckResult, IVulnerabilityReportFormatter<T> formatter) {
        return formatVulneabilityReport(toVulnerabilityReport(dependecyCheckResult), formatter);
    }    

    
    /**
     * Format a vulnerability report 
     *
     * @param <T> the generic type
     * @param vulnerabilityReport the vulnerability report
     * @param formatter the formatter
     * @return the string representation of the object
     * @throws IOException In case of a file read error
     */
    public <T> List<T> formatVulneabilityReport(VulnerabilityReport vulnerabilityReport, IVulnerabilityReportFormatter<T> formatter) {
        return formatVulneabilityReport(vulnerabilityReport, formatter, (String[])null);
    }    

    
    /**
     * Format a dependency check result 
     *
     * @param <T> the generic type
     * @param dependecyCheckResult the dependency check result to write
     * @param formatter the formatter
     * @param configurationFilter the configuration filter or null to format all configurations
     * @return the string representation of the object
     * @throws IOException In case of a file read error
     */
    public <T> List<T> formatVulneabilityReport(DependecyCheckResult dependecyCheckResult, IVulnerabilityReportFormatter<T> formatter, String... configurationFilter) {
        return formatVulneabilityReport(toVulnerabilityReport(dependecyCheckResult), formatter, configurationFilter);
    }    

    
    /**
     * Format a dependency check result into
     *  
     * @param <T> the generic type
     * @param vulnerabilityReport the vulnerability report
     * @param formatter the formatter
     * @param configurationFilter the configuration filter or null to format all configurations
     * @return the string representation of the object
     * @throws IOException In case of a file read error
     */
    public <T> List<T> formatVulneabilityReport(VulnerabilityReport vulnerabilityReport, IVulnerabilityReportFormatter<T> formatter, String... configurationFilter) {
        
        // prepare filer
        List<String> filterList = null;
        if (configurationFilter != null) {
            filterList = Arrays.asList(configurationFilter);
        } else {
            filterList = new ArrayList<String>(vulnerabilityReport.getVulnerabilityConfigurations());
        }
        
        List<T> result = new ArrayList<T>();
        for (String filter : filterList) {
            T content = formatter.format(vulnerabilityReport, filter);
            if (content != null) {
                result.add(content);
            }
        }
        
        return result;
    }    

    
    /**
     * Read a dependency check result
     *
     * @param dependecyCheckResult the dependency check result to write
     * @return the string representation of the object
     * @throws IOException In case of a file read error
     */
    public String toJsonString(DependecyCheckResult dependecyCheckResult) {
        return DependencyCheckFormatterFactory.getInstance().getJsonDependecyCheckFormatter().format(dependecyCheckResult);
    }
}
