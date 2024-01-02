/*
 * JsonDependecyCheckFormatterTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.toolarium.dependency.check.DependencyCheckUtil;
import com.github.toolarium.dependency.check.DependencyCheckUtilTest;
import com.github.toolarium.dependency.check.model.DependecyCheckResult;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;


/**
 * Test the json formatter
 *  
 * @author patrick
 */
public class JsonDependecyCheckFormatterTest {
    private static final String NL = "\n";
    private static final String JSON_RESULT = "{" + NL
            + "  \"reportSchema\" : \"1.1\"," + NL
            + "  \"scanInfo\" : {" + NL
            + "    \"engineVersion\" : \"9.0.6\"," + NL
            + "    \"dataSource\" : [ {" + NL
            + "      \"name\" : \"NVD API Last Checked\"," + NL
            + "      \"timestamp\" : \"2024-01-01T07:56:51+01\"" + NL
            + "    }, {" + NL
            + "      \"name\" : \"NVD API Last Modified\"," + NL
            + "      \"timestamp\" : \"2024-01-01T06:15:08Z\"" + NL
            + "    } ]" + NL
            + "  }," + NL
            + "  \"projectInfo\" : {" + NL
            + "    \"name\" : \"root project 'toolarium-system-command'\"," + NL
            + "    \"groupID\" : \"com.github.toolarium\"," + NL
            + "    \"artifactID\" : \"toolarium-system-command\"," + NL
            + "    \"version\" : \"0.9.1-SNAPSHOT\"," + NL
            + "    \"reportDate\" : \"2024-01-01T12:41:44.823341200Z\"," + NL
            + "    \"credits\" : {" + NL
            + "      \"NVD\" : \"This product uses the NVD API but is not endorsed or certified by the NVD. This report contains data retrieved from the National Vulnerability Database: https://nvd.nist.gov\"," + NL
            + "      \"CISA\" : \"This report may contain data retrieved from the CISA Known Exploited Vulnerability Catalog: https://www.cisa.gov/known-exploited-vulnerabilities-catalog\"," + NL
            + "      \"NPM\" : \"This report may contain data retrieved from the Github Advisory Database (via NPM Audit API): https://github.com/advisories/\"," + NL
            + "      \"RETIREJS\" : \"This report may contain data retrieved from the RetireJS community: https://retirejs.github.io/retire.js/\"," + NL
            + "      \"OSSINDEX\" : \"This report may contain data retrieved from the Sonatype OSS Index: https://ossindex.sonatype.org\"" + NL
            + "    }" + NL
            + "  }," + NL
            + "  \"dependencies\" : [ {" + NL
            + "    \"fileName\" : \"jackson-annotations-2.15.3.jar\"," + NL
            + "    \"description\" : \"Core annotations used for value types, used by Jackson data binding package.\\n  \"," + NL
            + "    \"license\" : \"The Apache Software License, Version 2.0: https://www.apache.org/licenses/LICENSE-2.0.txt\"," + NL
            + "    \"projectReferences\" : [ \"toolarium-system-command:annotationProcessor\" ]," + NL
            + "    \"includedBy\" : [ {" + NL
            + "      \"reference\" : \"pkg:maven/com.github.toolarium/toolarium-enum-configuration@1.1.8\"" + NL
            + "    } ]," + NL
            + "    \"packages\" : [ {" + NL
            + "      \"id\" : \"pkg:maven/com.fasterxml.jackson.core/jackson-annotations@2.15.3\"," + NL
            + "      \"confidence\" : \"HIGH\"," + NL
            + "      \"url\" : \"https://ossindex.sonatype.org/component/pkg:maven/com.fasterxml.jackson.core/jackson-annotations@2.15.3?utm_source=dependency-check&utm_medium=integration&utm_content=9.0.6\"" + NL
            + "    } ]," + NL
            + "    \"vulnerabilityIds\" : [ {" + NL
            + "      \"id\" : \"cpe:2.3:a:fasterxml:jackson-modules-java8:2.15.3:*:*:*:*:*:*:*\"," + NL
            + "      \"confidence\" : \"LOW\"" + NL
            + "    } ]" + NL
            + "  } ]" + NL
            + "}";
    
    
    /**
     * Test the json formatting
     * 
     * @throws IOException In case of an IO error 
     */
    @Test
    public void test() throws IOException {
        DependecyCheckResult dependecyCheckResult = DependencyCheckUtil.getInstance().readFile(Paths.get(DependencyCheckUtilTest.TEST_RESOURCE_PATH, DependencyCheckUtilTest.SAMPLE).toFile());
        assertNotNull(dependecyCheckResult);

        DependecyCheckResult simplifiedDependecyCheckResult = DependencyCheckUtil.getInstance().simplify(dependecyCheckResult);
        assertEquals(JSON_RESULT, DependencyCheckUtil.getInstance().toJsonString(simplifiedDependecyCheckResult).replaceAll("\r", ""));
    }
}
