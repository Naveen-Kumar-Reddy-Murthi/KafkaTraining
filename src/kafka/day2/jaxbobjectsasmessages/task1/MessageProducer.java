package kafka.day2.jaxbobjectsasmessages.task1;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import kafka.day2.jaxbobjectsasmessages.domain.Employee;

public class MessageProducer {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {

		System.out.println("sender starting");

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.setProperty("value.serializer", "kafka.day2.jaxbobjectsasmessages.serializer.EmployeeSerializer");
		properties.setProperty("partitioner.class",
				"kafka.day2.jaxbobjectsasmessages.task1.EmployeeMessagePartitioner"); // enabling custom
		// paritioning

		KafkaProducer<String, Employee> producer = new KafkaProducer<String, Employee>(properties);
		String topicName = "emp-topic-with4";
		String designation = null;

		for (int i = 1; i <= 4; i++) {

			designation = (i == 1 ? "Accountant" : i == 2 ? "Developer" : i == 3 ? "Architect" : "Tester");

			ProducerRecord<String, Employee> record = new ProducerRecord<String, Employee>(topicName, "employee-"+i,
					new Employee(Integer.parseInt("100"+i), UUID.randomUUID().toString(), designation));
			Future<RecordMetadata> future = producer.send(record);
			try {
				RecordMetadata rmd = future.get();
				System.out.println("message delivered to parition: " + rmd.partition() + " at offset: " + rmd.offset());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Employee message object sent");
		producer.close();

	}

}
