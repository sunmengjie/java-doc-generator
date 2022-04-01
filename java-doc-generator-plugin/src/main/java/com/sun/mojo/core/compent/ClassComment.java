package com.sun.mojo.core.compent;

import com.sun.javadoc.ClassDoc;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClassComment {
    private Class clazz;
    private ClassDoc classDoc;
}
