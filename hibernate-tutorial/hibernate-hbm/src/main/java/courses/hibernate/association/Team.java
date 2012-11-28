package courses.hibernate.association;

public class Team {
	private Long id;

	private String name;

	public Team() {
	}

	public Team(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Team [id=" + id + ", name=" + name + "]";
	}

}
