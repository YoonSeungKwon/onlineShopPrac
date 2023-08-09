package yoon.project.onlineShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.project.onlineShop.domain.Members;

@Repository
public interface MemberRepository extends JpaRepository<Members, Long> {

    Members findMembersByRefreshToken(String token);
    Members findMembersById(String id);

}
