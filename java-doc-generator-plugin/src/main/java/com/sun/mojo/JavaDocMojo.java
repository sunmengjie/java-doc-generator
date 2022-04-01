package com.sun.mojo;

import com.alibaba.fastjson.JSON;
import com.sun.mojo.core.MojoContext;
import com.sun.mojo.core.compent.ClassComment;
import com.sun.mojo.core.compent.doc.ClassDesc;
import com.sun.mojo.core.generator.DefaultGenerator;
import com.sun.mojo.core.out.MarkDownPrinter;
import com.sun.mojo.core.parser.ClassParser;
import com.sun.mojo.core.scanner.DefaultFileScanner;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

@Mojo(name = "generator")
public class JavaDocMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;
    @Parameter(defaultValue = "${session}", readonly = true)
    private MavenSession session;
    @Parameter
    private List<String> controllers;
    @Parameter
    private String projectPath;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        //1.初始化上下文
        MojoContext context = initContext();
        //2.扫描文件
        List<String> fileList = new DefaultFileScanner().scanner(context);
        //3.解析class classDoc
        List<ClassComment> classDescList = ClassParser.parse(fileList, context);
        //4.生成doc
        DefaultGenerator generator = new DefaultGenerator();
        ClassDesc classDesc = generator.generator(context, classDescList);
        // 5.输出文档
        context.getLog().info(JSON.toJSONString(classDesc));
        MarkDownPrinter markDownPrinter = new MarkDownPrinter();
        markDownPrinter.out(classDesc, context);

    }


    /**
     * 初始化插件上下文
     */
    private MojoContext initContext() throws MojoExecutionException {
        MojoContext context = new MojoContext(getLog(), project, session, controllers);
        Log log = context.getLog();
        log.info("==========Project.build.outputDirectory==========");
        log.info(context.getProject().getBuild().getOutputDirectory());
        log.info("==========Project.build.sourceDirectory==========");
        // E:\codeHome\java-doc-generator\generator-demo\src\main\java
        log.info(context.getProject().getBuild().getSourceDirectory());
        if (context.getControllers() == null) {
            context.getLog().error("controller 为空");
            throw new MojoExecutionException("controller为空");
        }
        for (String controller : context.getControllers()) {
            log.info("plugin will generate api doc for -> " + controller);
        }
        return context;
    }
}
