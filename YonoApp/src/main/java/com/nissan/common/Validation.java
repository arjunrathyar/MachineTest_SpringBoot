package com.nissan.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Component;

@Component
public class Validation {

	public Boolean isNameValid(String name) {
		boolean bool = false;
		try {
			Pattern namePattern = Pattern.compile("[A-Za-z]"); // either -> [^A-Za-z]
			Matcher nameMatcher = namePattern.matcher(name);
			if (!nameMatcher.find() || name.length()>30) { // and -> nameMatcher.find()
				throw new InvalidNameException("Hey! Invalid Name");
			} else {
				bool = true;
			}
		} catch (InvalidNameException e) {
			e.getMessage();
		}
		return bool;
	}

	public Boolean isMobileValid(String mNumber) {
		boolean bool = false;
		try {
			if (mNumber.matches("[0-9]*") && mNumber.length() == 10) {
				bool = true;
			} else {
				throw new InvalidNameException("Hey! Invalid Number");
			}
		} catch (InvalidNameException e) {
			e.getMessage();
		}
		return bool;
	}

}
