package kafka.day2.jaxbobjectsasmessages.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"empId", "empName", "designation", "phones"})
public class Employee {

	private int empId;
	private String empName;
	private String designation;
	private List<String> phones;
	

	public Employee() {
		super();
	}

	public Employee(int empId, String empName, String designation) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.designation = designation;
	}
	
	

	public Employee(int empId, String empName, String designation, List<String> phones) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.designation = designation;
		this.phones = phones;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", designation=" + designation + ", phones="
				+ phones + "]";
	}

}
