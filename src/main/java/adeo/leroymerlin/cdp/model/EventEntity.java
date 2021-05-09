package adeo.leroymerlin.cdp.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "event")
@Table(name = "event")
public class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	private String imgUrl;

	@OneToMany(fetch = FetchType.EAGER)
	private Set<BandEntity> bands;

	private Integer nbStars;

	private String comment;

	public EventEntity() {
	}

	public EventEntity(final Long id, final String title, final String imgUrl, final Set<BandEntity> bands,
			final Integer nbStars, final String comment) {
		this.id = id;
		this.title = title;
		this.imgUrl = imgUrl;
		this.bands = bands;
		this.nbStars = nbStars;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(final String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<BandEntity> getBands() {
		return bands;
	}

	public void setBands(final Set<BandEntity> bands) {
		this.bands = bands;
	}

	public Integer getNbStars() {
		return nbStars;
	}

	public void setNbStars(final Integer nbStars) {
		this.nbStars = nbStars;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}
}
