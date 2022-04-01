package com.sun.mojo.core.generator;

import com.sun.mojo.core.MojoContext;
import com.sun.mojo.core.compent.ClassComment;
import com.sun.mojo.core.compent.doc.ClassDesc;

import java.util.List;

public interface Generator {
    ClassDesc generator(MojoContext context, List<ClassComment> classComment);
}
