/*
 * TextUtil.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.util;

import com.github.toolarium.common.util.StringUtil;
import java.util.StringTokenizer;


/**
 * Text utility
 * 
 * @author patrick
 */
public final class TextUtil {
    /** NL */
    public static final String NL = "\n";

    /** SPACE */
    public static final String SPACE = " ";

    
    /**
     * Private class, the only instance of the singelton which will be created by accessing the holder class.
     *
     * @author patrick
     */
    private static class HOLDER {
        static final TextUtil INSTANCE = new TextUtil();
    }

    
    /**
     * Constructor
     */
    private TextUtil() {
        // NOP
    }

    
    /**
     * Get the instance
     *
     * @return the instance
     */
    public static TextUtil getInstance() {
        return HOLDER.INSTANCE;
    }


    /**
     * Create a block text
     *
     * @param indent the indention
     * @param title the title
     * @param maxTitelLen the max title len
     * @param text the text
     * @param maxLen the max len of the text
     * @return the created block text
     */
    public StringBuilder blockText(String indent, String title, int maxTitelLen, String text, int maxLen) {
        StringBuilder titleBuidler = new StringBuilder().append(title).append(StringUtil.getInstance().newString(SPACE, maxTitelLen - title.length()));
                
        StringBuilder builder = new StringBuilder().append(indent).append(titleBuidler);
        String subIndent = StringUtil.getInstance().newString(SPACE, titleBuidler.length() + indent.length());
        StringBuilder data = new StringBuilder();
        StringTokenizer st = new StringTokenizer(text, " \t\n\r\f", true);
        while (st.hasMoreTokens()) {
            String d = st.nextToken();
            
            if ((data.length() + d.length()) >= maxLen) {
                if (!data.toString().isEmpty()) {
                    builder.append(data);
                    if (data.length() < maxLen) {
                        builder.append(StringUtil.getInstance().newString(SPACE, maxLen - data.length()));
                    }
                    builder.append(NL).append(subIndent);
                }
                
                data = new StringBuilder();
                d = StringUtil.getInstance().trimLeft(d);
                
                while (d.length() > maxLen) {
                    data.append(d.substring(0, maxLen));
                    builder.append(data).append(NL).append(subIndent);
                    data = new StringBuilder();
                    d = d.substring(maxLen);
                }
                
                data = new StringBuilder().append(d);
            } else {
                data.append(d);
            }
            
            if (st.hasMoreTokens()) {
                String sep = st.nextToken();
                if (SPACE.equals(sep)) {
                    data.append(sep);
                }
            }
        }
        
        if (!data.toString().isEmpty()) {
            builder.append(data);
            if (data.length() < maxLen) {
                builder.append(StringUtil.getInstance().newString(SPACE, maxLen - data.length()));
            }
        }
        
        return builder;
    }
}
