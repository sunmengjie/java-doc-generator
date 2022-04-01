package com.sun.mojo.core.scanner;

import com.sun.mojo.core.MojoContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DefaultFileScanner implements FileScanner{
    @Override
    public List<String> scanner(MojoContext context) {
        List<String> controllers = context.getControllers();
        return controllers;
    }
}
