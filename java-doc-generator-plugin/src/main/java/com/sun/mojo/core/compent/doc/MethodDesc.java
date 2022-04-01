package com.sun.mojo.core.compent.doc;

import lombok.Data;

import java.util.Map;

@Data
public class MethodDesc {
    private String name;
    private String url;
    private String comment;
    private Map<String, ArgsDesc> argsMap;
}
