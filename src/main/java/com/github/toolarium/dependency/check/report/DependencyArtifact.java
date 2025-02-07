/*
 * DependencyArtifact.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.report;

import com.github.toolarium.common.util.StringUtil;
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


    /**
     * Parse artifact id
     *
     * @param input the id
     * @return the parsed artifact
     */
    public static DependencyArtifact toDependencyArtifact(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }
        
        if (StringUtil.getInstance().countCharacters(input, ':') == 2) {
            String[] split = input.trim().split(":"); // com.github.toolarium:toolarium-enum-configuration:1.1.8
            if (split.length > 2) {
                String groupId = split[0];
                String name = split[1];
                String version = split[2];
                return new DependencyArtifact(groupId, name, version);
            }
        }

        if (StringUtil.getInstance().countCharacters(input, '/') == 2) {
            String[] split = input.trim().split("/"); // pkg:maven/com.github.toolarium/toolarium-enum-configuration@1.1.8
            if (split.length > 2) {
                String groupId = split[1];
                String name = split[2];
                String version = "";
                
                int idx = name.indexOf("@");
                if (idx > 0) {
                    version = name.substring(idx + 1);
                    name = name.substring(0, idx);
                }
                
                return new DependencyArtifact(groupId, name, version);
            }
        }
        
        return null;
    }
}
