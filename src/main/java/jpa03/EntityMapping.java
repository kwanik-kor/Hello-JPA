package jpa03;

import jpa01.Member_2;
import jpa01.Team;

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
            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member);
            em.persist(team);

            // 연관관계의 주인에 값을 넣어주지 않고 갈 경우에 추후에 문제가 생길 수 있음
            Member_2 member = new Member_2();
            member.setUsername("member1");
//            member.changeTeam(team);
            team.addMember(member);

            em.persist(member);

            // 아래와 같이 넣어주지 않을 경우에는, 객체지향적으로 봤을 때 문제가 발생한다.
            // flush와 clear를 해주지 않는 이상엔 값이 없는채로 진행될 수 있는거임
            // 즉, 양방향 연관관계 설정 시 양쪽에 모두 값을 세팅해줄 것!!!!
//            team.getMembers().add(member);

            em.flush();
            em.clear();



            // Not Good!!!!
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member_2 member = new Member_2();
//            member.setUsername("member1");
//            member.setTeamId(team.getId());
//            em.persist(member);

//            Member_2 findMember = em.find(Member_2.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
