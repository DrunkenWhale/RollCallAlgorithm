package org.rollcall.alg

internal data class Node<T>(
    val data: List<T>,
    val label: Int
)