package com.example.AdvanceUser.repository;

import com.example.AdvanceUser.model.AdvancedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface  UserRepository extends JpaRepository<AdvancedUser, Long> {

    List<AdvancedUser> findByFullName(String fullName);
    List<AdvancedUser> findByGender(String gender);
    AdvancedUser findByEmail(String email);

}
