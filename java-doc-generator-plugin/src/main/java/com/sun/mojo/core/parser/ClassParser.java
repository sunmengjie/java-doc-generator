package com.sun.mojo.core.parser;

import com.sun.mojo.core.MojoContext;
import com.sun.mojo.core.compent.ClassComment;
import com.sun.mojo.util.DocUtil;

import java.util.List;

public class ClassParser {

    public static List<ClassComment> parse(List<String> fileList, MojoContext context) {
        try {
            return DocUtil.execute(context, fileList.get(0));
        } catch (ClassNotFoundException e) {
            context.getLog().error("an error occurred : " + e);
        }
        return null;
    }

}
