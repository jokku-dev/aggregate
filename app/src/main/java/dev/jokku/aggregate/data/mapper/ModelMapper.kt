package dev.jokku.aggregate.data.mapper

// In multimodular projects it's better to make these mappers as extension functions and place them
// in corresponding modules

interface FromRemoteMapper<To> {
    fun asEntity(): To
}

interface FromEntityMapper<To> {
    fun asExternalModel(): To
}

inline fun <To, reified From : FromRemoteMapper<To>> List<From>.mapList(): List<To> =
    map { it.asEntity() }

inline fun <To, reified From : FromEntityMapper<To>> List<From>.mapList(): List<To> =
    map { it.asExternalModel() }