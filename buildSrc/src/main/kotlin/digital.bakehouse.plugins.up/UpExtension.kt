package digital.bakehouse.plugins.up

import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory

open class UpExtension(val playDestination: PlayDestination) {
    var message: String? = null

    @javax.inject.Inject
    constructor(objectFactory: ObjectFactory) : this(objectFactory.newInstance(PlayDestination::class.java))

    fun play(action: Action<PlayDestination>) {
        action.execute(playDestination)
    }
}


