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
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");

            // 2) 영속 상태
            //  > EntityManager.persist를 수행할 경우, 1차 캐시에 담아둔다.
            //  > JPA는 Database를 먼저 접근하는 것이 아니라, 1차 캐시에서 우선적으로 조회를 함
            //  > 1차 캐시에 담겨 있지 않은 객체를 조회할 경우, DB 조회 후 1차 캐시에 담고 반환함
//            System.out.println("=== BEFORE ===");
//            em.persist(member);
//            System.out.println("=== AFTER ===");

            Member findMember = em.find(Member.class, 101L);
            System.out.printf("findMember = %d\n", findMember.getId());

            Member findMember2 = em.find(Member.class, 101L);
//            System.out.printf("findMember = %d\n", findMember2.getId());
            System.out.printf("Is findMember same as findMember2? %s\n", findMember == findMember2);

            // 3) Commit 하는 순간에 DB 에 INSERT SQL을 보냄!
            //  > persist 발생 시, '쓰기 지연 SQL 저장소'에 날릴 쿼리를 저장해둠
            //  > commit 을 치면 flush를 치면서 DB에 Insert Query를 날리게 됨
//            Member m1 = new Member(102L, "A");
//            Member m2 = new Member(103L, "B");
//
//            Arrays.asList(m1, m2).forEach(em::persist);

            // 4) Entity 수정 / 변경 감지
            //   --> 수정, 변경 시에는 persist 호출을 할 필요가 없음!!!
            //   --> Java의 Collection 쓰는 것과 동일한 거임!!!
            //  > 영속성 Context 의 1차 캐시에는 SnapShot도 존재함
            //   >> 스냅샷을 비교해서 해당 객체가 변경됐는지 여부를 확인하는 것임
//            Member member1 = em.find(Member.class, 102L);
//            member1.setName("ZZZZZZ");

            // 5) Flush
            //   --> '쓰기 지연 SQL 저장소'에 있던 Query들을 DB로 날려주는 것
            //  > em.flush() / commit / JPQL Query 실행 시 호출 됨
            //  > Flush 모드 옵션이 있음
            //  > 영속성 컨텍스트를 비우는 것이 아니라, 변경 내용을 데이터베이스에 동기화 하는 것임

            // 6) 준영속 상태
            //   --> 영속 상태의 Entity가 영속성 컨텍스트에서 분리된 상태(detached)
            //  > 영속성 Context 가 더 이상 해당 객체를 관리하지 않게 됨
            //  > em.detach(Object) / em.clear() / em.close()
//            em.detach(member1);
            em.clear();
            Member tempMember = em.find(Member.class, 101L);
            System.out.println("SAME ????? : " + (findMember == tempMember)); // false

            System.out.println("--------------------------");
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
