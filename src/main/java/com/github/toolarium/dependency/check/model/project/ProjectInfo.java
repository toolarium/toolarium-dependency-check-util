/*
 * ProjectInfo.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.model.project;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


/**
 * Define the product info
 * 
 * @author patrick
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "groupID", "artifactID", "version", "reportDate", "credits" })
public class ProjectInfo {
    @JsonProperty("name")
    private String name;
    @JsonProperty("groupID")
    private String groupID;
    @JsonProperty("artifactID")
    private String artifactID;
    @JsonProperty("version")
    private String version;
    @JsonProperty("reportDate")
    private String reportDate;
    @JsonProperty("credits")
    private Credits credits;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    
    /**
     * Get the name
     *
     * @return the name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    
    /**
     * Set the name
     *
     * @param name the name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    
    /**
     * Get the group id
     *
     * @return the group id
     */
    @JsonProperty("groupID")
    public String getGroupID() {
        return groupID;
    }

    
    /**
     * Set the group id
     *
     * @param groupID the group id
     */
    @JsonProperty("groupID")
    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    
    /**
     * Get the artifact id
     *
     * @return the artifact id
     */
    @JsonProperty("artifactID")
    public String getArtifactID() {
        return artifactID;
    }

    
    /**
     * Set the artifact id
     *
     * @param artifactID the artifact id
     */
    @JsonProperty("artifactID")
    public void setArtifactID(String artifactID) {
        this.artifactID = artifactID;
    }

    
    /**
     * Get the version
     *
     * @return the version
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    
    /**
     * Set the version
     *
     * @param version the version
     */
    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }
    
    
    /**
     * Get the report date
     *
     * @return the report date
     */
    @JsonProperty("reportDate")
    public String getReportDate() {
        return reportDate;
    }

    
    /**
     * Set the report date
     *
     * @param reportDate the report date
     */
    @JsonProperty("reportDate")
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
    
    
    /**
     * Get the credits
     *
     * @return the credits
     */
    @JsonProperty("credits")
    public Credits getCredits() {
        return credits;
    }

    
    /**
     * Set the credits
     *
     * @param credits the credits
     */
    @JsonProperty("credits")
    public void setCredits(Credits credits) {
        this.credits = credits;
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
        return Objects.hash(additionalProperties, artifactID, credits, groupID, name, reportDate, version);
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
        
        ProjectInfo other = (ProjectInfo) obj;
        return Objects.equals(additionalProperties, other.additionalProperties)
                && Objects.equals(artifactID, other.artifactID) && Objects.equals(credits, other.credits)
                && Objects.equals(groupID, other.groupID) && Objects.equals(name, other.name)
                && Objects.equals(reportDate, other.reportDate) && Objects.equals(version, other.version);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProjectInfo [name=" + name + ", groupID=" + groupID + ", artifactID=" + artifactID + ", version="
                + version + ", reportDate=" + reportDate + ", credits=" + credits + ", additionalProperties="
                + additionalProperties + "]";
    }
}
