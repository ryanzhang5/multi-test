package hibernate.association.unidirection.onetomany;

public class Player {
	private Long id;
	private String name;

	public Player() {
	}

	public Player(String name) {
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

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + "]";
	}

}
