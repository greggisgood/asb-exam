package nz.co.test.transactions.data.di

import android.app.Activity
import androidx.core.app.AppComponentFactory
import javax.inject.Inject
import javax.inject.Provider

class DaggerAppComponentFactory : AppComponentFactory() {

    private val component = DaggerAppComponent.create()

    init {
        component.inject(this)
    }

    @Inject
    lateinit var map: Map<Class<out Activity>, @JvmSuppressWildcards Provider<Activity>>
}