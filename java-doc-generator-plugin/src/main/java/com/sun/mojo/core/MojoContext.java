package com.sun.mojo.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import java.util.List;

@Data
@AllArgsConstructor
public class MojoContext {
    private Log log;
    private MavenProject project;
    private MavenSession session;
    private List<String> controllers;
}
