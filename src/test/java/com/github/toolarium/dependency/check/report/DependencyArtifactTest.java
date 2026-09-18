/*
 * DependencyArtifactTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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


    /**
     * null input returns null.
     */
    @Test
    void testParseNull() {
        assertNull(DependencyArtifact.toDependencyArtifact(null));
    }


    /**
     * Blank input returns null.
     */
    @Test
    void testParseBlank() {
        assertNull(DependencyArtifact.toDependencyArtifact("   "));
    }


    /**
     * Input that matches neither two-colon nor two-slash format returns null.
     */
    @Test
    void testParseUnknownFormat() {
        assertNull(DependencyArtifact.toDependencyArtifact("just-a-name"));
    }


    /**
     * pkg:maven format without version produces an empty version string.
     */
    @Test
    void testParsePkgMavenWithoutVersion() {
        DependencyArtifact artifact = DependencyArtifact.toDependencyArtifact("pkg:maven/com.example/my-lib");
        assertEquals("com.example", artifact.getGroupId());
        assertEquals("my-lib", artifact.getName());
        assertEquals("", artifact.getVersion());
    }
}
