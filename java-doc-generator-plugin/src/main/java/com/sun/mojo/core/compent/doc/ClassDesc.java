package com.sun.mojo.core.compent.doc;

import lombok.Data;

import java.util.List;

@Data
public class ClassDesc {
    /**
     * 类名称
     */
    private String className;

    /**
     * 方法
     */
    private List<MethodDesc> methodDesc;

    /**
     * url
     */
    private String url;

    /**
     * 注释
     */
    private String comment;

}
