/*
 * DependencyArtifactTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Test the dependency artifact.
 * 
 * @author patrick
 */
public class DependencyArtifactTest {


    /**
     * Test parse dependency artifact 
     */
    @Test
    void testParse() {
        assertEquals(DependencyArtifact.toDependencyArtifact("group:name:1.2.3").toArtifactId(), "group:name:1.2.3");
        assertEquals(DependencyArtifact.toDependencyArtifact("pkg:maven/com.github.toolarium/toolarium-enum-configuration@1.1.8").toArtifactId(), "com.github.toolarium:toolarium-enum-configuration:1.1.8");
    }
}
