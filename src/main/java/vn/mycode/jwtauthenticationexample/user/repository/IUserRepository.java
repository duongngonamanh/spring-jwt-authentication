package vn.mycode.jwtauthenticationexample.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.mycode.jwtauthenticationexample.user.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username like ?1")
    User findByUsernameLike(String username);

}