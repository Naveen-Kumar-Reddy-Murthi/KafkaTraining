package kafka.day2.jaxbobjectsasmessages.deserializer;

import java.io.StringReader;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.kafka.common.serialization.Deserializer;

import kafka.day2.jaxbobjectsasmessages.domain.Employee;

public class EmployeeDeserializer implements Deserializer<Employee> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public Employee deserialize(String topic, byte[] data) {

		String xmlContent = new String(data);
		Employee emp = null;

		try {
			JAXBContext context = JAXBContext.newInstance(Employee.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader reader = new StringReader(xmlContent);
			emp = (Employee) unmarshaller.unmarshal(reader);

		} catch (JAXBException e) {
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
