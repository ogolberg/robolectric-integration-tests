package com.example.testapp

import org.junit.runners.model.FrameworkMethod
import org.robolectric.RobolectricTestRunner
import org.robolectric.internal.bytecode.InstrumentationConfiguration
import org.robolectric.util.inject.Injector

/**
 * Fixes `class Foo cannot be cast to class Foo` when Mockking.
 */
open class PatchedRobolectricTestRunner : RobolectricTestRunner {
    constructor(testClass: Class<*>) : super(testClass)
    constructor(testClass: Class<*>, injector: Injector) : super(testClass, injector)

    override fun createClassLoaderConfig(method: FrameworkMethod?) =
        InstrumentationConfiguration.Builder(super.createClassLoaderConfig(method))
            .apply {
                // https://github.com/robolectric/robolectric/issues/5994
                packagesToNotAcquire.remove("io.mockk.")
                packagesToNotAcquire.add("io.mockk.proxy.")
            }
            .build()
}
