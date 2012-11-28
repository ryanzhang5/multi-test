package courses.hibernate.collection.primitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class User {
	private Long id;
	private List<String> addressList = new ArrayList<String>();
	private Set<String> addressSet = new HashSet<String>();
	private Map<Integer, String> addressMap = new HashMap<Integer, String>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}

	public Set<String> getAddressSet() {
		return addressSet;
	}

	public void setAddressSet(Set<String> addressSet) {
		this.addressSet = addressSet;
	}

	public Map<Integer, String> getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(Map<Integer, String> addressMap) {
		this.addressMap = addressMap;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", addressList=" + addressList
				+ ", addressSet=" + addressSet + ", addressMap=" + addressMap
				+ "]";
	}

}
