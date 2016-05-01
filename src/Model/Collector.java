package Model;

public class Collector {
	private String name;
	private int collector_id;
	private int age;
	public Collector(String name, int collector_id, int age) {
		this.name = name;
		this.collector_id = collector_id;
		this.age = age;
	}

	public Collector(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCollector_id() {
		return collector_id;
	}
	public void setCollector_id(int collector_id) {
		this.collector_id = collector_id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
