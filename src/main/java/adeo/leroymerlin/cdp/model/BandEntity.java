package adeo.leroymerlin.cdp.model;

import javax.persistence.*;

import java.util.Set;

@Entity(name = "band")
@Table(name = "band")
public class BandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(fetch=FetchType.EAGER)
    private Set<MemberEntity> members;
    
    public BandEntity() {
	}
    
    public BandEntity(final Long id, final String name, final Set<MemberEntity> members) {
		this.id = id;
		this.name = name;
		this.members = members;
	}

	public Set<MemberEntity> getMembers() {
        return members;
    }

    public void setMembers(final Set<MemberEntity> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}
    
}
