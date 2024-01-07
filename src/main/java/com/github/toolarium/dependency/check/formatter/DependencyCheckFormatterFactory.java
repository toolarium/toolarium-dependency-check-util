/*
 * DependencyCheckFormatterFactory.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.formatter;

import com.github.toolarium.dependency.check.formatter.impl.JsonDependecyCheckFormatter;
import com.github.toolarium.dependency.check.formatter.impl.VulnerabilityReportDependecyCheckFormatter;

/**
 * Defines the {@link IDependencyCheckFormatter} factory.
 * 
 * @author patrick
 */
public final class DependencyCheckFormatterFactory {

    /**
     * Private class, the only instance of the singelton which will be created by accessing the holder class.
     *
     * @author patrick
     */
    private static class HOLDER {
        static final DependencyCheckFormatterFactory INSTANCE = new DependencyCheckFormatterFactory();
    }

    
    /**
     * Constructor
     */
    private DependencyCheckFormatterFactory() {
        // NOP
    }

    
    /**
     * Get the instance
     *
     * @return the instance
     */
    public static DependencyCheckFormatterFactory getInstance() {
        return HOLDER.INSTANCE;
    }

    
    /**
     * Get a json dependency check formatter
     *
     * @return a json dependency check formatter
     */
    public JsonDependecyCheckFormatter getJsonDependecyCheckFormatter() {
        return new JsonDependecyCheckFormatter();
    }
    
    
    /**
     * Get a vulnerability report dependency check formatter
     *
     * @return a vulnerability report dependency check formatter
     */
    public VulnerabilityReportDependecyCheckFormatter getVulnerabilityReportDependecyCheckFormatter() {
        return new VulnerabilityReportDependecyCheckFormatter();
    }
}
