package com.pranav.blog.services;

import com.pranav.blog.entities.User;
import com.pranav.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
 UserDto addUser(UserDto user);
 UserDto updateUser(UserDto user,Integer userId);
 UserDto getUserById(Integer userId);
 List<UserDto> getAllUsers();
 void deleteUser(Integer userId);
}
