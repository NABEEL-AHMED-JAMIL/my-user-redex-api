package com.user.redex.service.impl;

import com.user.redex.dto.AppResponse;
import com.user.redex.dto.UserDto;
import com.user.redex.model.User;
import com.user.redex.repository.UserRepository;
import com.user.redex.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public static String SUCCESS = "SUCCESS";
    public static String ERROR = "ERROR";

    @Autowired
    private UserRepository userRepository;

    @Override
    public AppResponse fetchAllUser() {
        return new AppResponse(SUCCESS, "Data Fetch",
            this.userRepository.findAll().stream().map(user -> getUserDto(user)).collect(Collectors.toList()));
    }

    @Override
    public AppResponse fetchById(UserDto userDto) {
        Optional<User> user = this.userRepository.findById(userDto.getUuid());
        if (user.isPresent()) {
            return new AppResponse(SUCCESS, "Data Save", getUserDto(user.get()));
        }
        return new AppResponse(ERROR, "Data Not Found");
    }

    @Override
    public AppResponse addUser(UserDto userDto) {
        userDto.setTimestamp(System.currentTimeMillis()+"");
        User user = this.userRepository.save(getUser(userDto));
        userDto.setUuid(user.getUuid());
        return new AppResponse(SUCCESS, "Data Save", userDto);
    }

    @Override
    public AppResponse updateUser(UserDto userDto) {
        if (userDto.getUuid() != null) {
            Optional<User> user = this.userRepository.findById(userDto.getUuid());
            if (user.isPresent()) {
                user.get().setUuid(userDto.getUuid());
                user.get().setTimestamp(userDto.getTimestamp());
                user.get().setUsername(userDto.getUsername());
                user.get().setFirstName(userDto.getFirstName());
                user.get().setLastName(userDto.getLastName());
                user.get().setAge(userDto.getAge());
                user.get().setSalary(userDto.getSalary());
                user.get().setAge(userDto.getAge());
                this.userRepository.save(user.get());
                return new AppResponse(SUCCESS, "Data Update", userDto);
            }
        }
        return new AppResponse(ERROR, "No Data Found");
    }

    @Override
    public AppResponse deleteUser(UserDto userDto) {
        Optional<User> user = this.userRepository.findById(userDto.getUuid());
        if (user.isPresent()) {
            this.userRepository.delete(user.get());
            return new AppResponse(SUCCESS, "Data Delete", userDto);
        }
        return new AppResponse(ERROR, "Data Not Found");
    }
}
