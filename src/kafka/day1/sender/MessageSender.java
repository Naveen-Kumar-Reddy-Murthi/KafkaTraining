package kafka.day1.sender;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MessageSender {

	public static void main(String[] args) {

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

		String topicName = "first-topic";

		for (int i = 0; i < 10; i++) {
			String message = UUID.randomUUID().toString();

			String value = new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss SSS").format(new Date());
			// System.out.println(message);

			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, message, value);

			producer.send(record);
			
			try {
				Thread.currentThread().sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		producer.close();

	}

}
