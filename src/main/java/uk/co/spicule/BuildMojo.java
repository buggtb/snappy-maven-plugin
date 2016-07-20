/*
 * Copyright (c) 2014 Spotify AB.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package uk.co.spicule;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Used to build docker images.
 */
@Mojo(name = "snaps")
public class BuildMojo extends AbstractMojo {

  @Parameter(property = "project.build.directory")
  protected String buildDirectory;


  @Parameter( property = "snaps.name" )
  private String name;

  @Parameter( property = "snaps.version" )
  private String version;

  @Parameter( property = "snaps.summary" )
  private String summary;

  @Parameter( property = "snaps.description" )
  private String description;

  @Parameter( property = "snaps.confinement" )
  private String confinement;


  @Parameter(property = "snaps.apps")
  private Map<String,App> apps;

  @Parameter(property = "snaps.parts")
  private Map<String,Part> parts;

  private final String defaultProfile;

  public BuildMojo() {
    this(null);
  }

  public BuildMojo(final String defaultProfile) {
    this.defaultProfile = defaultProfile;
  }



  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    DumperOptions options = new DumperOptions();
    options.setLineBreak(DumperOptions.LineBreak.UNIX);
    options.setExplicitStart(false);
    options.setIndent(4);
    options.setPrettyFlow(true);
    Map<String,Object> appss = new HashMap<>();

    for (Map.Entry<String, App> entry : apps.entrySet())
    {
      Map<String, Object> appentry = new HashMap<>();
      appentry.put("command", entry.getValue().getCommand());
      appentry.put("plugs", entry.getValue().getPlugs().toArray());
      appss.put(entry.getKey(), appentry);

    }
    Map<String, Object> m = new HashMap<>();
    m.put("name", name);
    m.put("version", version);
    m.put("summary", summary);
    m.put("description", description);
    m.put("confinement", confinement);

    m.put("apps", appss);

    Map<String,Object> partss= new HashMap<>();

    for (Map.Entry<String, Part> entry : parts.entrySet()){
      Map<String,Object> partentry = new HashMap<>();
      if(entry.getValue().getPlugin()!=null) {
        partentry.put("plugin", entry.getValue().getPlugin());
      }
      if(entry.getValue().getSource()!=null) {
        partentry.put("source", entry.getValue().getSource());
      }

      Map<String,String> files = new HashMap<>();
      if(entry.getValue().getFiles()!=null && entry.getValue().getFiles().size()>0) {
        for (File f : entry.getValue().getFiles()) {
          files.put(f.getFrom(), f.getTo());
        }
        partentry.put("files", files);
      }

      if(entry.getValue().getStagepackages()!=null && entry.getValue().getStagepackages().size()>0) {
        partentry.put("stage-packages", entry.getValue().getStagepackages().toArray());
      }
      partss.put(entry.getKey(), partentry);
    }
    m.put("parts", partss);
    Yaml yaml = new Yaml(options);
    FileWriter fw = null;
    try {
      Files.createDirectories(Paths.get(buildDirectory+ "/snapcraft"));
      fw = new FileWriter(buildDirectory+"/snapcraft/snapcraft.yaml");

    } catch (IOException e) {
      e.printStackTrace();
    }
    yaml.dump(m, fw);

    try {
      if (fw != null) {
        fw.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    Runtime runtime = Runtime.getRuntime();
    Process process = null;
    try {

      process = runtime.exec("/usr/bin/snapcraft", null, new java.io.File(buildDirectory+"/snapcraft"));

    InputStream is = process.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    String line;

    System.out.printf("Output of running %s is:",
            "/usr/bin/snapcraft");

    while ((line = br.readLine()) != null) {
      System.out.println(line);
    }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}