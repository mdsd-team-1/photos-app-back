package co.edu.unal.photosappback.model;

import javax.persistence.*;

@Entity
@Table(name = "album")
public class Album {

	private int id;
	private String name;
	private int userId;

	public Album() {}

	public Album(String name, int userId) {
		this.name = name;
		this.userId = userId;
	}

	@Id
	@Column(unique = true, updatable = false, name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "user_id", nullable = false)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Album{" +
				"id=" + id +
				", name='" + name + '\'' +
				", user_id='" + userId + '\'' +
				'}';
	}
}