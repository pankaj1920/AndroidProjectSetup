package com.applaunch.appbase.utils_base

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val mPending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        if (hasActiveObservers()) {
            Print.log("Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner) { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }

    }

    override fun postValue(value: T?) {
        mPending.set(true)
        super.postValue(value)
    }

    override fun setValue(value: T) {
        mPending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        postValue(null)
    }
}