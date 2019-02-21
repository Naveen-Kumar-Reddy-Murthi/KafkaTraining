package kafka.day4.t1.authentication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class MessageSender {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {

		System.out.println("sender starting");

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//		properties.setProperty("partitioner.class", "kafka.day1.custompartitioner.MessagePartitioner"); // enabling custom
																										// paritioning
		properties.setProperty("security.protocol", "SASL_PLAINTEXT");
		properties.setProperty("sasl.mechanism","PLAIN");

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

		String topicName = "mytopic4";

		String key = "";

		for (int i = 1; i <= 4; i++) {

			key = (i == 1 ? "key-1" : i == 2 ? "key-2" : i == 3 ? "key-3" : "key-4");

			String value = new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss SSS").format(new Date());

			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, key, value+" "+i);

			//producer.send(record); //fire and forget kind of sending
			
			/*//Asynchronous sending start
			// In this kind of sending, producer won't send the next message
			// until it recieves meta data info in the form of future object
			Future<RecordMetadata> future=producer.send(record);

			try {
				RecordMetadata rmd=future.get();
				System.out.println("parition: "+rmd.partition()+" at offset: "+rmd.offset());
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Asynchronous sending end
*/			
			
			//Synchronous sending
			// here producer will keep sending the messages regardless of 
			// whether it received acknowledgement from kafka topic or not
			
			producer.send(record, new Callback() {
				
				@Override
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					
					if(exception == null) {// exception is null means message wasn't delivered successfully
					System.out.println("parition: "+metadata.partition()+" at offset: "+metadata.offset());
					}
				}
			});
			
			Thread.currentThread().sleep(1000);

		}
		System.out.println("sent");
		producer.close();
	
	}

}
