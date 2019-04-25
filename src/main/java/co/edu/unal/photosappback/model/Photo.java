package co.edu.unal.photosappback.model;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {

	private int id;
	private String name;
	private String url;
	private int albumId;
	
	public Photo() {}
	
	public Photo(String name, String url, int albumId) {
		this.name = name;
		this.url = url;
		this.albumId = albumId;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

	@Column(name = "url", nullable = false)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "album_id", nullable = false)
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", album_id='" + albumId + '\'' +
                '}';
    }
}