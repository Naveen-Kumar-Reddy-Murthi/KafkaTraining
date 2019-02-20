package kafka.day3.t1.cluster.sender;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


public class MessageProducer {

	public static void main(String[] args) {// TODO Auto-generated method stub

        Properties props=new Properties();
        props.setProperty("bootstrap.servers", "localhost:9091,localhost:9092,localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
        KafkaProducer<String, String> producer=new KafkaProducer<String, String>(props);
        String topicName="TopicWith4P3R";
        
        for(int i=1;i<=10;i++){
        ProducerRecord<String, String> record=
                new ProducerRecord<String, String>(topicName, "message-"+i,"This is a test message "+i+1000);
        producer.send(record, new Callback() {
            
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                // TODO Auto-generated method stub
                
                if(exception==null){
                System.out.println("message delivered to partition: "+metadata.partition()+" at offset: "+metadata.offset());
                }
                else{
                    exception.printStackTrace();
                }
            }
        });
        }
        System.out.println("messages sent");
        producer.close();
        }

}
