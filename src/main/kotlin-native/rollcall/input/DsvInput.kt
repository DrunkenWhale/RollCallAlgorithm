package org.rollcall.input

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.fclose
import platform.posix.fgets
import platform.posix.fopen


class DsvInput(
    val pathString: String,
    val sep: String = ","
) : Input {

    override fun read(): Pair<List<Lesson>, List<Double>> {
        val lines = readFile(pathString).split("\n")
        val gpaList = lines[0]
            .split(sep)
            .map { it.toDouble() }

        // last line will exist null
        val lessonList = lines.subList(1, lines.size-1)
            .map { line ->
                line.split(sep)
                    .map { str -> str.toInt() }
            }
        return Pair(lessonList, gpaList)
    }

}

private fun readFile(filePath: String): String {
    val returnBuffer = StringBuilder()
    val file = fopen(filePath, "r") ?: throw IllegalArgumentException("Cannot open input file $filePath")

    try {
        memScoped {
            val readBufferLength = 64 * 1024
            val buffer = allocArray<ByteVar>(readBufferLength)
            var line = fgets(buffer, readBufferLength, file)?.toKString()
            while (line != null) {
                returnBuffer.append(line)
                line = fgets(buffer, readBufferLength, file)?.toKString()
            }
        }
    } finally {
        fclose(file)
    }

    return returnBuffer.toString()
}