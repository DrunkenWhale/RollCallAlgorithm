package org.rollcall.gen

internal data class Node<T>(
    val data: List<T>,
    val label: Int
)