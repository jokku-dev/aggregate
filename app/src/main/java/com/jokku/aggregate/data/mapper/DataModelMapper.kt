package com.jokku.aggregate.data.mapper

interface DataModelMapper<To> {
    fun map() : To
}