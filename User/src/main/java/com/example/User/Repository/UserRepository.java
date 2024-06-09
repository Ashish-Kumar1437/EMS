package com.example.User.Repository;

import com.example.User.Models.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    UserEntity findByUserName(String userName);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u set u.status = false where u.deactivationDate = :deactivationDate AND u.status = true")
    int updateStatusForDeactivatedUsers(String deactivationDate);

    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
}
