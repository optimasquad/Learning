package extractor.impl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegExMatcher {
	public static void main(String args[]) {

		final String toMatch = "StepUp.java";
		final String pattern = "^[A-Za-z]*StepUp[A-Za-z]*.java";

		System.out.println("Result :" + JavaRegExMatcher.matches(toMatch, pattern));
	}

	public static Object matches(final String toMatch, final String pattern) {
		final Pattern p = Pattern.compile(pattern);
		final Matcher matcher = p.matcher(toMatch);

		while (matcher.find()) {
			System.out.println("Start index: " + matcher.start());
			System.out.println("End index: " + matcher.end());
			System.out.println(matcher.group());
		}

		return null;

	}

}
