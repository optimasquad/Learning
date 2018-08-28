package com.itests.fx.common;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.itests.fx.exception.UnableToExtractException;
import com.itests.fx.model.FailureResponse;
import com.itests.fx.testsuite.PpvTestSuite;

/**
 * 
 * 
 * @author jatinma
 * 
 *         This is the class used for generating the report
 *
 */

public class ReportGenerator {
	/**
	 * @param args
	 */

	private static final boolean isPosix = FileSystems.getDefault().supportedFileAttributeViews().contains("posix");

	private static final String REPORT_FILES_DIRECORTY = "reportFilesDirectory";

	public static void reportGenerate() {
		final String reportFilesDirectory = PropertyLoader.getPropValue(ReportGenerator.REPORT_FILES_DIRECORTY);
		Result result = JUnitCore.runClasses(PpvTestSuite.class);

		if (!result.wasSuccessful()) {
			List<FailureResponse> failuresList = result.getFailures().stream()
					.map(p -> new FailureResponse(p.getMessage() != null ? p.getMessage().substring(0, 70) : "",
							p.getMessage() != null ? p.getMessage().substring(0, 70) : "",
							p.getTestHeader() != null ? p.getTestHeader().substring(0, 70) : "",
							p.getTrace() != null ? p.getTrace().substring(0, 70) : ""))
					.collect(Collectors.toList());

			reportTestCaseDetails(failuresList, reportFilesDirectory);
		}

	}

	private static String reportTestCaseDetails(List<FailureResponse> failures, String reportDir) {
		final Path reportPath = Paths.get(reportDir);
		if (Files.notExists(reportPath)) {
			if (isPosix) {
				try {
					final Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("r-xr-----");
					final FileAttribute<Set<PosixFilePermission>> fileAttributes = PosixFilePermissions
							.asFileAttribute(permissions);
					Files.createDirectory(reportPath, fileAttributes);
				} catch (final IOException io) {
					io.printStackTrace();

				}
			} else {
				reportPath.toFile().mkdir();
			}
		} else if (!Files.isDirectory(reportPath)) {
			throw new UnableToExtractException("the report path is either does'nt exit or is not a directory");
		} else if (!Files.isWritable(reportPath)) {
			throw new UnableToExtractException("the report directory either does'nt exit or is not writable");
		}
		final Path reportFile = Paths.get(reportDir + "/FailureCasesReport_"
				+ new SimpleDateFormat("dd-MM-yyyyHH_mm_ss.S").format(new Date()) + ".csv");

		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(reportFile, Charset.defaultCharset(),
				StandardOpenOption.CREATE_NEW)) {

			bufferedWriter.write("Test Case Name,Test Case Status,TestCasesException \n");
			bufferedWriter.newLine();

			for (FailureResponse failureResponse : failures) {

				bufferedWriter.write(failureResponse.getMessage() + ", " + failureResponse.getTestHeader() + " ,"
						+ failureResponse.getTrace() + " ," + failureResponse.getClassName());
				bufferedWriter.newLine();

			}

		} catch (final IOException io) {
			io.printStackTrace();

		}
		System.out.println("Report generation complete at Path:" + reportFile.toString());
		return reportFile.toString();
	}

}
