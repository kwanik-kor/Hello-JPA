package jpa02;

import jpa01.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 02. 영속성 컨텍스트에 대해 알아보기
 *  1) 영속성 컨텍스트란 엔티티를 영구 저장하는 환경을 의미함
 *  2) persist 메소드는 사실 DB에 저장하는 것이 아니라 영속성 컨텍스트에 저장하는 것임!
 *  3) Entity의 생명 주기
 *    > 비영속(new / transient)
 *      >> 객체를 생성한 상태
 *    > 영속(managed)
 *      >> 객체를 저장한 상태(EntityManager.persist)
 *    > 준영속(detached)
 *    > 삭제(removed)
 *
 */
public class PersistEntity {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 1) 비영속 상태
            Member member = new Member();
            member.setId(100L);
            member.setName("HI");

            // 2) 영속 상태
            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");

            

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
