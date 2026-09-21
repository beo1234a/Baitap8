package anhtuan.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import anhtuan.vn.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}