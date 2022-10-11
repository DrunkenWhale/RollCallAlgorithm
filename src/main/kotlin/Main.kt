import org.rollcall.alg.*
import org.rollcall.core.extractSample
import org.rollcall.input.DsvInput
import org.rollcall.output.ConsoleOutput
import org.rollcall.output.JsonOutput
import org.rollcall.output.Output
import org.rollcall.output.TextOutput
import java.nio.file.Files
import java.nio.file.Path

fun main() {

    val rollCallFunction: Map<String, RollCallSchema> = mapOf(
        Pair("onlyRollCallFrequentlyAbsentStudents", onlyRollCallFrequentlyAbsentStudents),
        Pair("rollCallStudentsPartlyBasedOnGpa", rollCallStudentsPartlyBasedOnGpa),
        Pair("knn", knn)
    )

    rollCallFunction.forEach { (fName, f) ->
        reGenerateDataSet()
        walk(f, ConsoleOutput())
        walk(f, JsonOutput("$fName.json"))
        walk(f, TextOutput(fName))
    }

}

fun reGenerateDataSet() {
    // 这里是一个异步调用
    // 如果不阻塞到生成完毕
    // 会导致只读到前60%的数据
    // (只有60%也能跑 这是不是说明我写的很合理捏)
    // 正确率计算没有可信度
    Runtime.getRuntime().exec("./executable/gen").waitFor()
//    Thread.sleep(1000)
}

fun walk(f: RollCallSchema, output: Output) {
    val rollCallNumber = 7
    val pathString = "data"
    val dsvInputList = Files.list(Path.of(pathString)).map { path ->
        DsvInput(path)
    }.toList()!!

    dsvInputList.forEach { extractSample(f, it, output, rollCallNumber) }

    val rollCallScheme = output.read()

    val inputDataList = dsvInputList.map { it.read().first }

    val e = calculateE(inputDataList, rollCallScheme)

    output.output(e)
}
