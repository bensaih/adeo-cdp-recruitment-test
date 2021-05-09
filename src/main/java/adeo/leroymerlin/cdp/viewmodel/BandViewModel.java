package adeo.leroymerlin.cdp.viewmodel;

import java.util.Set;

public class BandViewModel {

	private Long id;

	private String name;

	private Set<MemberViewModel> members;

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

	public Set<MemberViewModel> getMembers() {
		return members;
	}

	public void setMembers(final Set<MemberViewModel> members) {
		this.members = members;
	}
}
