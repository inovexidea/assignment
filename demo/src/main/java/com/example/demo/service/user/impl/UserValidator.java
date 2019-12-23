package com.example.demo.service.user.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.entity.user.User;
import com.example.demo.service.user.UserService;


/**
 * UserValidator class
 *
 * @author Faruk Hossain
 *
 */
@Component
public class UserValidator implements Validator {
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "NotEmpty");
//		if (user.getAge() < 1) {
//			errors.rejectValue("age", "Invalid.userForm.age");
//		}
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
//		if (!(isValidEmailAddress(user.getEmail()))) {
//			errors.rejectValue("email", "Invalid.userForm.email");
//		}
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
//		if (userService.findByUsername(user.getUsername()) != null) {
//			errors.rejectValue("username", "Duplicate.userForm.username");
//		}
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
//		if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
//			errors.rejectValue("password", "Size.userForm.password");
//		}
//
//		if (!user.getPasswordConfirm().equals(user.getPassword())) {
//			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
//		}

	}

	public boolean isValidEmailAddress(String email) {
		String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern pattern = java.util.regex.Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
