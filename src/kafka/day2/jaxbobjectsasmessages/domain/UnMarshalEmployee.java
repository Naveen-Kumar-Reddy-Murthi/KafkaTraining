package kafka.day2.jaxbobjectsasmessages.domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class UnMarshalEmployee {

	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		
		JAXBContext context = JAXBContext.newInstance(Employee.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		FileInputStream fis = new FileInputStream("employee.xml");
		Employee emp = (Employee) unmarshaller.unmarshal(fis);
		
		System.out.println("Unmarshalled emp "+ emp.toString());

	}

}
