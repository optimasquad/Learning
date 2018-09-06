package com.mkyong.core.utils;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.joda.time.LocalDate;

public class App {

	public static void main(String[] args) {

		Options options = new Options();

		Option input = new Option("i", "input", true, "input file path");
		input.setRequired(true);
		options.addOption(input);

		Option output = new Option("o", "output", true, "output file");
		output.setRequired(true);
		options.addOption(output);

		CommandLineParser parser = new BasicParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd;

		try {
			cmd = parser.parse(options, args);
			String inputFilePath = cmd.getOptionValue("input");
			String outputFilePath = cmd.getOptionValue("output");

			System.out.println(inputFilePath);
			System.out.println(outputFilePath);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			formatter.printHelp("utility-name", options);

			System.exit(1);
		}

		
		System.out.println(getLocalCurrentDate());
	}

	private static String getLocalCurrentDate() {

		LocalDate date = new LocalDate();
		return date.toString();

	}

}
