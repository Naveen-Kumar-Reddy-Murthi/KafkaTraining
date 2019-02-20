package kafka.day2.jaxbobjectsasmessages.domain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class MarshalEmployee {

	public static void main(String[] args) throws JAXBException, IOException {
		
		JAXBContext context = JAXBContext.newInstance(Employee.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		Employee emp = new Employee(2, "Jesse", "SSE", new ArrayList<>(Arrays.asList("123", "456")));
		
		FileOutputStream fout = new FileOutputStream("employee.xml");
		
		marshaller.marshal(emp, fout);
		fout.close();
		
		

	}

}
