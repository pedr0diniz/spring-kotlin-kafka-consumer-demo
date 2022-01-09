package br.com.zinid.kafkaconsumer.consumer

import br.com.zinid.kafkaconsumer.Car
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CarConsumer {

    @Value("\${spring.kafka.group-id}")
    lateinit var groupId: String

    @Value("\${topic.name}")
    lateinit var topic: String

    val logger = LoggerFactory.getLogger(this.javaClass)

    @KafkaListener(topics = ["\${topic.name}"],
        groupId = "\${spring.kafka.group-id}",
        containerFactory = "carKafkaListenerContainerFactory")
    fun listenCarTopic(record: ConsumerRecord<String, Car>) {
        logger.info("Message received at partition ${record.partition()}: ${record.value()}")
    }

}