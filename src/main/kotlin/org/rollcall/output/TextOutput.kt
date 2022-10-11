package org.rollcall.output

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

private const val resultStorageDir = "res"

open class TextOutput(
    val outputFileName: String = "result"
) : Output {

    protected val data = mutableListOf<List<Lesson>>()

    override fun write(data: List<Lesson>) {
        this.data.add(data)
    }

    override fun read(): List<List<Lesson>> {
        return this.data
    }

    // only output e value for the draw image
    override fun output(e: Double) {

        val filePath = getFilePath(outputFileName)

        outputToFile(
            filePath = filePath,
            bytes = "$e\n".toByteArray(),
            option = StandardOpenOption.APPEND
        )
    }

    protected fun outputToFile(
        filePath: Path,
        bytes: ByteArray,
        option: StandardOpenOption
    ) {
        createPathIfNotExist(filePath)
        Files.write(
            filePath,
            bytes,
            option
        )
    }



    protected fun createPathIfNotExist(filePath: Path) {
        if (!Files.exists(Path.of(resultStorageDir))) {
            Files.createDirectories(Path.of(resultStorageDir))
        }

        if (!Files.exists(filePath)) {
            Files.createFile(filePath)
        }
    }

    protected fun getFilePath(fileName: String): Path {
        return Path.of(
            resultStorageDir + System.getProperty("file.separator") + fileName
        )
    }


}