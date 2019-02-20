package kafka.day2.t2.jsonobjectsasmessages.sender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import kafka.day2.t2.jsonobjectsasmessages.domain.Address;
import kafka.day2.t2.jsonobjectsasmessages.domain.Employee;


public class MessageProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty("value.serializer", "kafka.day2.t2.jsonobjectsasmessages.serializer.EmployeeSerializer");

		KafkaProducer<String, Employee> producer = new KafkaProducer<String, Employee>(props);
		String topicName = "emp-topic-with4";
		
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(new Address("Madhapur","Hyderabad"));
		addresses.add(new Address("Prashanth Nagar","Siddipet"));
		ProducerRecord<String, Employee> record = new ProducerRecord<String, Employee>(topicName, "employee-1",
				new Employee(41111, "Naveen Reddy", "Accountant", addresses));
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
