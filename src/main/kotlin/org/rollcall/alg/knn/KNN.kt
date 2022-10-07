package org.rollcall.alg.knn

import org.rollcall.output.Label
import org.rollcall.output.Lesson
import kotlin.math.pow


class KNN(private val neighborNumber: Int = 3) {

    private val nodes = mutableListOf<Node<Int>>()

    fun fit(dataList: List<Lesson>, labelList: List<Label>) {
        assert(dataList.size == labelList.size)
        assert(dataList.map { it.size }.toSet().size != 1)
        val nodeList = (labelList.indices).map { columnIndex ->
            dataList.map { line -> line[columnIndex] }
        }.zip(labelList).map { Node(it.first, it.second) }
        nodes.addAll(nodeList)
    }

    /**
     * @return label
     * */
    fun predict(data: List<Int>): Int {
        assert(nodes.size > 0 && data.size == nodes[0].data.size)
        return nodes.sortedBy { euclideanDistance(it.data, data) }.subList(1, 1 + neighborNumber) // ignore itself
            .groupBy { it.label }.map { Pair(it.key, it.value.size) }.maxByOrNull { it.second }!!.first
    }

}

private fun euclideanDistance(node1: List<Int>, node2: List<Int>): Double {
    return node1.zip(node2).map { Pair(it.first.toDouble(), it.second.toDouble()) }
        .sumOf { (it.first - it.second).pow(2) }.pow(0.5)
}