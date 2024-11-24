package nz.co.test.transactions.data.di

import dagger.Component
import nz.co.test.transactions.data.di.activities.ActivitiesModule
import nz.co.test.transactions.data.di.network.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ActivitiesModule::class
    ]
)
interface AppComponent {
    fun inject(appComponent: DaggerAppComponentFactory)
}