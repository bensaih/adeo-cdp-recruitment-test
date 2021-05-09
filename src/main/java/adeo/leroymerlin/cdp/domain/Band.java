package adeo.leroymerlin.cdp.domain;

import java.util.Set;

public class Band {

	private Long id;

	private String name;

	private Set<Member> members;

	public Band(final Long id, final String name, final Set<Member> members) {
		this.id = id;
		this.name = name;
		this.members = members;
	}

	public Band() {
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

	public Set<Member> getMembers() {
		return members;
	}

	public void setMembers(final Set<Member> members) {
		this.members = members;
	}
}
