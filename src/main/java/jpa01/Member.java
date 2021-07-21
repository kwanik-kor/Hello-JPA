package jpa01;

import javax.persistence.*;
import java.util.Date;

/**
 * @Entity Annotation을 붙임으로써 JPA가 사용해야할 모델이란 것을 인지할 수 있게 됨
 *  1) 기본 생성장 필수
 *  2) final 클래스, enum, interface, inner 클래스 사용X
 *  3) 저장할 필드에 final 사용 X
 *
 */
@Entity
@Table(name = "MEMBER")
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
public class Member {

    /*
        1) IDENTITY : PK 생성을 DB에 위임
         > IDENTITY 정책은 DB가 PK 를 생성하기 때문에, IDENTITY로 설정되어 있을 경우에는 persist 순간에 INSERT Query가 날라감
         > 즉, 모아서 한꺼번에 INSERT 하지는 못한다~!
        2) SEQUENCE : sequence 생성 후에 NEXT_VALUE 가져와줌
         > persist 처리할 경우, DB에서 Sequence 의 다음 값을 가져옴(아직 INSERT가 되지는 않았음!)
         > 하지만 allocationSize를 설정해놓는다면, 미리 size 만큼의 value를 들고와서 메모리에서 당겨 씀
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;

    // Column Annotation에 Unique 제약 조건도 있지만, 잘 안쓰는 이유가 생성되는 제약조건 이름이 활용성이 없다.
    @Column(name = "name", nullable = false)
    private String userName;

    private Integer age;

    // Java의 Enum Type을 쓰기 위한 Annotation
    // Java Annotation에서 ordinal을 사용하면 안되듯이, ORDINAL은 사용하지 말 것
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 날짜 타입을 쓰기 위한 Annotation
    // LocalDate와 LocalDateTime을 쓰는 경우에는 Annotation 생략 가능
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // CLOB, BLOB
    @Lob
    private String description;

    // Memory에서만 사용하기 위한 field
    @Transient
    private String temp;

    public Member() {}

    public Member(Long id, String userName, Integer age, RoleType roleType, Date createDate, Date lastModifiedDate, String description, String temp) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.roleType = roleType;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
        this.temp = temp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
