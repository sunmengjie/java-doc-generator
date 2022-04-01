package com.sun.mojo.core.scanner;

import com.sun.mojo.core.MojoContext;

import java.io.File;
import java.util.List;

public interface FileScanner {
    List<String> scanner(MojoContext context);
}
