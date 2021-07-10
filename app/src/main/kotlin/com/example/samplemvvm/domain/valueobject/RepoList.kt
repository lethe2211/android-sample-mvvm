package com.example.samplemvvm.domain.valueobject

data class RepoList(
    private val listData: List<Element>
) {

    data class Element(
        val title: String,
        val description: String
    )

    operator fun get(i: Int): Element {
        return listData[i]
    }

    fun size(): Int {
        return listData.size
    }
}