package com.example.AdvanceUser.service;

import com.example.AdvanceUser.GlobalExceptionHandler.AdvanceUserFileNotFound;
import com.example.AdvanceUser.model.AdvancedUser;
import com.example.AdvanceUser.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class UserService {//model->Repository->Service->Controller->client

    private UserRepository userRepository;

    @CacheEvict(value = "allUsers", allEntries = true)
    public AdvancedUser saveUser(@Valid AdvancedUser advancedUser) {
        return userRepository.save(advancedUser);
    }

    public Map<String, Boolean> saveAllUsers(List<AdvancedUser> users){
        Map<String, Boolean> response = new HashMap<>();
        for(AdvancedUser user : users){
            response.put(user.getFullName()+" user added successfully", true);
        }
        return response;
    }
    @Cacheable("allUsers")
    public List<AdvancedUser> getAll() {
        return userRepository.findAll();
    }

    @Cacheable(value = "singleUser", key = "#id")
    public AdvancedUser findUserById(long id){
        return userRepository.findById(id).orElse(null);
    }


    @CacheEvict(value = {"singleUser", "allUsers"}, key = "#id")
    public String updateUser(long id,@Valid AdvancedUser advancedUser){
        AdvancedUser toUpdate = findUserById(id);

        toUpdate.setEmail(advancedUser.getEmail());
        toUpdate.setGender(advancedUser.getGender());
        toUpdate.setFullName(advancedUser.getFullName());
        toUpdate.setPhoneNumber(advancedUser.getPhoneNumber());

        userRepository.save(toUpdate);
        return "user successfully updated";
    }

    @CacheEvict(value = {"singleUser", "allUsers"}, allEntries = true)
    public AdvancedUser deleteById(long id) {
        AdvancedUser user = findUserById(id);
        userRepository.deleteById(id);
        return(user);
    }
}

