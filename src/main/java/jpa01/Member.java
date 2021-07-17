package jpa01;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Entity Annotation을 붙임으로써 JPA가 사용해야할 모델이란 것을 인지할 수 있게 됨
 *  1) 기본 생성장 필수
 *  2) final 클래스, enum, interface, inner 클래스 사용X
 *  3) 저장할 필드에 final 사용 X
 *
 */
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    private Long id;
    private String name;
    private int age;

    public Member() {}

    public Member(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
