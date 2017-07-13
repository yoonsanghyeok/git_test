package sesoc.global.test;

public class Friend {
	private String name;
	private int age;
	private String phone;
	/**
	 * @param name
	 * @param phone
	 * @param age
	 */
	public Friend(String name, String phone, int age) {
		super();
		this.name = name;
		this.phone = phone;
		this.age = age;
	}
	/**
	 * 
	 */
	public Friend() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Friend [name=" + name + ", phone=" + phone + ", age=" + age + "]";
	}
	
	
}
