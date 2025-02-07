/*
 * DependencyCheckFilter.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.report;

import java.util.HashSet;
import java.util.Set;


/**
 * Defines the dependency check filter
 * 
 * @author patrick
 */
public class DependencyCheckFilter {
    //private boolean removesimplify;
    private Set<DependencyArtifact> whitelist;
    
    
    /**
     * Constructor for DependencyCheckFilter
     */
    public DependencyCheckFilter() {
        whitelist = new HashSet<DependencyArtifact>();
    }
    
    
    /**
     * Add whitelist
     *
     * @param dependencyArtifactAsString the dependency artefact
     */
    public void addWhitelist(String dependencyArtifactAsString) {
        addWhitelist(DependencyArtifact.toDependencyArtifact(dependencyArtifactAsString));
    }

    
    /**
     * Add whitelist
     *
     * @param dependencyArtifact the dependency artefact
     */
    public void addWhitelist(DependencyArtifact dependencyArtifact) {
        if (!whitelist.contains(dependencyArtifact)) {
            whitelist.add(dependencyArtifact);
        }
    }
}

