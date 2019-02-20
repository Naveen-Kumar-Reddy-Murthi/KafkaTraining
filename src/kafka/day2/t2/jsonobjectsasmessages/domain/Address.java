package kafka.day2.t2.jsonobjectsasmessages.domain;

public class Address {

	private String location;
	private String city;
	
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(String location, String city) {
		super();
		this.location = location;
		this.city = city;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Address [location=" + location + ", city=" + city + "]";
	}
	
	

}
