package kafka.day2.t2.jsonobjectsasmessages.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kafka.day2.t2.jsonobjectsasmessages.domain.Employee;

public class EmployeeSerializer implements Serializer<Employee>{

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] serialize(String topic, Employee data) {
		
		ObjectMapper mapper = new ObjectMapper();
		byte[] byteArr = null;
		try {
				String jsonContent = mapper.writeValueAsString(data);
				byteArr = jsonContent.getBytes();
		}
		catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return byteArr;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
