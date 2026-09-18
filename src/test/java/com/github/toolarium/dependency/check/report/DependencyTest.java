/*
 * DependencyTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;


/**
 * Test the report {@link Dependency}.
 *
 * @author patrick
 */
public class DependencyTest {


    /**
     * addProjectReferenceList deduplicates entries.
     */
    @Test
    public void addProjectReferenceListDeduplication() {
        Dependency dep = new Dependency();
        dep.addProjectReferenceList("proj-a");
        dep.addProjectReferenceList("proj-b");
        dep.addProjectReferenceList("proj-a");

        assertEquals(2, dep.getProjectReferenceList().size());
        assertEquals("proj-a", dep.getProjectReferenceList().get(0));
        assertEquals("proj-b", dep.getProjectReferenceList().get(1));
    }


    /**
     * setProjectReferenceList rebuilds the internal set so subsequent addProjectReferenceList
     * respects entries that were set via the setter.
     */
    @Test
    public void setProjectReferenceListRebuildsDedupSet() {
        Dependency dep = new Dependency();
        dep.setProjectReferenceList(Arrays.asList("x", "y"));

        dep.addProjectReferenceList("x");
        dep.addProjectReferenceList("z");

        assertEquals(3, dep.getProjectReferenceList().size());
        assertEquals("x", dep.getProjectReferenceList().get(0));
        assertEquals("y", dep.getProjectReferenceList().get(1));
        assertEquals("z", dep.getProjectReferenceList().get(2));
    }


    /**
     * setProjectReferenceList with null initialises an empty dedup set.
     */
    @Test
    public void setProjectReferenceListNull() {
        Dependency dep = new Dependency();
        dep.addProjectReferenceList("existing");
        dep.setProjectReferenceList(null);

        dep.addProjectReferenceList("new");
        assertEquals(1, dep.getProjectReferenceList().size());
        assertEquals("new", dep.getProjectReferenceList().get(0));
    }
}
