**Maven package为jar文件后运行，报：“没有主清单属性”**

- 参考：[解决Idea Maven生成的jar运行出现“没有主清单属性”问题_jar中没有主清单属性啥意思啊-CSDN博客](https://blog.csdn.net/u012637358/article/details/113996771)

- 没有指定主类导致的

- pom文件添加如下内容

  ```xml
  <build>
      <plugins>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-shade-plugin</artifactId>
              <version>3.2.4</version>
              <executions>
                  <execution>
                      <phase>package</phase>
                      <goals>
                          <goal>shade</goal>
                      </goals>
                      <configuration>
                          <transformers>
                              <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                  <mainClass>org.sonatype.haven.HavenCli</mainClass>
                              </transformer>
                          </transformers>
                      </configuration>
                  </execution>
              </executions>
          </plugin>
      </plugins>
  </build>
  ```

  