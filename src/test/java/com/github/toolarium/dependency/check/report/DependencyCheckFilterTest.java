/*
 * DependencyCheckFilterTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.toolarium.dependency.check.DependencyCheckUtil;
import com.github.toolarium.dependency.check.DependencyCheckUtilTest;
import com.github.toolarium.dependency.check.model.DependecyCheckResult;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;


/**
 * Test the {@link DependencyCheckFilter} and its integration with
 * {@link DependencyCheckUtil#filter(DependecyCheckResult, DependencyCheckFilter)}.
 *
 * @author patrick
 */
public class DependencyCheckFilterTest {


    /**
     * An artifact is whitelisted after adding it by string.
     */
    @Test
    public void isWhitelistedAfterAdd() {
        DependencyCheckFilter filter = new DependencyCheckFilter();
        filter.addWhitelist("com.example:my-lib:1.0.0");

        assertTrue(filter.isWhitelisted(new DependencyArtifact("com.example", "my-lib", "1.0.0")));
    }


    /**
     * An artifact that was not added is not whitelisted.
     */
    @Test
    public void isNotWhitelistedWhenAbsent() {
        DependencyCheckFilter filter = new DependencyCheckFilter();
        filter.addWhitelist("com.example:my-lib:1.0.0");

        assertFalse(filter.isWhitelisted(new DependencyArtifact("com.example", "other-lib", "1.0.0")));
    }


    /**
     * null is never whitelisted.
     */
    @Test
    public void isNotWhitelistedForNull() {
        DependencyCheckFilter filter = new DependencyCheckFilter();
        assertFalse(filter.isWhitelisted(null));
    }


    /**
     * Adding the same artifact twice does not duplicate the whitelist entry.
     */
    @Test
    public void addWhitelistDeduplication() {
        DependencyCheckFilter filter = new DependencyCheckFilter();
        DependencyArtifact artifact = new DependencyArtifact("g", "a", "1");
        filter.addWhitelist(artifact);
        filter.addWhitelist(artifact);

        assertTrue(filter.isWhitelisted(artifact));
    }


    /**
     * pkg:maven format is parsed and matched correctly.
     */
    @Test
    public void whitelistFromPkgMavenFormat() {
        DependencyCheckFilter filter = new DependencyCheckFilter();
        filter.addWhitelist("pkg:maven/com.fasterxml.jackson.core/jackson-databind@2.15.3");

        assertTrue(filter.isWhitelisted(new DependencyArtifact("com.fasterxml.jackson.core", "jackson-databind", "2.15.3")));
    }


    /**
     * filter(result, DependencyCheckFilter) excludes the whitelisted dependency and returns
     * an empty dependency list when jackson-databind is the only vulnerable package.
     *
     * @throws IOException In case of a file error
     */
    @Test
    public void filterExcludesWhitelistedDependency() throws IOException {
        DependecyCheckResult raw = DependencyCheckUtil.getInstance().readFile(
                Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, DependencyCheckUtilTest.FULL_REPORT_1_VULNERABLE).toFile());
        assertNotNull(raw);

        DependecyCheckResult withoutWhitelist = DependencyCheckUtil.getInstance().filter(raw);
        assertFalse(withoutWhitelist.getDependencies().isEmpty());

        DependencyCheckFilter filter = new DependencyCheckFilter();
        filter.addWhitelist("com.fasterxml.jackson.core:jackson-databind:2.15.3");
        DependecyCheckResult withWhitelist = DependencyCheckUtil.getInstance().filter(raw, filter);
        assertNotNull(withWhitelist);
        assertTrue(withWhitelist.getDependencies().isEmpty());
    }


    /**
     * filter(result, null) behaves the same as filter(result) — null filter is a no-op.
     *
     * @throws IOException In case of a file error
     */
    @Test
    public void filterWithNullFilterIsNoOp() throws IOException {
        DependecyCheckResult raw = DependencyCheckUtil.getInstance().readFile(
                Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, DependencyCheckUtilTest.FULL_REPORT_1_VULNERABLE).toFile());
        assertNotNull(raw);

        DependecyCheckResult withoutFilter = DependencyCheckUtil.getInstance().filter(raw);
        DependecyCheckResult withNullFilter = DependencyCheckUtil.getInstance().filter(raw, null);

        assertEquals(withoutFilter.getDependencies().size(), withNullFilter.getDependencies().size());
    }
}
