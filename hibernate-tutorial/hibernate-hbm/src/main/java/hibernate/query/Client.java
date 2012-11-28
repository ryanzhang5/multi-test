package hibernate.query;

public class Client {
	private Long id;
	private String name;
	private int age;

	public Client() {
	}

	public Client(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
