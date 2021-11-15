package com.example.dummyapp.ui.utils.viewBindings

import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Delegate Property to simplify init of activity view bindings
 * @author https://proandroiddev.com/viewbinding-with-kotlin-property-delegate-c907682e24c9
 * @sample val binding by activityViewBinding(MyActivity::inflate)
 */
inline fun <reified T : ViewBinding> AppCompatActivity.activityViewBinding(noinline initializer: (LayoutInflater) -> T) =
        ActivityViewBindingPropertyDelegate(this, initializer)

class ActivityViewBindingPropertyDelegate<T : ViewBinding>(
    private val activity: AppCompatActivity,
    private val initializer: (LayoutInflater) -> T
) : ReadOnlyProperty<AppCompatActivity, T>, LifecycleEventObserver {
    
    private var _value: T? = null
    
    init {
        activity.lifecycle.addObserver(this)
    }
    
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_CREATE) {
            onCreate()
        }
    }

    private fun onCreate() {
        if (_value == null) {
            _value = initializer(activity.layoutInflater)
        }
        activity.setContentView(_value?.root!!)
        activity.lifecycle.removeObserver(this)
    }
    
    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        if (_value == null) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw IllegalThreadStateException("This cannot be called from other threads. It should be on the main thread only.")
            }
            
            _value = initializer(thisRef.layoutInflater)
        }
        return _value!!
    }
}