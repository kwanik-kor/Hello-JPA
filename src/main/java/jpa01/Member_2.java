package jpa01;

import javax.persistence.*;

@Entity
public class Member_2 {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;
    private int age;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    // This should be owner cause it contains 'FOREIGN KEY'!!
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    // 연관관계 편의 메소드를 작성해주자!!!
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
