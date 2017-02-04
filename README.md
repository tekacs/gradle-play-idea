# gradle-play-idea

[![GitHub Release](https://img.shields.io/github/release/ia-toki/gradle-play-idea.svg)](https://github.com/ia-toki/gradle-play-idea)

Gradle plugin for setting up IntelliJ IDEA project for Play framework projects.

## Usage

1. Apply the [Play framework plugin](https://docs.gradle.org/current/userguide/play_plugin.html).
1. Apply the [IDEA plugin](https://docs.gradle.org/current/userguide/idea_plugin.html).
1. Apply this plugin by adding the following to your `build.gradle`:

   ```
   buildscript {
       repositories {
           jcenter()
       }
 
       dependencies {
           classpath 'org.iatoki:gradle-play-idea:0.4.0'
       }
   }
 
   apply plugin: 'org.iatoki.play-idea'
   ```
   
## Features

This plugin will:

1. Mark `app` directory as source directory.
1. Mark `conf` directory as resources directory.
1. Mark `test` directory as test directory.
1. Mark `test/resources` directory as test resources directory.
1. Mark generated Scala sources from Play routes and Twirl templates as generated source directories.
1. Add `play`, `playRun`, and `playTest` configurations to the appropriate dependency scopes.
