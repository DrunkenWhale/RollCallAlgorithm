package org.rollcall.alg

import org.rollcall.output.Lesson

fun calculateE(realRollCallList: List<List<Lesson>>, schemaList: List<List<Lesson>>): Double {
    assert(schemaList.size == realRollCallList.size)
    assert(schemaList[0].size == realRollCallList[0].size)
    assert(schemaList[0][0].size == realRollCallList[0][0].size)

    val validRollCall = schemaList.flatten().flatten()
        .zip(realRollCallList.flatten().flatten())
        .filter { it.first != 0 && it.first == it.second }
        .size

    val rollCall = schemaList.flatten().flatten().sum()

    return validRollCall.toDouble() / rollCall
}