package com.sun.mojo.util;

import com.sun.mojo.core.MojoContext;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class ClassLoaderUtil {

    public ClassLoader getClassLoader(MojoContext context)
    {
        try
        {
            MavenProject project = context.getProject();
            // 所有的类路径环境，也可以直接用 compilePath
            List<String> classpathElements = project.getCompileClasspathElements();

            classpathElements.add( project.getBuild().getOutputDirectory() );
            classpathElements.add( project.getBuild().getTestOutputDirectory() );
            // 转为 URL 数组
            URL urls[] = new URL[classpathElements.size()];
            for ( int i = 0; i < classpathElements.size(); ++i )
            {
                urls[i] = new File( (String) classpathElements.get( i ) ).toURL();
            }
            // 自定义类加载器
            return new URLClassLoader( urls, this.getClass().getClassLoader() );
        }
        catch ( Exception e )
        {
            context.getLog().debug( "Couldn't get the classloader." );
            return this.getClass().getClassLoader();
        }
    }


}
