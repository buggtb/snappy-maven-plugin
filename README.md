# Snappy Maven Plugin

## Overview

This maven plugin will allow your existing maven build to generate snapcraft yaml files and build a snapcraft package.

Snap packages are a universal file packaging format for Linux binaries and allow for secure cross platform deployments of different technologies. To create a package you need to create a snapcraft file which in turn will generate your package. To simplify the usage by Java developers who use Maven, this plugin allows you to generate the definition during your maven compilation making it an integral part of the build process.

## Usage

The plugin currently isn't in maven central, so you need to define a 3rd party repo.

The plugin itself is an XML representation of all the tags I could find. More work has to be done to clean this up, but it is functional. See TODO for more info about missing pieces.
 
    <pluginRepositories>
        <pluginRepository>
          <id>alabs.release</id>
          <url>http://repo.meteorite.bi/content/repositories/alabs-release-local</url>
        </pluginRepository>
     </pluginRepositories>

    ....

            <plugin>
                <groupId>uk.co.spicule</groupId>
                <artifactId>snaps-maven-plugin</artifactId>
                <version>0.1</version>
                <configuration>
                    <name>${pom.name}</name>
                    <version>${pom.version}</version>
                    <summary>Pentaho Data Integration Community Edition</summary>
                    <description>Pentaho Data Integration Community Edition</description>
                    <confinement>devmode</confinement>
                    <apps>
                        <pentahodataintegration>
                            <name>pentahodataintegration</name>
                            <command>bin/launcher</command>
                            <plugs>
                                <plug>home</plug>
                                <plug>x11</plug>
                                <plug>network</plug>
                                <plug>network-bind</plug>
                            </plugs>
                        </pentahodataintegration>
                    </apps>
                    <parts>
                        <pentahodataintegration>
                            <plugin>copy</plugin>
                            <source>${build.directory}/pdi.zip</source>
                            <files>
                                <file>
                                    <from>*</from>
                                    <to>.</to>
                                </file>
                            </files>
                            <stagepackages>
                                <stagepackage>openjdk-8-jdk</stagepackage>
                            </stagepackages>
                        </pentahodataintegration>
                        <launcher>
                            <plugin>copy</plugin>
                            <files>
                                <file>
                                    <from>launcher</from>
                                    <to>bin/launcher</to>
                                </file>
                            </files>
                        </launcher>
                    </parts>
                </configuration>
                <executions>
                    <execution>
                        <id>compile</id>
                        <goals>
                            <goal>snaps</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

## Todo

Currently the plugin supports all the tags I could find in the snappy playpen. It makes sense to also allow for a crafted yaml entry to be used instead so that you could override the supported tags and add custom entries.

I will also add the functionality to be able to push snap packages to repositories so users can compile, test and deploy all in the same maven build.

## Contact

Tom Barber - tom@analytical-labs.com
