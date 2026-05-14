/*
 * DependencyCheckMain.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.dependency.check.main;

import com.github.toolarium.ansi.AnsiStringBuilder;
import com.github.toolarium.ansi.color.ForegroundColor;
import com.github.toolarium.dependency.check.DependencyCheckUtil;
import com.github.toolarium.dependency.check.formatter.IDependencyCheckFormatter.DependencyFilter;
import com.github.toolarium.dependency.check.model.DependecyCheckResult;
import com.github.toolarium.dependency.check.report.format.VulnerabilityReportFormatterFactory;
import com.github.toolarium.dependency.check.report.format.impl.StringVulnerabilityReportFormatter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * Implement the dependency-check main application.
 *
 * @author patrick
 */
public class DependencyCheckMain {
    private static final PrintStream OUT = System.out; // CHECKSTYLE_IGNORE_THIS_LINE
    private static final String NO_HEADER = "--no-header";
    private static final String FILTER = "--filter";
    private static final String CONFIGURATION = "--configuration";
    private static final String DIRECT = "--direct";
    private static final String SIMPLIFY = "--simplify";
    private static final String INDENT = "  ";

    private String file;
    private boolean suppressHeader;
    private boolean simplify;
    private DependencyFilter dependencyFilter;
    private String configuration;


    /**
     * Constructor for DependencyCheckMain
     */
    public DependencyCheckMain() {
        file = null;
        suppressHeader = false;
        simplify = false;
        dependencyFilter = DependencyFilter.ALL;
        configuration = null;
    }


    /**
     * The main entry-point of an application.
     *
     * @param args The arguments to run the main method.
     */
    public static void main(String[] args) {
        DependencyCheckMain app = new DependencyCheckMain();
        app.parseArguments(args);
        app.execute();
    }


    /**
     * Parse command line arguments.
     *
     * @param args the arguments
     */
    protected void parseArguments(String[] args) {
        int idx = 0;
        while (idx < args.length) {
            if (NO_HEADER.equals(args[idx])) {
                suppressHeader = true;
                idx++;
            } else if (DIRECT.equals(args[idx])) {
                dependencyFilter = DependencyFilter.DIRECT;
                idx++;
            } else if (SIMPLIFY.equals(args[idx])) {
                simplify = true;
                idx++;
            } else if (FILTER.equals(args[idx]) && idx + 1 < args.length) {
                dependencyFilter = DependencyFilter.valueOf(args[idx + 1].toUpperCase());
                idx += 2;
            } else if (CONFIGURATION.equals(args[idx]) && idx + 1 < args.length) {
                configuration = args[idx + 1];
                idx += 2;
            } else {
                if (!args[idx].startsWith("-")) {
                    file = args[idx];
                }
                idx++;
            }
        }
    }


    /**
     * Execute the dependency-check report formatting.
     */
    protected void execute() {
        if (file == null || file.isBlank()) {
            logToConsole(new AnsiStringBuilder()
                    .color(ForegroundColor.YELLOW, "Could not find the dependency-check report file.")
                    .toString());
            printHelp();
            return;
        }

        Path filePath = Paths.get(file);
        if (!Files.exists(filePath)) {
            logToConsole(new AnsiStringBuilder()
                    .color(ForegroundColor.RED, "File not found: ")
                    .bold(file)
                    .toString());
            return;
        }

        try {
            DependecyCheckResult dependecyCheckResult = DependencyCheckUtil.getInstance().readFile(filePath.toFile());

            if (simplify) {
                dependecyCheckResult = DependencyCheckUtil.getInstance().simplify(dependecyCheckResult);
                logToConsole(DependencyCheckUtil.getInstance().toJsonString(dependecyCheckResult));
                return;
            }

            if (!suppressHeader) {
                logToConsole(new AnsiStringBuilder()
                        .append("Dependency-check report: ")
                        .bold().color(ForegroundColor.CYAN, file).resetBold()
                        .toString());

                if (dependecyCheckResult.getProjectInfo() != null) {
                    logToConsole(new AnsiStringBuilder()
                            .append("Project: ")
                            .bold(dependecyCheckResult.getProjectInfo().getName())
                            .append(", Version: ")
                            .bold(dependecyCheckResult.getProjectInfo().getVersion())
                            .toString());
                }
            }

            StringVulnerabilityReportFormatter formatter = VulnerabilityReportFormatterFactory.getInstance().getStringFormatter();

            List<String> result;
            if (configuration != null) {
                String[] configs = configuration.split(",");
                for (int i = 0; i < configs.length; i++) {
                    configs[i] = configs[i].trim();
                }
                result = DependencyCheckUtil.getInstance().formatVulneabilityReport(dependecyCheckResult, formatter, dependencyFilter, configs);
            } else {
                result = DependencyCheckUtil.getInstance().formatVulneabilityReport(dependecyCheckResult, formatter, dependencyFilter);
            }

            if (result.isEmpty()) {
                logToConsole(new AnsiStringBuilder()
                        .color(ForegroundColor.GREEN, "No vulnerabilities found.")
                        .toString());
            } else {
                for (String s : result) {
                    logToConsole(s);
                }
            }
        } catch (IOException e) {
            logToConsole(new AnsiStringBuilder()
                    .color(ForegroundColor.RED, "Could not read file ")
                    .bold(file)
                    .color(ForegroundColor.RED, ": " + e.getMessage())
                    .toString());
        }
    }


    /**
     * Print help information.
     */
    protected void printHelp() {
        logToConsole(new AnsiStringBuilder()
                .append("Usage: dependency-check-util [options] <file>")
                .toString());
        logToConsole(new AnsiStringBuilder()
                .append(INDENT).bold("<file>").append("                            The dependency-check report JSON file.")
                .toString());
        logToConsole(new AnsiStringBuilder()
                .append(INDENT).bold(NO_HEADER).append("                         Suppress the header information.")
                .toString());
        logToConsole(new AnsiStringBuilder()
                .append(INDENT).bold(DIRECT).append("                            Show only direct dependencies.")
                .toString());
        logToConsole(new AnsiStringBuilder()
                .append(INDENT).bold(FILTER).append(" <ALL|DIRECT>               Set the dependency filter (default: ALL).")
                .toString());
        logToConsole(new AnsiStringBuilder()
                .append(INDENT).bold(CONFIGURATION).append(" <config1,config2>   Filter by configuration (e.g. runtimeClasspath).")
                .toString());
        logToConsole(new AnsiStringBuilder()
                .append(INDENT).bold(SIMPLIFY).append("                          Output simplified JSON.")
                .toString());
    }


    /**
     * Log a message to the console using stdout.
     *
     * @param message the message to log
     */
    protected void logToConsole(String message) {
        OUT.println(message);
    }
}
