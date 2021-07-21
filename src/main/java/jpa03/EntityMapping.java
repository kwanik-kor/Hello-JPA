package jpa03;

import jpa01.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 1) 객체와 테이블 매핑
 * 2) 기본 키 매핑
 * 3) 필드와 컬럼 매핑
 * 4) 연관관계 매핑
 */
public class EntityMapping {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
//            member.setId("ID_A");
            member.setUserName("C");

            em.persist(member);

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
