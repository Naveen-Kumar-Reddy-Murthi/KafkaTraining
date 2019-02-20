package kafka.day2.jaxbobjectsasmessages.task1;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import kafka.day2.jaxbobjectsasmessages.domain.Employee;

public class EmployeeMessagePartitioner implements Partitioner {

	@Override
	public void configure(Map<String, ?> configs) {
		// TODO Auto-generated method stub

	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

		Employee emp = (Employee) value;
		
		String designation = emp.getDesignation();

		if (designation.equalsIgnoreCase("Accountant")) {
			return 0;
		} else if (designation.equalsIgnoreCase("Developer")) {
			return 1;
		} else if (designation.equalsIgnoreCase("Architect")) {
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
