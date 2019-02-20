package kafka.day2.t2.jsonobjectsasmessages.deserializer;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import kafka.day2.t2.jsonobjectsasmessages.domain.Employee;

public class EmployeeDeSerializer implements Deserializer<Employee> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee deserialize(String topic, byte[] data) {
		
		ObjectMapper mapper = new ObjectMapper();
		Employee emp = null;
	
		try {
			 emp = mapper.readValue(data, Employee.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return emp;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
