/*
 * ScanInfo.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.model.scan;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * Defines the scan info
 * 
 * @author patrick
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"engineVersion", "dataSource" })
public class ScanInfo {
    @JsonProperty("engineVersion")
    private String engineVersion;
    @JsonProperty("dataSource")
    private List<DataSource> dataSource;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    
    /**
     * Get the engine version
     *
     * @return the engine version
     */
    @JsonProperty("engineVersion")
    public String getEngineVersion() {
        return engineVersion;
    }

    
    /**
     * Set the engine version
     *
     * @param engineVersion the engine version
     */
    @JsonProperty("engineVersion")
    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    
    /**
     * Get the data source
     *
     * @return the data source
     */
    @JsonProperty("dataSource")
    public List<DataSource> getDataSource() {
        return dataSource;
    }
    
    
    /**
     * Set the data source
     *
     * @param dataSource the data source
     */

    @JsonProperty("dataSource")
    public void setDataSource(List<DataSource> dataSource) {
        this.dataSource = dataSource;
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
        return Objects.hash(additionalProperties, dataSource, engineVersion);
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
        
        ScanInfo other = (ScanInfo) obj;
        return Objects.equals(additionalProperties, other.additionalProperties)
                && Objects.equals(dataSource, other.dataSource) && Objects.equals(engineVersion, other.engineVersion);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ScanInfo [engineVersion=" + engineVersion + ", dataSource=" + dataSource + ", additionalProperties=" + additionalProperties + "]";
    }
}
