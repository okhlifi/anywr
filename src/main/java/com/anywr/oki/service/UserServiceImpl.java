package com.anywr.oki.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anywr.oki.dao.UserRepository;
import com.anywr.oki.exception.UserNotFoundException;
import com.anywr.oki.model.User;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	private Validator validator;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void saveUser(User user) {
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
		userRepository.save(user);
	}

	@Override
	public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException {
		User user = userRepository.findByUserNameAndPassword(name, password);
		if (user == null) {
			throw new UserNotFoundException("Invalid id and password");
		}
		return user;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User getProfil(String userName) {
		return userRepository.findByUserName(userName);
	}

}