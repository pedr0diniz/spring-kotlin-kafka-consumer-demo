package br.com.zinid.kafkaconsumer.config

import br.com.zinid.kafkaconsumer.Car
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConsumerConfig {

    @Value("\${spring.kafka.bootstrap-servers}")
    lateinit var bootstrapAddress: String

    @Value("\${spring.kafka.group-id}")
    lateinit var groupId: String

    @Bean
    fun carConsumerFactory(): DefaultKafkaConsumerFactory<String, Car> {
        val configProps = mutableMapOf<String, Any>()
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress)
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer::class.java)
        return DefaultKafkaConsumerFactory(
            configProps,
            StringDeserializer(),
            JsonDeserializer(Car::class.java, false))
    }

    @Bean
    fun carKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Car> {

        return ConcurrentKafkaListenerContainerFactory<String, Car>().apply {
            this.consumerFactory = carConsumerFactory()
        }

    }

}