/*
 * IDependencyCheckFormatter.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.formatter;

import com.github.toolarium.dependency.check.model.DependecyCheckResult;


/**
 * Defines the dependency check formatter api.
 * 
 * @author patrick
 */
public interface IDependencyCheckFormatter<T> {

    /**
     * Format a dependency check result
     *
     * @param dependecyCheckResult the dependecy check result
     * @return the formatted type
     */
    T format(DependecyCheckResult dependecyCheckResult);
}
