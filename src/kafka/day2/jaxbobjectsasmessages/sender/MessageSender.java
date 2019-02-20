package kafka.day2.jaxbobjectsasmessages.sender;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import kafka.day2.jaxbobjectsasmessages.domain.Employee;

public class MessageSender {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty("value.serializer", "kafka.day2.jaxbobjectsasmessages.serializer.EmployeeSerializer");

		KafkaProducer<String, Employee> producer = new KafkaProducer<String, Employee>(props);
		String topicName = "empTopic";
		ProducerRecord<String, Employee> record = new ProducerRecord<String, Employee>(topicName, "employee-1",
				new Employee(43332, "Naveen Reddy", "Accountant"));
		Future<RecordMetadata> future = producer.send(record);
		try {
			RecordMetadata rmd = future.get();
			System.out.println("message delivered to parition: " + rmd.partition() + " at offset: " + rmd.offset());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Employee message object sent");
		producer.close();
	}

}
