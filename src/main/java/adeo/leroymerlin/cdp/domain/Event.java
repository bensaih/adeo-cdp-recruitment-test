package adeo.leroymerlin.cdp.domain;

import java.util.Set;

public class Event {

	private Long id;

	private String title;

	private String imgUrl;

	private Set<Band> bands;

	private Integer nbStars;

	private String comment;

	public Event(final Long id, final String title, final String imgUrl, final Set<Band> bands, final Integer nbStars,
			final String comment) {
		this.id = id;
		this.title = title;
		this.imgUrl = imgUrl;
		this.bands = bands;
		this.nbStars = nbStars;
		this.comment = comment;
	}

	public Event() {
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

	public Set<Band> getBands() {
		return bands;
	}

	public void setBands(final Set<Band> bands) {
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
