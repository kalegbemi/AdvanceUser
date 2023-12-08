package com.example.AdvanceUser.controller;

import com.example.AdvanceUser.model.AdvancedUser;
import com.example.AdvanceUser.model.AdvancedUserResource;
import com.example.AdvanceUser.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<AdvancedUser> saveUser(@Valid @RequestBody AdvancedUser advancedUser) {
        return new ResponseEntity<>(userService.saveUser(advancedUser), HttpStatus.CREATED);
    }

    @PostMapping("/allusers")
    public Map<String, Boolean> saveAllUser(@Valid @RequestBody List<AdvancedUser> advancedUsers){
        return userService.saveAllUsers(advancedUsers);
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdvancedUser>> getAllUser (){
        return ResponseEntity.ok(userService.getAll());
    }



    @GetMapping("/users/{id}")
    public ResponseEntity<AdvancedUser>  getUserById(@PathVariable long id){
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<String> updateUser(@Valid @PathVariable long id, @RequestBody AdvancedUser advancedUser){
        return ResponseEntity.ok(userService.updateUser(id, advancedUser));
    }
    @DeleteMapping("users/{id}")
    public ResponseEntity<AdvancedUser> deleteuser (@PathVariable long id){
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @GetMapping("user/{id}")
    public ResponseEntity<AdvancedUserResource> getUserResource(@PathVariable long id){
        AdvancedUserResource advancedUserResource = new AdvancedUserResource();
        AdvancedUser advancedUser = getUserById(id).getBody();
        advancedUserResource.setAdvancedUser(advancedUser);

        Link idlink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .getUserById(id)).withRel("idlink");
        Link updatelink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .updateUser(id, advancedUser)).withRel("updatelink");
        Link deletelink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .deleteuser(id)).withRel("deletelink");
        Link createlink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .saveUser(advancedUser)).withRel("createlink");
        advancedUserResource.add(idlink,updatelink,deletelink,createlink);
        return ResponseEntity.ok(advancedUserResource);
    }
}
