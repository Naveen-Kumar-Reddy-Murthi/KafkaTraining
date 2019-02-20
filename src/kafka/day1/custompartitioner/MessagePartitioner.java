package kafka.day1.custompartitioner;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class MessagePartitioner implements Partitioner {

	@Override
	public void configure(Map<String, ?> configs) {
		// TODO Auto-generated method stub

	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

		String messageKey = (String) key;

		if (messageKey.equals("key-1")) {
			return 0;
		} else if (messageKey.equals("key-2")) {
			return 1;
		} else if (messageKey.equals("key-3")) {
			return 2;
		}
		else {
			return 3;
		}

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
