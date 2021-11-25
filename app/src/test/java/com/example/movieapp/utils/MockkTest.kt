package com.example.movieapp.utils

import io.mockk.MockKAnnotations
import org.junit.Before

open class MockkTest {

    @Before
    fun initMock() {
        MockKAnnotations.init(this)
    }
}
