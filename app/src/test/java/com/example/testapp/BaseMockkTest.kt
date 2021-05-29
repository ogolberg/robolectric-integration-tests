package com.example.testapp

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

abstract class BaseMockkTest {
    @Test
    fun test() {
        val entity = Entity()

        val repo: Repository = mockk {
            every {
                get(any(), Entity::class.java)
            } returns entity
        }

        assertEquals(entity, repo.get("key", Entity::class.java))
    }
}