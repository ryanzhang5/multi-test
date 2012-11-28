package hibernate.association.unidirection.onetoone;

public class Address {
	private long id;
	private String street;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + "]";
	}

}
