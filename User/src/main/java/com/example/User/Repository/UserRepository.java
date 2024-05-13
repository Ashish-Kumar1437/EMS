package com.example.User.Repository;

import com.example.User.Entities.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {
    UserDTO findByEmail(String email);

    UserDTO findByUserName(String userName);

    @Modifying
    @Transactional
    @Query("UPDATE UserDTO u set u.status = false where u.deactivationDate = :deactivationDate AND u.status = true")
    int updateStatusForDeactivatedUsers(String deactivationDate);
}
