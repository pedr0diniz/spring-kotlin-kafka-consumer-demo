package br.com.zinid.kafkaconsumer

data class Car(
    val id: String,
    val model: String,
    val color: String
) {

    fun toDTO() : CarDTO {
        return CarDTO(
            model = model,
            color = color
        )
    }

}