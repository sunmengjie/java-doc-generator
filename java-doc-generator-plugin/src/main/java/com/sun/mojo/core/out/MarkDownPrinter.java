package com.sun.mojo.core.out;

import com.sun.mojo.core.MojoContext;
import com.sun.mojo.core.compent.doc.ArgsDesc;
import com.sun.mojo.core.compent.doc.ClassDesc;
import com.sun.mojo.core.compent.doc.MethodDesc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MarkDownPrinter {
    public void out(ClassDesc classDesc, MojoContext context) {
        File file = new File("api.md");

        try {
            if (file.exists()) {
                context.getLog().error("请先删除生成的接口文档");
                return;
            } else {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            String classUrl = classDesc.getUrl();
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("# 接口文档 \n");
            bw.write("## " + classDesc.getComment() + "\n");
            bw.write("- class : " + classDesc.getClassName() + "\n");
            List<MethodDesc> methodDescList = classDesc.getMethodDesc();
            for (MethodDesc methodDesc : methodDescList) {
                bw.write("\n");
                bw.write("### 接口 ： " + methodDesc.getComment() + "\n");
                bw.write("- url  ：  " + classUrl + methodDesc.getUrl() + "\n");
                bw.write("- method  :   " + methodDesc.getName() + "\n");
                bw.newLine();
                bw.write("#### 参数 \n" );
                bw.newLine();
                bw.write("|   参数名   |   类型   |   comment   |\n");
                bw.write("|   ----   |   ----   |   ----   |\n");
                for (Map.Entry<String, ArgsDesc> stringArgsDescEntry : methodDesc.getArgsMap().entrySet()) {
                    bw.write("|   " + stringArgsDescEntry.getValue().getName() +
                                    "   |   " + stringArgsDescEntry.getValue().getType() +
                                    "   |   " + stringArgsDescEntry.getValue().getComment() +
                                    "   |\n");
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
