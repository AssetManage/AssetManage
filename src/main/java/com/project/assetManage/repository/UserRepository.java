package com.project.assetManage.repository;

import com.project.assetManage.entity.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);
    Optional<User> findUserByEmail(String email);

    List<User> findAllByMobileTelNum(String mobileTelNum);
}
