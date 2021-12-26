package com.test.interf;

import com.test.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IUser extends CrudRepository<User, Integer> {

}
