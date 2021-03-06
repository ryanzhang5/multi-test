package org.hibernate.hduo;

import java.util.HashSet;
import java.util.Set;

public class Team {
	private Long id;

	private String name;

	private Set<Player> players = new HashSet<Player>();

	public Team() {
	}

	public Team(String name) {
		this.name = name;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
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
