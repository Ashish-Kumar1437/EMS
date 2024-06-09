package com.example.User.Repository;

import com.example.User.Models.Entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
}
