package vn.mycode.jwtauthenticationexample.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.mycode.jwtauthenticationexample.user.entity.RelUsersRole;

import java.util.List;

public interface IRelUsersRoleRepository extends JpaRepository<RelUsersRole, Integer> {
    @Query("select r.role.name from RelUsersRole r where r.user.username like ?1")
    List<String> findRole_NameByUser_UsernameLike(String username);
}