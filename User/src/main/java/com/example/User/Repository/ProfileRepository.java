package com.example.User.Repository;

import com.example.User.Entities.ProfileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileDTO,Long> {
}
