package com.user.redex.controller;

import com.user.redex.dto.AppResponse;
import com.user.redex.dto.UserDto;
import com.user.redex.service.IUserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user.json")
public class UserRestApi {

    private Logger logger = LoggerFactory.getLogger(UserRestApi.class);

    public static String ERROR = "ERROR";

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/fetchAllUser", method = RequestMethod.GET)
    public ResponseEntity<?> fetchAllUser() {
        try {
            return new ResponseEntity<>(this.userService.fetchAllUser(), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("An error occurred while fetchAllUser ", getRootCause(ex));
            return new ResponseEntity<>(new AppResponse(ERROR, getRootCauseMessage(ex)), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/fetchById", method = RequestMethod.POST)
    public ResponseEntity<?> fetchById(@RequestBody UserDto requestPayload) {
        try {
            return new ResponseEntity<>(this.userService.fetchById(requestPayload), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("An error occurred while fetchById ", getRootCause(ex));
            return new ResponseEntity<>(new AppResponse(ERROR, getRootCauseMessage(ex)), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody UserDto requestPayload) {
        try {
            return new ResponseEntity<>(this.userService.addUser(requestPayload), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("An error occurred while addUser ", getRootCause(ex));
            return new ResponseEntity<>(new AppResponse(ERROR, getRootCauseMessage(ex)), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody UserDto requestPayload) {
        try {
            return new ResponseEntity<>(this.userService.updateUser(requestPayload), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("An error occurred while updateUser ", getRootCause(ex));
            return new ResponseEntity<>(new AppResponse(ERROR, getRootCauseMessage(ex)), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseEntity<?> deleteUser(@RequestBody UserDto requestPayload) {
        try {
            return new ResponseEntity<>(this.userService.deleteUser(requestPayload), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("An error occurred while deleteUser ", getRootCause(ex));
            return new ResponseEntity<>(new AppResponse(ERROR, getRootCauseMessage(ex)), HttpStatus.BAD_REQUEST);
        }
    }

    private static Throwable getRootCause(final Throwable throwable) {
        final List<Throwable> list = getThrowableList(throwable);
        Throwable rootCause = list.size() < 2 ? null : (Throwable) list.get(list.size() - 1);
        if (rootCause == null) {
            return throwable;
        }
        return rootCause;
    }

    private static String getRootCauseMessage(final Throwable throwable) {
        Throwable root = getRootCause(throwable);
        return root.toString();
    }

    private static List<Throwable> getThrowableList(Throwable throwable) {
        final List<Throwable> list = new ArrayList<Throwable>();
        while (throwable != null && list.contains(throwable) == false) {
            list.add(throwable);
            throwable = throwable.getCause();
        }
        return list;
    }

}
