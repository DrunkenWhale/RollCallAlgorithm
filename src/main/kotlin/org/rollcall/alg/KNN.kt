package org.rollcall.alg

import kotlin.math.pow


class KNN(private val neighborNumber: Int = 3) {

    private val nodes = mutableListOf<Node<Double>>()

    fun fit(dataList: List<List<Double>>, labelList: List<Int>) {
        assert(dataList.size == labelList.size)
        assert(dataList.map { it.size }.toSet().size != 1)
        val nodeList = dataList.zip(labelList).map { Node(it.first, it.second) }
        nodes.addAll(nodeList)
    }

    /**
     * @return label
     * */
    fun predict(data: List<Double>): Int {
        assert(nodes.size > 0 && data.size == nodes[0].data.size)
        return nodes.sortedBy { euclideanDistance(it.data, data) }
            .subList(0, neighborNumber)
            .groupBy { it.label }
            .map { Pair(it.key, it.value.size) }
            .maxByOrNull { it.second }!!
            .first
    }

    private companion object KNN {

        private fun euclideanDistance(node1: List<Double>, node2: List<Double>): Double {
            return node1.zip(node2)
                .sumOf { (it.first - it.second).pow(2) }
                .pow(0.5)
        }

    }
}

