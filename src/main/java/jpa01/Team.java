package jpa01;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    // write field name on opposite side
    // 다대일 인 경우
    @OneToMany(mappedBy = "team")
    private List<Member_2> members = new ArrayList<>();

    // 요렇게 짜기도 함
    public void addMember(Member_2 member) {
        member.setTeam(this);
        members.add(member);
    }

    // 일대다
//    @OneToMany
//    @JoinColumn(name = "TEAM_ID")
//    public List<Member> members = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member_2> getMembers() {
        return members;
    }

    public void setMembers(List<Member_2> members) {
        this.members = members;
    }
}
