package kafka.day2.jaxbobjectsasmessages.serializer;

import java.io.StringWriter;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.kafka.common.serialization.Serializer;

import kafka.day2.t2.jsonobjectsasmessages.domain.Employee;


public class EmployeeSerializer implements Serializer<Employee> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] serialize(String topic, Employee data) {

		JAXBContext context = null;
		byte[] byteArr = null;
		try {

			context = JAXBContext.newInstance(Employee.class);
			Marshaller marshaller = context.createMarshaller();
			StringWriter writer = new StringWriter();
			marshaller.marshal(data, writer);
			String xmlContent = writer.toString();
			byteArr = xmlContent.getBytes();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return byteArr;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
