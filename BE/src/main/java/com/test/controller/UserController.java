package com.test.controller;

import com.test.interf.IUser;
import com.test.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    IUser iuser;

    Boolean accessUser;

    @SuppressWarnings("unchecked")
    @RequestMapping(method = RequestMethod.POST, value = "/getUser")
    @ResponseBody
    public ResponseEntity<?> getUser (@RequestBody User user) throws SQLException {
        accessUser = false;

        User ut = new User("", "");
        iuser.findAll().forEach(u -> {
                if(u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())) {
                    accessUser = true;
                    ut.setName(u.getName());
                    ut.setSurname(u.getSurname());
                };
        });

        if(accessUser) {
            return new ResponseEntity<User>(ut, HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
        }
    }
}
