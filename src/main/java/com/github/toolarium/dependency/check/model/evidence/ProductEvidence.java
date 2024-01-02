/*
 * ProductEvidence.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.model.evidence;

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
 * Define the product evidence
 * 
 * @author patrick
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "confidence", "source", "name", "value" })
public class ProductEvidence {
    @JsonProperty("type")
    private String type;
    @JsonProperty("confidence")
    private String confidence;
    @JsonProperty("source")
    private String source;
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    
    /**
     * Get the type
     *
     * @return the type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    
    /**
     * Set the type
     *
     * @param type the type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    
    /**
     * Get the confidence
     *
     * @return the confidence
     */
    @JsonProperty("confidence")
    public String getConfidence() {
        return confidence;
    }

    
    /**
     * Set the confidence
     *
     * @param confidence the confidence
     */
    @JsonProperty("confidence")
    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    
    /**
     * Get the source
     *
     * @return the source
     */
    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    
    /**
     * Set the source
     *
     * @param source the source
     */
    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    
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
     * Get the value
     *
     * @return the value
     */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    
    /**
     * Set the value
     *
     * @param value the value
     */
    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
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
        return Objects.hash(additionalProperties, confidence, name, source, type, value);
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
        
        ProductEvidence other = (ProductEvidence) obj;
        return Objects.equals(additionalProperties, other.additionalProperties)
                && Objects.equals(confidence, other.confidence) && Objects.equals(name, other.name)
                && Objects.equals(source, other.source) && Objects.equals(type, other.type)
                && Objects.equals(value, other.value);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProductEvidence [type=" + type + ", confidence=" + confidence + ", source=" + source + ", name=" + name
                + ", value=" + value + ", additionalProperties=" + additionalProperties + "]";
    }
}
