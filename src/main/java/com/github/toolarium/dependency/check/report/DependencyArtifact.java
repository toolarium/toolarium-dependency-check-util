/*
 * DependencyArtifact.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.report;

import java.util.Objects;

/**
 * Defines the dependency artifact
 * 
 * @author patrick
 */
public class DependencyArtifact {
    private String name;
    private String groupId;
    private String version;

    
    /**
     * Constructor for DependencyArtefact
     *
     * @param groupId the group id
     * @param name the name
     * @param version the version
     */
    public DependencyArtifact(String groupId, String name, String version) {
        this.name = name;
        this.groupId = groupId;
        this.version = version;
    }
    
    
    /**
     * Get the group id
     *
     * @return the group id
     */
    public String getGroupId() {
        return groupId;
    }

    
    /**
     * Set the group id
     *
     * @param groupId the group id
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    
    /**
     * Get the name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    
    /**
     * Set the g
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Get the version
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    
    /**
     * Set the version
     *
     * @param version the version
     */
    public void setVersion(String version) {
        this.version = version;
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(groupId, name, version);
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
        
        DependencyArtifact other = (DependencyArtifact) obj;
        return Objects.equals(groupId, other.groupId) && Objects.equals(name, other.name)
                && Objects.equals(version, other.version);
    }

    
    /**
     * Format as an artifact id
     *
     * @return as an artifact id
     */
    public String toArtifactId() {
        return groupId + ":" + name + ":" + version;
    }

    
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DependencyArtefact [groupId=" + groupId + ", name=" + name + ", version=" + version + "]";
    }
}
