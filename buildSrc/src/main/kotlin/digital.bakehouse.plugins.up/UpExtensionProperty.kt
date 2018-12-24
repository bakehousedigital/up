package digital.bakehouse.plugins.up

import org.gradle.api.Project
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class UpExtensionProperty<T>(private val project: Project, private val type: Class<T>)
    : ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return project.objects.property(type).get()
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        project.objects.property(type).set(value)
    }
}
