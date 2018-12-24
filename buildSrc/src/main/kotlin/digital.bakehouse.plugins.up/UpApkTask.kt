package digital.bakehouse.plugins.up

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

open class UpApkTask : DefaultTask() {

    private var destination: String? = null
    var message: String? = null

    @Option(option = "to", description = "Destination for publishing apk file")
    fun setDestination(destination: String) {
        this.destination = destination
    }

    @TaskAction
    fun run() {
        println("Hi $destination with message = $message")
    }


}