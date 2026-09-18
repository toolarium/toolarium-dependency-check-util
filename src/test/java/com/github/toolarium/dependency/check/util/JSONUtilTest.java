/*
 * JSONUtilTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;


/**
 * Test the {@link JSONUtil}.
 *
 * @author patrick
 */
public class JSONUtilTest {


    /**
     * convert(Collection) with null returns empty JSON array.
     */
    @Test
    public void convertCollectionNull() {
        assertEquals("[]", JSONUtil.getInstance().convert((java.util.Collection<String>) null));
    }


    /**
     * convert(Collection) with empty list returns empty JSON array.
     */
    @Test
    public void convertCollectionEmpty() {
        String result = JSONUtil.getInstance().convert(Collections.emptyList());
        assertTrue(result.contains("[ ]") || result.contains("[]"));
    }


    /**
     * convert(Collection) with entries produces a JSON array containing those entries.
     */
    @Test
    public void convertCollectionWithEntries() {
        String result = JSONUtil.getInstance().convert(Arrays.asList("alpha", "beta"));
        assertTrue(result.contains("\"alpha\""));
        assertTrue(result.contains("\"beta\""));
    }


    /**
     * convert(Collection) skips null entries.
     */
    @Test
    public void convertCollectionSkipsNullEntries() {
        String result = JSONUtil.getInstance().convert(Arrays.asList("a", null, "b"));
        assertTrue(result.contains("\"a\""));
        assertTrue(result.contains("\"b\""));
    }


    /**
     * convert(String) with null returns empty list.
     */
    @Test
    public void convertStringNull() {
        List<String> result = JSONUtil.getInstance().convert((String) null);
        assertTrue(result.isEmpty());
    }


    /**
     * convert(String) with blank returns empty list.
     */
    @Test
    public void convertStringBlank() {
        List<String> result = JSONUtil.getInstance().convert("   ");
        assertTrue(result.isEmpty());
    }


    /**
     * convert(String) with a plain non-JSON string is returned as a single-element list.
     */
    @Test
    public void convertStringSingleValue() {
        List<String> result = JSONUtil.getInstance().convert("hello");
        assertEquals(1, result.size());
        assertEquals("hello", result.get(0));
    }


    /**
     * convert(String) with a JSON array returns all elements.
     */
    @Test
    public void convertStringJsonArray() {
        List<String> result = JSONUtil.getInstance().convert("[\"a\",\"b\",\"c\"]");
        assertEquals(3, result.size());
        assertEquals("a", result.get(0));
        assertEquals("b", result.get(1));
        assertEquals("c", result.get(2));
    }


    /**
     * convert(String) with an empty JSON array returns empty list.
     */
    @Test
    public void convertStringEmptyJsonArray() {
        List<String> result = JSONUtil.getInstance().convert("[]");
        assertTrue(result.isEmpty());
    }


    /**
     * convert(String) with malformed JSON throws IllegalArgumentException.
     */
    @Test
    public void convertStringMalformedJson() {
        assertThrows(IllegalArgumentException.class, () -> JSONUtil.getInstance().convert("[unclosed"));
    }


    /**
     * read() with null input stream returns null without throwing.
     *
     * @throws IOException In case of an error
     */
    @Test
    public void readNullInputStream() throws IOException {
        Object result = JSONUtil.getInstance().read(String.class, null);
        assertNull(result);
    }


    /**
     * read() with empty input stream returns null (no content).
     *
     * @throws IOException In case of an error
     */
    @Test
    public void readEmptyInputStream() throws IOException {
        ByteArrayInputStream empty = new ByteArrayInputStream(new byte[0]);
        Object result = JSONUtil.getInstance().read(String.class, empty);
        assertNull(result);
    }


    /**
     * read() with malformed JSON throws IOException.
     */
    @Test
    public void readMalformedJson() {
        ByteArrayInputStream bad = new ByteArrayInputStream("{not valid json".getBytes(StandardCharsets.UTF_8));
        assertThrows(IOException.class, () -> JSONUtil.getInstance().read(String.class, bad));
    }
}
