package org.rollcall.alg.knn

internal data class Node<T>(
    val data: List<T>,
    val label: Int
)