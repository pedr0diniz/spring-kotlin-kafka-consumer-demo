package br.com.zinid.kafkaconsumer

import java.util.*

data class CarDTO(
    val model: String,
    val color: String
) {

    fun toEntity() : Car {
        return Car(
            id = UUID.randomUUID().toString(),
            model = model,
            color = color
        )
    }

}