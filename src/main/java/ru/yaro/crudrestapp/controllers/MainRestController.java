package ru.yaro.crudrestapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yaro.crudrestapp.model.Role;
import ru.yaro.crudrestapp.model.User;
import ru.yaro.crudrestapp.service.RoleService;
import ru.yaro.crudrestapp.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
public class MainRestController {
    private UserService userService;
    private RoleService roleService;

    public MainRestController() {
    }

    @Autowired
    public MainRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/getCurrentUser")
    public ResponseEntity<User> getCurrentUser (Principal principal){
        User user = userService.getByEmail(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<Set<Role>> getAllRoles (){
        Set<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers (){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/editUser")
    public ResponseEntity<Void> editUser(@RequestBody User user){
        roleService.setExistingRoles(user);
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestBody User user){
        userService.deleteUserById(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/newUser")
    public ResponseEntity<Void> newUser(@RequestBody User user){
        roleService.setExistingRoles(user);
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
