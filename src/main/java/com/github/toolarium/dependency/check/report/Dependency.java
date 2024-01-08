/*
 * Dependency.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Defines the dependency 
 * 
 * @author patrick
 */
public class Dependency {
    private String fileName;
    private DependencyArtifact dependencyArtifact;
    private String packageDescription;
    private String packageLicence;
    private String confidence;
    private String url;
    private List<String> projectReferenceList;
    private List<DependencyArtifact> includedByReferenceList;
    private List<Vulnerability> vulnerabilityList;

    
    /**
     * Constructor for Vulnerability
     *
     */
    public Dependency() {
        projectReferenceList = new ArrayList<String>();
    }
    
    
    /**
     * Get the file name
     *
     * @return the file name
     */
    public String getFilename() {
        return fileName;
    }

    
    /**
     * Set the file name
     *
     * @param fileName the file name
     */
    public void setFilename(String fileName) {
        this.fileName = fileName;
    }

    
    /**
     * Get the dependency artifact of the package
     *
     * @return the dependency artifact of the package
     */
    public DependencyArtifact getDependencyArtifact() {
        return dependencyArtifact;
    }


    /**
     * Set the dependency artifact of the package
     *
     * @param dependencyArtifact the dependency artifact of the package
     */
    public void setDependencyArtifact(DependencyArtifact dependencyArtifact) {
        this.dependencyArtifact = dependencyArtifact;
    }
    
    
    /**
     * Get the package description
     *
     * @return the package description
     */
    public String getPackageDescription() {
        return packageDescription;
    }

    
    /**
     * Get the package description
     *
     * @param packageDescription the package description
     */
    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    
    /**
     * Get the package license
     *
     * @return the package license
     */
    public String getPackageLicence() {
        return packageLicence;
    }

    
    /**
     * Get the package license
     *
     * @param packageLicence the package license
     */
    public void setPackageLicence(String packageLicence) {
        this.packageLicence = packageLicence;
    }
    
    
    /**
     * Get the project reference list
     *
     * @return the project reference list
     */
    public List<String> getProjectReferenceList() {
        return projectReferenceList;
    }

    
    /**
     * Get the confidence
     *
     * @return the confidence
     */
    public String getConfidence() {
        return confidence;
    }
    
    
    /**
     * Set the confidence
     *
     * @param confidence the confidence
     */
    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }


    /**
     * Get the url
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    
    
    /**
     * Set the url
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    
    /**
     * Set the project reference list
     *
     * @param projectReferenceList the project reference list
     */
    public void setProjectReferenceList(List<String> projectReferenceList) {
        this.projectReferenceList = projectReferenceList;
    }

    
    /**
     * Get the included by reference list
     *
     * @return the included by reference list
     */
    public List<DependencyArtifact> getIncludedByReferenceList() {
        return includedByReferenceList;
    }

    
    /**
     * Set the included by reference list
     *
     * @param includedByReferenceList the included by reference list
     */
    public void setIncludedByReferenceList(List<DependencyArtifact> includedByReferenceList) {
        this.includedByReferenceList = includedByReferenceList;
    }

    
    /**
     * Add a project reference
     *
     * @param projectReference the project reference to add
     */
    public void addProjectReferenceList(String projectReference) {
        if (!this.projectReferenceList.contains(projectReference)) {
            this.projectReferenceList.add(projectReference);
        }
    }
    

    /**
     * Get the most critical vulnerability
     *
     * @return the most critical vulnerability
     */
    public Vulnerability getMostCrititcalVulnerability() {
        if (vulnerabilityList != null && !vulnerabilityList.isEmpty()) {
            return vulnerabilityList.get(0);
        }
        
        return null;
    }

    
    /**
     * Get the vulnerability list
     *
     * @return the vulnerability list
     */
    public List<Vulnerability> getVulnerabilityList() {
        return vulnerabilityList;
    }

    
    /**
     * Set the vulnerability list
     *
     * @param vulnerabilityList the vulnerability id list
     */
    public void setVulnerabilityList(List<Vulnerability> vulnerabilityList) {
        this.vulnerabilityList = vulnerabilityList;
    }

    
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(confidence, dependencyArtifact, fileName, includedByReferenceList, packageDescription,
                packageLicence, projectReferenceList, url, vulnerabilityList);
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
        return Objects.equals(confidence, other.confidence)
                && Objects.equals(dependencyArtifact, other.dependencyArtifact)
                && Objects.equals(fileName, other.fileName)
                && Objects.equals(includedByReferenceList, other.includedByReferenceList)
                && Objects.equals(packageDescription, other.packageDescription)
                && Objects.equals(packageLicence, other.packageLicence)
                && Objects.equals(projectReferenceList, other.projectReferenceList) 
                && Objects.equals(vulnerabilityList, other.vulnerabilityList) 
                && Objects.equals(url, other.url);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Dependency [fileName=" + fileName + ", dependencyArtifact=" + dependencyArtifact
                + ", packageDescription=" + packageDescription + ", packageLicence=" + packageLicence + ", confidence="
                + confidence + ", url=" + url + ", projectReferenceList=" + projectReferenceList
                + ", includedByReferenceList=" + includedByReferenceList 
                + ", vulnerabilityList=" + vulnerabilityList
                + "]";
    }
}
