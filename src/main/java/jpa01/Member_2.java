package jpa01;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    // 일대일 관계 매핑
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    // 다대다 관계 매핑
    // --> 관계 테이블을 Entity로 승격해서 OneToMany ManyToOne 관계를 만들어줌
//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> products = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    // ===========================

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
