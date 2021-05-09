package adeo.leroymerlin.cdp.controller.requestparameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateEventParameter {

	private Integer nbStars;

	private String comment;
	
	@JsonCreator
	public UpdateEventParameter(
			@JsonProperty("nbStars") Integer nbStars,
			@JsonProperty("comment") String comment) {
		this.nbStars = nbStars;
		this.comment = comment;
	}

	public Integer getNbStars() {
		return nbStars;
	}

	public String getComment() {
		return comment;
	}

}
