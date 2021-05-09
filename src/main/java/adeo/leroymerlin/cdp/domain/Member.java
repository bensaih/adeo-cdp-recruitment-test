package adeo.leroymerlin.cdp.domain;

public class Member {
	
    private Long id;

    private String name;

	public Member(final Long id, final String name) {
		this.id = id;
		this.name = name;
	}

	public Member() {
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
