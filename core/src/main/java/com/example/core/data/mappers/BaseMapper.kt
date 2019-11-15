package com.example.core.data.mappers

import java.util.*

abstract class BaseMapper<Input, Output> {

    abstract fun transform(input: Input, position: Int, outputCollection: List<Input>): Output

    fun transform(input: Input): Output {
        return transform(input, -1, Collections.emptyList())
    }

    fun transformAllToOutput(inputCollection: List<Input>?): List<Output> {
        if (inputCollection == null) {
            return Collections.emptyList()
        }
        val outputCollection = arrayListOf()
        for (i in inputCollection.indices) {
            outputCollection.add(transform(inputCollection[i], i, inputCollection))
        }
        return outputCollection
    }
}