import org.rollcall.core.temp
import org.rollcall.input.CsvInput
import org.rollcall.output.ConsoleOutput
import java.nio.file.Files
import java.nio.file.Path

fun main(args: Array<String>) {
    val pathString = "data"
    Files.list(Path.of(pathString)).forEach { path ->
        temp(CsvInput(path), ConsoleOutput())
    }
}