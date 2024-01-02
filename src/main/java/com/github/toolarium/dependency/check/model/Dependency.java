/*
 * Dependency.java
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
import com.github.toolarium.dependency.check.model.evidence.EvidenceCollected;
import com.github.toolarium.dependency.check.model.vulnerability.Vulnerability;
import com.github.toolarium.dependency.check.model.vulnerability.VulnerabilityId;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Defines the dependency
 * 
 * @author patrick
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"isVirtual", "fileName", "filePath", "md5", "sha1", "sha256", "description", "license", "projectReferences", "includedBy", 
                    "evidenceCollected", "packages", "vulnerabilityIds", "vulnerabilities" })
public class Dependency {
    @JsonProperty("isVirtual")
    private Boolean isVirtual;
    @JsonProperty("fileName")
    private String fileName;
    @JsonProperty("filePath")
    private String filePath;
    @JsonProperty("md5")
    private String md5;
    @JsonProperty("sha1")
    private String sha1;
    @JsonProperty("sha256")
    private String sha256;
    @JsonProperty("description")
    private String description;
    @JsonProperty("license")
    private String license;
    @JsonProperty("projectReferences")
    private List<String> projectReferences;
    @JsonProperty("includedBy")
    private List<IncludedBy> includedBy;
    @JsonProperty("evidenceCollected")
    private EvidenceCollected evidenceCollected;
    @JsonProperty("packages")
    private List<Package> packages;
    @JsonProperty("vulnerabilityIds")
    private List<VulnerabilityId> vulnerabilityIds;
    @JsonProperty("vulnerabilities")
    private List<Vulnerability> vulnerabilities;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    
    /**
     * Check is virtual
     *
     * @return true if it is virtual
     */
    @JsonProperty("isVirtual")
    public Boolean getIsVirtual() {
        return isVirtual;
    }

    
    /**
     * Set is virtual
     *
     * @param isVirtual true if it is virtual
     */
    @JsonProperty("isVirtual")
    public void setIsVirtual(Boolean isVirtual) {
        this.isVirtual = isVirtual;
    }


    /**
     * Get the file name
     *
     * @return the file name
     */
    @JsonProperty("fileName")
    public String getFileName() {
        return fileName;
    }

    
    /**
     * Set the file name
     *
     * @param fileName the file name
     */
    @JsonProperty("fileName")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    /**
     * Get the file path
     *
     * @return the file path
     */
    @JsonProperty("filePath")
    public String getFilePath() {
        return filePath;
    }

    
    /**
     * Set the file path
     *
     * @param filePath the file path
     */
    @JsonProperty("filePath")
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    
    /**
     * Get the md5 
     *
     * @return the md5
     */
    @JsonProperty("md5")
    public String getMd5() {
        return md5;
    }
    
    
    /**
     * Set the md5 
     *
     * @param md5 the md5
     */
    @JsonProperty("md5")
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    
    /**
     * Get the sha1
     *
     * @return the sha1
     */
    @JsonProperty("sha1")
    public String getSha1() {
        return sha1;
    }

    
    /**
     * Set the sha1
     *
     * @param sha1 the sha1
     */
    @JsonProperty("sha1")
    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    
    /**
     * Get the sha256
     *
     * @return the sha256
     */
    @JsonProperty("sha256")
    public String getSha256() {
        return sha256;
    }

    
    /**
     * Set the sha256
     *
     * @param sha256 the sha256
     */
    @JsonProperty("sha256")
    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    
    /**
     * Get the description
     *
     * @return the description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    
    /**
     * Set the description
     *
     * @param description the description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    
    /**
     * Get the license
     *
     * @return the license
     */
    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    
    /**
     * Set the license
     *
     * @param license the license
     */
    @JsonProperty("license")
    public void setLicense(String license) {
        this.license = license;
    }

    
    /**
     * Get the project reference
     *
     * @return the project reference
     */
    @JsonProperty("projectReferences")
    public List<String> getProjectReferences() {
        return projectReferences;
    }

    
    /**
     * Set the project reference
     *
     * @param projectReferences the project reference
     */
    @JsonProperty("projectReferences")
    public void setProjectReferences(List<String> projectReferences) {
        this.projectReferences = projectReferences;
    }

    
    /**
     * Get the included by
     *
     * @return the included by
     */
    @JsonProperty("includedBy")
    public List<IncludedBy> getIncludedBy() {
        return includedBy;
    }

    
    /**
     * Set the included by
     *
     * @param includedBy the included by
     */
    @JsonProperty("includedBy")
    public void setIncludedBy(List<IncludedBy> includedBy) {
        this.includedBy = includedBy;
    }

    
    /**
     * Get the evidence collected
     *
     * @return the evidence collected
     */
    @JsonProperty("evidenceCollected")
    public EvidenceCollected getEvidenceCollected() {
        return evidenceCollected;
    }

    
    /**
     * Set the evidence collected
     *
     * @param evidenceCollected the evidence collected
     */
    @JsonProperty("evidenceCollected")
    public void setEvidenceCollected(EvidenceCollected evidenceCollected) {
        this.evidenceCollected = evidenceCollected;
    }

    
    /**
     * Get the packages
     *
     * @return the packages
     */
    @JsonProperty("packages")
    public List<Package> getPackages() {
        return packages;
    }

    
    /**
     * Set the packages
     *
     * @param packages the packages
     */
    @JsonProperty("packages")
    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    
    /**
     * Get the vulnerability ids
     *
     * @return the vulnerability ids
     */
    @JsonProperty("vulnerabilityIds")
    public List<VulnerabilityId> getVulnerabilityIds() {
        return vulnerabilityIds;
    }

    
    /**
     * Set the the vulnerability ids
     *
     * @param vulnerabilityIds the vulnerability ids
     */
    @JsonProperty("vulnerabilityIds")
    public void setVulnerabilityIds(List<VulnerabilityId> vulnerabilityIds) {
        this.vulnerabilityIds = vulnerabilityIds;
    }


    /**
     * Get the vulnerabilities
     *
     * @return the vulnerabilities
     */
    @JsonProperty("vulnerabilities")
    public List<Vulnerability> getVulnerabilities() {
        return vulnerabilities;
    }

    
    /**
     * Set the vulnerabilities
     *
     * @param vulnerabilities the vulnerabilities
     */
    @JsonProperty("vulnerabilities")
    public void setVulnerabilities(List<Vulnerability> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
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
        return Objects.hash(additionalProperties, description, evidenceCollected, fileName, filePath, includedBy,
                isVirtual, license, md5, packages, projectReferences, sha1, sha256, vulnerabilityIds, vulnerabilities);
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
        
        Dependency other = (Dependency) obj;
        return Objects.equals(additionalProperties, other.additionalProperties)
                && Objects.equals(description, other.description)
                && Objects.equals(evidenceCollected, other.evidenceCollected)
                && Objects.equals(fileName, other.fileName) && Objects.equals(filePath, other.filePath)
                && Objects.equals(includedBy, other.includedBy) && Objects.equals(isVirtual, other.isVirtual)
                && Objects.equals(license, other.license) && Objects.equals(md5, other.md5)
                && Objects.equals(packages, other.packages)
                && Objects.equals(projectReferences, other.projectReferences) && Objects.equals(sha1, other.sha1)
                && Objects.equals(sha256, other.sha256) && Objects.equals(vulnerabilityIds, other.vulnerabilityIds)
                && Objects.equals(sha256, other.sha256) && Objects.equals(vulnerabilities, other.vulnerabilities);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Dependency [isVirtual=" + isVirtual + ", fileName=" + fileName + ", filePath=" + filePath + ", md5="
                + md5 + ", sha1=" + sha1 + ", sha256=" + sha256 + ", description=" + description + ", license="
                + license + ", projectReferences=" + projectReferences + ", includedBy=" + includedBy
                + ", evidenceCollected=" + evidenceCollected + ", packages=" + packages 
                + ", vulnerabilityIds=" + vulnerabilityIds + ", vulnerabilities=" + vulnerabilities 
                + ", additionalProperties=" + additionalProperties + "]";
    }
}
