package com.james.hayward.artinstituteofchicago.data.mappers

interface Mapper<in InputModel, out OutputModel> {

    fun toDomain(value: InputModel): OutputModel
}