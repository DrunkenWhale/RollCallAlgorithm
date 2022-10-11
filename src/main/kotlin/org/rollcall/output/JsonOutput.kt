package org.rollcall.output

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.file.StandardOpenOption

class JsonOutput(
    val outputJsonFileName: String = "result.json"
) : TextOutput(outputJsonFileName) {

    override fun output(e: Double) {

        val filePath = getFilePath(outputJsonFileName)

        super.outputToFile(
            filePath,
            Json.encodeToString(Res(e, data)).toByteArray(Charsets.UTF_8),
            StandardOpenOption.TRUNCATE_EXISTING
        )
    }

    @kotlinx.serialization.Serializable
    data class Res(val e: Double, val data: List<List<Lesson>>)

}