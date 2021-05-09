package adeo.leroymerlin.cdp.viewmodel;

import java.util.List;

public class EventViewModel{

	private Long id;

	private String title;

	private String imgUrl;

	private List<BandViewModel> bands;

	private Integer nbStars;

	private String comment;

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

	public List<BandViewModel> getBands() {
		return bands;
	}

	public void setBands(final List<BandViewModel> bands) {
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
