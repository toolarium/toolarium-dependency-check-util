/*
 * IncludedBy.java
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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


/**
 * Define the included by
 * 
 * @author patrick
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"reference" })
public class IncludedBy {
    @JsonProperty("reference")
    private String reference;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * Get the reference
     *
     * @return the reference
     */
    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    
    /**
     * Set the reference
     *
     * @param reference the reference
     */
    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
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
     * @param name  the name
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
        return Objects.hash(additionalProperties, reference);
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
        
        IncludedBy other = (IncludedBy) obj;
        return Objects.equals(additionalProperties, other.additionalProperties)
                && Objects.equals(reference, other.reference);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "IncludedBy [reference=" + reference + ", additionalProperties=" + additionalProperties + "]";
    }
}
