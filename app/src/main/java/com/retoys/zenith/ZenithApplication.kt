package com.retoys.zenith

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.KoinApplication
import org.koin.core.annotation.Module

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

@Module
@Configuration
@ComponentScan("com.retoys.zenith.data")
class DataModule

@Module
@Configuration
@ComponentScan("com.retoys.zenith.domain")
class DomainModule

@KoinApplication
class ZenithApplication

startKoin<ZenithApplication> {
    androidLogger()
    androidContext(this@ZenithApplication)
}
