package com.sun.mojo.util;

import com.sun.javadoc.*;
import com.sun.mojo.core.MojoContext;
import com.sun.mojo.core.compent.ClassComment;
import com.sun.tools.javadoc.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class DocUtil {
    /**
     * 会自动注入
     */
    private static RootDoc rootDoc;
    private static String JAVA_SUFFIX = ".java";
    private static String CLASS_SUFFIX = ".class";

    /**
     * 会自动调用这个方法
     *
     * @param root root
     * @return true
     */
    public static boolean start(RootDoc root) {
        rootDoc = root;
        return true;
    }

    /**
     * 生成文档
     * @return 文档注释
     */
    public static List<ClassComment> execute(MojoContext context, String controllerPath) throws ClassNotFoundException {
        List<ClassComment> result = new ArrayList<>();

        String beanFilePath = toJavaPath(context.getProject().getBuild().getSourceDirectory(), controllerPath);
        beanFilePath = new File(beanFilePath).getAbsolutePath();
//        String beanFilePath = "E:\\codeHome\\java-doc-generator\\generator-demo\\src\\main\\java\\com\\sun\\mojo\\demo\\controller\\DemoController.java";
        Main.execute(new String[]{"-doclet", DocUtil.class.getName(), "-docletpath", "接口文档", "-encoding", "utf-8", beanFilePath});
        ClassDoc[] classes  = rootDoc.classes();
        ClassDoc classDoc = classes [0];
        // load class
        ClassLoader classLoader = new ClassLoaderUtil().getClassLoader(context);
        Class<?> aClass = classLoader.loadClass(classDoc.qualifiedName());
        ClassComment classComment = new ClassComment(aClass, classDoc);
        result.add(classComment);
        return result;
        // 填充类信息
//        AnnotationDesc[] annotations = classDoc.annotations();
//        Map<String, List<AnnotationDesc>> annotationMap = Arrays.stream(annotations).collect(Collectors.groupingBy(item -> item.annotationType().typeName()));
//        AnnotationDesc requestMapping = annotationMap.get("RequestMapping").get(0);
//        AnnotationDesc.ElementValuePair[] elementValuePairs = requestMapping.elementValues();
//
//        MethodDoc[] methods = classDoc.methods();
//        for (MethodDoc method : methods) {
//            context.getLog().info("方法 : " + method.name());
//            context.getLog().info("注释 : " + method.commentText());
//            context.getLog().info("返回类型 : " + method.returnType().typeName());
//            ParamTag[] paramTags = method.paramTags();
//            Map<String, List<ParamTag>> paramTagMap = Arrays.stream(paramTags).collect(Collectors.groupingBy(paramTag -> paramTag.parameterName()));
//
//            Parameter[] parameters = method.parameters();
//            for (Parameter parameter : parameters) {
//                context.getLog().info("\t" + parameter.name() + "\t" + paramTagMap.get(parameter.name()).get(0).parameterComment() + "\t"+ "\t" + parameter.typeName());
//            }
//        }
//
//
//        return null;

    }

    private static String toJavaPath(String projectPath, String controllerPath) {
        String s = controllerPath.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
        return projectPath + "\\" + s + JAVA_SUFFIX;
    }


    public static void main(String[] args) throws ClassNotFoundException {
        String projectPath = "E:\\codeHome\\java-doc-generator\\generator-demo\\";
        String controllerPath = "com.sun.mojo.demo.controller.DemoController";

        execute(null, null);
//        String absolutePath = file.getAbsolutePath();
//        String[] list = file.list();
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            BufferedInputStream byteArrayOutputStream = new BufferedInputStream(fileInputStream);
//            String text = "";
//            byte[] buffer = new byte[1024];
//            int flag;
//            while((flag = byteArrayOutputStream.read(buffer)) != -1) {
//                text += new String(buffer, 0, flag);
//            }
//            System.out.println(text);
//            byteArrayOutputStream.close();
//            System.out.println(absolutePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
