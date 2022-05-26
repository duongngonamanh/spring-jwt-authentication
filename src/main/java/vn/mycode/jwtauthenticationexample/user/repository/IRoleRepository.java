package vn.mycode.jwtauthenticationexample.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.mycode.jwtauthenticationexample.user.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
}