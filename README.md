# 1.development environment

IDEA 2021.1  
mysql 8.0  
JDK 1.8

# 2.compile and package
mvn compile  
mvn package

# 3.Incremental Deployment

            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <mainClass>com.ether.data.EtherApplication</mainClass>
                    <jvmArguments>-Dfile.encoding=UTF-8</jvmArguments>
                    <layout>ZIP</layout>
                    <includes>
                        <include>
                            <groupId>non-exists</groupId>
                            <artifactId>non-exists</artifactId>
                        </include>
                    </includes>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <executions>
                    <execution>
                        <id>copy-dependencies</id>
                        <phase>package</phase>
                        <goals>
                            <goal>copy-dependencies</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>${project.build.directory}/lib</outputDirectory>
                            <excludeTransitive>false</excludeTransitive>
                            <stripVersion>false</stripVersion>
                            <includeScope>runtime</includeScope>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
			
# 4.run

java -Dloader.path=./lib/ -jar ether-data-0.0.1-SNAPSHOT.jar 
