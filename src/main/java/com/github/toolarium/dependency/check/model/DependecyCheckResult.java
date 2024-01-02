/*
 * DependecyCheckResult.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.toolarium.dependency.check.model.project.ProjectInfo;
import com.github.toolarium.dependency.check.model.scan.ScanInfo;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * Define the dependency check result
 * 
 * @author patrick
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"reportSchema", "scanInfo", "projectInfo", "dependencies" })
public class DependecyCheckResult {
    @JsonProperty("reportSchema")
    private String reportSchema;
    @JsonProperty("scanInfo")
    private ScanInfo scanInfo;
    @JsonProperty("projectInfo")
    private ProjectInfo projectInfo;
    @JsonProperty("dependencies")
    private List<Dependency> dependencies;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    
    /**
     * Constructor for DependecyCheckResult
     */
    public DependecyCheckResult() {
    }
    
    /**
     * Constructor for DependecyCheckResult
     *
     * @param dependecyCheckResult the dependency check result
     */
    public DependecyCheckResult(DependecyCheckResult dependecyCheckResult) {
        this.reportSchema = dependecyCheckResult.getReportSchema();
        this.scanInfo = dependecyCheckResult.getScanInfo();
        this.projectInfo = dependecyCheckResult.getProjectInfo();
        this.dependencies = dependecyCheckResult.getDependencies();
    }
    
    
    /**
     * Get the report schema
     *
     * @return the report schema
     */
    @JsonProperty("reportSchema")
    public String getReportSchema() {
        return reportSchema;
    }

    
    /**
     * Set the report schema
     *
     * @param reportSchema the report schema
     */
    @JsonProperty("reportSchema")
    public void setReportSchema(String reportSchema) {
        this.reportSchema = reportSchema;
    }

    
    /**
     * Get the scan info
     *
     * @return the scan info
     */
    @JsonProperty("scanInfo")
    public ScanInfo getScanInfo() {
        return scanInfo;
    }

    
    /**
     * Set the scan info
     *
     * @param scanInfo the scan info
     */
    @JsonProperty("scanInfo")
    public void setScanInfo(ScanInfo scanInfo) {
        this.scanInfo = scanInfo;
    }

    
    /**
     * Get the project info
     *
     * @return the project info
     */
    @JsonProperty("projectInfo")
    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    
    /**
     * Set the project info
     *
     * @param projectInfo the project info
     */
    @JsonProperty("projectInfo")
    public void setProjectInfo(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    
    /**
     * Get the dependencies
     *
     * @return the dependencies
     */
    @JsonProperty("dependencies")
    public List<Dependency> getDependencies() {
        return dependencies;
    }

    
    /**
     * Set the dependencies
     *
     * @param dependencies the dependencies
     */
    @JsonProperty("dependencies")
    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    
    /**
     * Get the additional properties
     *
     * @return the additional properties
     */
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    
    /**
     * Set the additional properties
     *
     * @param name the name
     * @param value the value
     */
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(additionalProperties, dependencies, projectInfo, reportSchema, scanInfo);
    }


    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
    
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
            
        DependecyCheckResult other = (DependecyCheckResult) obj;
        return Objects.equals(additionalProperties, other.additionalProperties)
                && Objects.equals(dependencies, other.dependencies) && Objects.equals(projectInfo, other.projectInfo)
                && Objects.equals(reportSchema, other.reportSchema) && Objects.equals(scanInfo, other.scanInfo);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DependecyCheckResult [reportSchema=" + reportSchema + ", scanInfo=" + scanInfo + ", projectInfo="
                + projectInfo + ", dependencies=" + dependencies + ", additionalProperties=" + additionalProperties + "]";
    }
}
