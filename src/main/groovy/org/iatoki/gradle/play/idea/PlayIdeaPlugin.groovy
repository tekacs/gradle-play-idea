package org.iatoki.gradle.play.idea

import org.gradle.api.Plugin
import org.gradle.api.Project

class PlayIdeaPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.idea.module {
            sourceDirs += project.file('app')
            testSourceDirs += project.file('test')

            project.file("${project.buildDir}/src/play/binary").listFiles().each {
                if (it.name.endsWith('ScalaSources')) {
                    sourceDirs += it
                    generatedSourceDirs += it
                }
            }

            outputDir = project.file("${project.buildDir}/playBinary/classes")
            testOutputDir = project.file("${project.buildDir}/playBinary/testClasses")

            scopes.COMPILE = scopes.COMPILE ?: [plus: [], minus: []]
            scopes.RUNTIME = scopes.RUNTIME ?: [plus: [], minus: []]
            scopes.TEST = scopes.TEST ?: [plus: [], minus: []]
            
            scopes.COMPILE.plus += [ project.configurations.play ]
            scopes.RUNTIME.plus += [ project.configurations.playRun ]
            scopes.RUNTIME.minus += [ project.configurations.play ]
            scopes.TEST.plus += [ project.configurations.playTest ]
            scopes.TEST.minus += [ project.configurations.playRun ]

            sourceDirs += project.file('conf')
            testSourceDirs += project.file('test/resources')

            iml.withXml {
                def sourceFolder = it.asNode().component.content.sourceFolder

                def resourcesDir = sourceFolder.find { it.@url == 'file://$MODULE_DIR$/conf' }
                if (resourcesDir) {
                    resourcesDir.attributes().remove('isSource')
                    resourcesDir.attributes().put('type', 'java-resource')
                }

                def testResourcesDir = sourceFolder.find {it.@url == 'file://$MODULE_DIR$/test/resources' }
                if (testResourcesDir) {
                    testResourcesDir.attributes().remove('isTestSource')
                    testResourcesDir.attributes().put('type', 'java-test-resource')
                }
            }
        }
    }
}
