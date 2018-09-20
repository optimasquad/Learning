package com.mkyong.core.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexPredicateExample {
	public static void main(String[] args) {
		// Compile regex as predicate
		Predicate<String> emailFilter = Pattern.compile("^(.+)@example.com$").asPredicate();

		// Input list
		//List<String> emails = Arrays.asList("alex@example.com", "bob@yahoo.com", "cat@google.com", "david@example.com");
		List<String> emails = Arrays.asList("alex@example.com", "bob");
		// Apply predicate filter
		List<String> desiredEmails = emails.stream().filter(emailFilter).collect(Collectors.<String>toList());
		
		String desiredEmail = Optional.of(emails.stream().filter(emailFilter).findAny().orElse("jatin")).orElse("jatin");
		// Now perform desired operation
		desiredEmails.forEach(System.out::println);
		
		System.out.println(desiredEmail);
	}
}