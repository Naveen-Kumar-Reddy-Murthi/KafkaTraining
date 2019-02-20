package kafka.day1.custompartitioner;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class MessageConsumerWithCustomerPartitioner {

	public static void main(String[] args) {
		
		String groupId = "group1";
		
		System.out.println("Consumer groupId = "+groupId);

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.setProperty("group.id", groupId);
		
		//groupid property is meant to share the messages between same 

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

		List<TopicPartition> partitionList = new ArrayList<>();
		
		int partitionNumber = Integer.parseInt(args[0]);
		
		partitionList.add(new TopicPartition("mytopic4", partitionNumber));

		consumer.assign(partitionList);

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));

			records.forEach(record -> {
				System.out.println("R|" + record.key() + " : " + record.value() + " | pid =" + record.partition());

			});

		}

	}

}
