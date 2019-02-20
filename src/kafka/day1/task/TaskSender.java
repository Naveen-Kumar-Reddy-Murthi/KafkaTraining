package kafka.day1.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class TaskSender {

	public static void main(String[] args) {

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

		String topicName = "TopicWith3P";

		for (int i = 1; i <= 3; i++) {

			String key = i == 1 ? "Key-1" : i == 2 ? "Key-2" : "Key-3";

			String value = new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss SSS").format(new Date());
			// System.out.println(message);

			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, key, value);

			producer.send(record);

			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		producer.close();

	}

}
