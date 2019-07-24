package net.rafaeltoledo.kotlin

import kotlin.test.Test
import kotlin.test.assertTrue

class SampleTestIos {

    @Test
    fun testHello() {
        assertTrue("iOS" in hello())
    }
}