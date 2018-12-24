package digital.bakehouse.plugins.up

import org.gradle.api.Plugin
import org.gradle.api.Project

open class UpPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val extension = target.extensions.create("up", UpExtension::class.java, target.objects)

        target.afterEvaluate {
            val upApkTask = target.tasks.create("upApk", UpApkTask::class.java)
            upApkTask.group = "Up - Versioning, Building and Publishing"
            upApkTask.description = "Upload raw APK file"
            upApkTask.message = "${extension.message} ${extension.playDestination?.username}"
        }
    }
}