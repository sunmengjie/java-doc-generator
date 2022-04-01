# java-doc-generator
### 介绍
- 一款基于 javadoc和 URLClassLoader 生成接口文档的maven 插件

## 如何使用
### 1.将java-doc-generator-plugin  install ->本地仓库
### 2.如generator-demo中 引入本都install 的插件
````xml
<build>
        <plugins>
            <plugin>
                <groupId>com.smj</groupId>
                <artifactId>java-doc-generator-plugin</artifactId>
                <version>1.0.0-SNAPSHOT</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generator</goal>
                        </goals>
                    </execution>
                </executions>

                <configuration>
                    <controllers>
                        <controller>com.sun.mojo.demo.controller.UserController</controller>
                    </controllers>
                </configuration>
            </plugin>
        </plugins>
    </build>
````
- 在<configuration>中配置 要生成接口文档的controller
### 3.mvn compile
- 接口文档以.md 格式生成在根目录下

### 注意：
- 当前版本仅支持少量注解的解析 
  - @RestController
  - @Controller
  - @GetMapping
- 按照javadoc注释规范进行注释

### todoList
待补充
- 1
- 2
- 3

