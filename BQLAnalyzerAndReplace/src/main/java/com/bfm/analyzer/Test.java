/**
 * 
 */
package com.bfm.analyzer;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @author hp
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String text = "jatin is a jatin very good boy";

		String keys[] = { "jatin", "good", "boy" };
		String[] values = { "Test", "Test1", "Test2" };

		String newString = StringUtils.replaceEach(text, keys, values);
		System.out.println(newString);

	}

}
