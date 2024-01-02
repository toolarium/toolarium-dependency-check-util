/*
 * Credits.java
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
 * Define the credits
 * 
 * @author patrick
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"NVD", "CISA", "NPM", "RETIREJS", "OSSINDEX" })
public class Credits {
    @JsonProperty("NVD")
    private String nvd;
    @JsonProperty("CISA")
    private String cisa;
    @JsonProperty("NPM")
    private String npm;
    @JsonProperty("RETIREJS")
    private String retirejs;
    @JsonProperty("OSSINDEX")
    private String ossindex;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    
    /**
     * Get the Nvd
     *
     * @return the nvd
     */
    @JsonProperty("NVD")
    public String getNvd() {
        return nvd;
    }

    
    /**
     * Set the nvd
     *
     * @param nvd the nvd
     */
    @JsonProperty("NVD")
    public void setNvd(String nvd) {
        this.nvd = nvd;
    }

    
    /**
     * GEt the cisa
     *
     * @return the  cisa
     */
    @JsonProperty("CISA")
    public String getCisa() {
        return cisa;
    }

    
    /**
     * Set the cisa
     *
     * @param cisa the cisa
     */
    @JsonProperty("CISA")
    public void setCisa(String cisa) {
        this.cisa = cisa;
    }

    
    /**
     * Get the npm
     *
     * @return the npm
     */
    @JsonProperty("NPM")
    public String getNpm() {
        return npm;
    }

    
    /**
     * Set the npm
     *
     * @param npm the npm
     */
    @JsonProperty("NPM")
    public void setNpm(String npm) {
        this.npm = npm;
    }

    
    /**
     * Get the retirejs
     *
     * @return the retirejs
     */
    @JsonProperty("RETIREJS")
    public String getRetirejs() {
        return retirejs;
    }

    
    /**
     * Set the retirejs
     *
     * @param retirejs the retirejs
     */
    @JsonProperty("RETIREJS")
    public void setRetirejs(String retirejs) {
        this.retirejs = retirejs;
    }

    
    /**
     * Get the oss index
     *
     * @return the oss index
     */
    @JsonProperty("OSSINDEX")
    public String getOssindex() {
        return ossindex;
    }

    
    /**
     * Set the oss index 
     *
     * @param ossindex the oss index
     */
    @JsonProperty("OSSINDEX")
    public void setOssindex(String ossindex) {
        this.ossindex = ossindex;
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
        return Objects.hash(additionalProperties, cisa, npm, nvd, ossindex, retirejs);
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
        
        Credits other = (Credits) obj;
        return Objects.equals(additionalProperties, other.additionalProperties) && Objects.equals(cisa, other.cisa)
                && Objects.equals(npm, other.npm) && Objects.equals(nvd, other.nvd)
                && Objects.equals(ossindex, other.ossindex) && Objects.equals(retirejs, other.retirejs);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Credits [nvd=" + nvd + ", cisa=" + cisa + ", npm=" + npm + ", retirejs=" + retirejs + ", ossindex="
                + ossindex + ", additionalProperties=" + additionalProperties + "]";
    }
}
