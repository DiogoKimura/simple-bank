package com.example.sdk.delegates

import android.view.View
import androidx.annotation.IdRes
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

fun <T : View> viewProvider(@IdRes idRes: Int) = ViewProviderDelegate<T>(idRes)

class ViewProviderDelegate<out T>(@IdRes private val idRes: Int) {
    private var weakView: WeakReference<View>? = null

    private var view: View?
        get() = weakView?.get()
        set(value) {
            weakView = if (value == null) null else WeakReference(value)
        }

    operator fun getValue(thisRef: View, property: KProperty<*>): T {
        return findView(property) {
            thisRef.findViewById(idRes)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private inline fun findView(property: KProperty<*>, crossinline initializer: () -> View?): T {
        view = view ?: initializer.invoke()
        if (view == null)
            throw IllegalStateException("View ID $idRes for ${property.name} not found")
        return view as T
    }
}