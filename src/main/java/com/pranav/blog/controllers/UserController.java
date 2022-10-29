package com.pranav.blog.controllers;

import com.pranav.blog.payloads.UserDto;
import com.pranav.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    // POST: Add user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createdUserDto = userService.addUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }
    // PUT: Update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
        UserDto updatedUser = userService.updateUser(userDto,uid);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
    // DELETE: Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid){
       userService.deleteUser(uid);
        return new ResponseEntity<>(Map.of("message","User deleted successfully!"),HttpStatus.OK);
    }
    // GET: User with a particular Id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer uid){
        UserDto userDto = userService.getUserById(uid);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    // GET: All the users in the DB
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> usersList = userService.getAllUsers();
        return new ResponseEntity<>(usersList,HttpStatus.OK);
    }
}
