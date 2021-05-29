package com.buchi.fullentry

import com.buchi.fullentry.utilities.MockResponseFileReader
import junit.framework.Assert.assertEquals
import org.junit.Test

class TestMockResponseFileReader{
    @Test
    fun readSimpleFile(){
        val reader = MockResponseFileReader("test.json")
        assertEquals(reader.content, "success")
    }
}