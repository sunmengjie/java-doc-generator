# java-doc-generator
### ����
- һ����� javadoc�� URLClassLoader ���ɽӿ��ĵ���maven ���

## ���ʹ��
### 1.��java-doc-generator-plugin  install ->���زֿ�
### 2.��generator-demo�� ���뱾��install �Ĳ��
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
- ��<configuration>������ Ҫ���ɽӿ��ĵ���controller
### 3.mvn compile
- �ӿ��ĵ���.md ��ʽ�����ڸ�Ŀ¼��

### ע�⣺
- ��ǰ�汾��֧������ע��Ľ��� 
  - @RestController
  - @Controller
  - @GetMapping
- ����javadocע�͹淶����ע��

### todoList
������
- 1
- 2
- 3

