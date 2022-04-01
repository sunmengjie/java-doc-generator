package com.sun.mojo.core.generator;

import com.sun.javadoc.*;
import com.sun.mojo.core.MojoContext;
import com.sun.mojo.core.compent.ClassComment;
import com.sun.mojo.core.compent.doc.ArgsDesc;
import com.sun.mojo.core.compent.doc.ClassDesc;
import com.sun.mojo.core.compent.doc.MethodDesc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class DefaultGenerator implements Generator {

    @Override
    public ClassDesc generator(MojoContext context, List<ClassComment> classComments) {
        ClassDesc target = new ClassDesc();

        ClassComment classComment = classComments.get(0);
        ClassDoc classDoc = classComment.getClassDoc();
        Class clazz = classComment.getClazz();

        List<Annotation> annotationList = Arrays.asList(clazz.getAnnotations());
        if (clazz.getAnnotation(Controller.class) == null && clazz.getAnnotation(RestController.class) == null) {
            context.getLog().error(clazz.getSimpleName() + " is not a controller");
            return null;
        }
        // 解析class name
        target.setClassName(clazz.getName());
        // 解析url
        RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
        String[] value = requestMapping.value();
        context.getLog().info(" path " + value[0]);
        target.setUrl(value[0]);
        // 解析类注释
        target.setComment(classDoc.getRawCommentText());
        // 解析方法
        List<MethodDesc> methodDescs = toMethodDesc(context, classComment);
        target.setMethodDesc(methodDescs);

        return target;
    }

    private List<MethodDesc> toMethodDesc(MojoContext context, ClassComment classComment) {
        List<MethodDesc> target = new ArrayList<>();
        Class clazz = classComment.getClazz();
        ClassDoc classDoc = classComment.getClassDoc();
        Map<String, List<MethodDoc>> methodDocMap = Arrays.stream(classDoc.methods()).collect(Collectors.groupingBy(item -> item.name()));

        for (Method method : clazz.getMethods()) {
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            if (getMapping != null) {
                MethodDesc methodDesc = new MethodDesc();
                // 解析url
                methodDesc.setUrl(getMapping.value()[0]);
                // 解析name
                methodDesc.setName(method.getName());
                // 解析注释
                MethodDoc methodDoc = methodDocMap.get(method.getName()).get(0);
                methodDesc.setComment(methodDoc.commentText());
                // 解析入参
                Map<String, ArgsDesc> argsDescMap = new HashMap<>();
                ParamTag[] paramTags = methodDoc.paramTags();
                Map<String, List<ParamTag>> paramTagMap = Arrays.stream(paramTags).collect(Collectors.groupingBy(paramTag -> paramTag.parameterName()));
                Parameter[] parameters = methodDoc.parameters();
                for (Parameter parameter : parameters) {
                    ArgsDesc argsDesc = new ArgsDesc();
                    argsDesc.setName(parameter.name());
                    argsDesc.setType(parameter.typeName());
                    argsDesc.setComment(paramTagMap.get(parameter.name()).get(0).parameterComment());
                    argsDescMap.put(parameter.name(), argsDesc);
                }
                methodDesc.setArgsMap(argsDescMap);
                target.add(methodDesc);
            }
        }
        return target;
    }
}
