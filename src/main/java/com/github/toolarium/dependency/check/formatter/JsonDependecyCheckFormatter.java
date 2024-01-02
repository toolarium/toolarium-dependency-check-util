/*
 * JsonDependecyCheckFormatter.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.formatter;

import com.github.toolarium.dependency.check.model.DependecyCheckResult;
import com.github.toolarium.dependency.check.util.JSONUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Format the {@link DependecyCheckResult} into a json string.
 *  
 * @author patrick
 */
public class JsonDependecyCheckFormatter implements IDependencyCheckFormatter<String> {
    private static final Logger LOG = LoggerFactory.getLogger(JsonDependecyCheckFormatter.class);

    
    /**
     * @see com.github.toolarium.dependency.check.formatter.IDependencyCheckFormatter#format(com.github.toolarium.dependency.check.model.DependecyCheckResult)
     */
    @Override
    public String format(DependecyCheckResult dependecyCheckResult) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            JSONUtil.getInstance().write(dependecyCheckResult, stream);
            return new String(stream.toString(Charset.defaultCharset()));
        } catch (IOException e) {
            LOG.warn("Could not format: " + e.getMessage(), e);
        }

        return null;
    }
}
