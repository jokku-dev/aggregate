package com.jokku.aggregate.data.mapper

interface DataModelMapper<To> {
    fun map() : To
}

inline fun <To, reified From: DataModelMapper<To>> List<From>.mapList(): List<To> = map { it.map() }