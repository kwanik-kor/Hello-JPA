package jpa01;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // Transaction 단위로 반드시 EntityManager를 만들어 줘야 함
        //  > EntityManager 는 Thread 간 공유가 일어나서는 안되며,
        //    Transaction 안에서 모든 데이터 변경 처리를 해줘야 함!!!!
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 1. Insert
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloA");
//            em.persist(findMember);

            // 2. Update
            //  > Update는 persist 처리를 하지 않아도 됨.
            //  > 객체를 JPA에서 관리하기 때문에, commit 시점에서 쿼리를 날림
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloC");

            // 3. JPQL 맛보기
            //  - Table이 아니라 객체가 대상이 됨("쿼리 문 안에 Member는 클래스 명과 동일해야 함")
            List<Member> result = em.createQuery("SELECT m FROM Member AS m", Member.class)
                    .getResultList();

//            for(Member member : result) {
//                System.out.println("member.getName() : " + member.getName());
//            }

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
