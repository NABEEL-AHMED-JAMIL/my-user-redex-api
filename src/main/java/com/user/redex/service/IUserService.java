package com.user.redex.service;

import com.user.redex.dto.AppResponse;
import com.user.redex.dto.UserDto;
import com.user.redex.model.User;


public interface IUserService {

    public AppResponse fetchAllUser();

    public AppResponse fetchById(UserDto userDto);

    public AppResponse addUser(UserDto userDto);

    public AppResponse updateUser(UserDto userDto);

    public AppResponse deleteUser(UserDto userDto);

    public default UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUuid(user.getUuid());
        userDto.setTimestamp(user.getTimestamp());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setAge(user.getAge());
        userDto.setSalary(user.getSalary());
        userDto.setAge(user.getAge());
        return userDto;
    }

    public default User getUser(UserDto userDto) {
        User user = new User();
        user.setUuid(userDto.getUuid());
        user.setTimestamp(userDto.getTimestamp());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setSalary(userDto.getSalary());
        user.setAge(userDto.getAge());
        return user;
    }
}
