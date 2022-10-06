import org.rollcall.core.extractSample
import org.rollcall.input.DsvInput
import org.rollcall.output.ConsoleOutput
import java.nio.file.Files
import java.nio.file.Path

fun main(args: Array<String>) {
    val pathString = "data"
    Files.list(Path.of(pathString)).forEach { path ->
        extractSample(DsvInput(path), ConsoleOutput())
    }
}