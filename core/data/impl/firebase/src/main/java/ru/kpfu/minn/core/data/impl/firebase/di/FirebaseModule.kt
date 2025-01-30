package ru.kpfu.minn.core.data.impl.firebase.di

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides


@Module(includes = [FirebaseBindsModule::class])
class FirebaseModule {

    @Provides
    fun provideFirebaseAuth() = Firebase.auth

}