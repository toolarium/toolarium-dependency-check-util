/*
 * EvidenceCollected.java
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
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * Define the evidence collected
 * 
 * @author patrick
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"vendorEvidence", "productEvidence" })
public class EvidenceCollected {
    @JsonProperty("vendorEvidence")
    private List<VendorEvidence> vendorEvidence;
    @JsonProperty("productEvidence")
    private List<ProductEvidence> productEvidence;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * Get the vendor engine
     *
     * @return the vendor engine
     */
    @JsonProperty("vendorEvidence")
    public List<VendorEvidence> getVendorEvidence() {
        return vendorEvidence;
    }

    
    /**
     * Set the vendor engine
     *
     * @param vendorEvidence the vendor engine
     */
    @JsonProperty("vendorEvidence")
    public void setVendorEvidence(List<VendorEvidence> vendorEvidence) {
        this.vendorEvidence = vendorEvidence;
    }

    
    /**
     * Get the product evidence
     *
     * @return the product evidence
     */
    @JsonProperty("productEvidence")
    public List<ProductEvidence> getProductEvidence() {
        return productEvidence;
    }

    
    /**
     * Set the product evidence
     *
     * @param productEvidence the product evidence
     */
    @JsonProperty("productEvidence")
    public void setProductEvidence(List<ProductEvidence> productEvidence) {
        this.productEvidence = productEvidence;
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
        return Objects.hash(additionalProperties, productEvidence, vendorEvidence);
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
        
        EvidenceCollected other = (EvidenceCollected) obj;
        return Objects.equals(additionalProperties, other.additionalProperties)
                && Objects.equals(productEvidence, other.productEvidence)
                && Objects.equals(vendorEvidence, other.vendorEvidence);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EvidenceCollected [vendorEvidence=" + vendorEvidence + ", productEvidence=" + productEvidence + ", additionalProperties=" + additionalProperties + "]";
    }
}
