package org.rollcall.output

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption


private const val resultStorageDir = "res"

@kotlinx.serialization.Serializable
data class Res(val e: Double, val data: List<List<Lesson>>)

class JsonOutput(
    val outputFileName: String = "result.json"
) : Output {

    private val data = mutableListOf<List<Lesson>>()

    override fun write(data: List<Lesson>) {
        this.data.add(data)
    }

    override fun read(): List<List<Lesson>> {
        return this.data
    }

    override fun output(e: Double) {
        if (!Files.exists(Path.of(resultStorageDir))) {
            Files.createDirectories(Path.of(resultStorageDir))
        }
        Files.writeString(
            Path.of(resultStorageDir + System.getProperty("file.separator") + outputFileName),
            Json.encodeToString(Res(e, data)),
            StandardOpenOption.CREATE
        )
    }
}