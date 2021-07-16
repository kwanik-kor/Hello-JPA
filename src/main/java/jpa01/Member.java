package jpa01;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Entity Annotation을 붙임으로써 JPA가 사용해야할 모델이란 것을 인지할 수 있게 됨
 */
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    private Long id;
    private String name;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
