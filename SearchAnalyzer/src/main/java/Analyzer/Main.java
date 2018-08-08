package Analyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import beans.AnalyzerDto;
import beans.Criteria;
import beans.DependencyExtractorResponse;
import exception.UnableToExtractException;
import extract.DependencyExtractor;
import extractor.impl.util.FileParsingDependencyExtractor;
import extractor.impl.util.PropertyLoader;

/**
 * @author jatin
 *
 */
public class Main {

	private static final String SOURCE_FILE_NAME_PATTERN = "sourceFileNamePattern";

	private static final String SOURCE_FILES_DIRECORTY = "sourceFilesDirectory";

	private static final String REPORT_FILES_DIRECORTY = "reportFilesDirectory";

	private static final String IS_DEEP_SEARCH_REQUIRED = "isDeepSearchRequired";

	private static final String DEPENDENCY_MATCH_REG_EX = "dependencyMatchRegEx";

	private static final String EXTRACTION_TIMEOUT = "extractionTimeout";

	private static final String METHOD_DEPENDENCY_MATCH_REG_EX = "methodDependencyMatchRegEx";

	public static String extractAndReportBasedOnPropsFile(AnalyzerDto analyzerDto) {

		String reportFilePath = null;
		try {
			PropertyLoader.loadPropValues();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		final DependencyExtractor dependencyExtractor = new FileParsingDependencyExtractor();
		final Criteria criteria = new Criteria();
		final List<String> fileNameRegEx = new ArrayList<String>();

		// final String sourceFileNamePattern=analyzerDto.getSearchPattern();

		final String sourceFileNamePattern = PropertyLoader.getPropValue(Main.SOURCE_FILE_NAME_PATTERN);
		if (null == sourceFileNamePattern) {
			throw new UnableToExtractException("sourceFileNamePattern not found in props file");
		}
		fileNameRegEx.add(sourceFileNamePattern);
		//fileNameRegEx.add(PropertyLoader.getPropValue(Main.SOURCE_FILE_NAME_PATTERN));
		criteria.setSourceFileNameRegExList(fileNameRegEx);
		final String isDeepSearchRequired = analyzerDto.getDeepSearchRequired();

		// final String isDeepSearchRequired =
		// PropertyLoader.getPropValue(Main.IS_DEEP_SEARCH_REQUIRED);

		criteria.setDeepSearchRequired(null != isDeepSearchRequired ? isDeepSearchRequired.equals("true") : false);

		final String dependencyMatchRegEx = PropertyLoader.getPropValue(Main.DEPENDENCY_MATCH_REG_EX);
		if (dependencyMatchRegEx == null) {
			throw new UnableToExtractException("dependencyMatchRegEx not found in props file");
		}
		criteria.setDependencyMatchRegEx(dependencyMatchRegEx);

		final String methodMatchRegEx = PropertyLoader.getPropValue(Main.METHOD_DEPENDENCY_MATCH_REG_EX);
		if (null == methodMatchRegEx) {
			throw new UnableToExtractException("methodMatchRegEx not found in props file");
		}
		criteria.setMethodDependencyRegEx(methodMatchRegEx);

		/*
		 * final String extractionTimeout =
		 * PropertyLoader.getPropValue(Main.EXTRACTION_TIMEOUT);
		 */

		final String extractionTimeout = analyzerDto.getExtractionTimeout();
		if (extractionTimeout == null) {
			throw new UnableToExtractException("sourceFilesDirectory not found in props file");
		}

		final String reportFilesDirectory = PropertyLoader.getPropValue(Main.REPORT_FILES_DIRECORTY);

		if (reportFilesDirectory == null) {
			throw new UnableToExtractException("reportFilesDirectory not foubd in props file");
		}
		Map<String, List<DependencyExtractorResponse>> dependencies = dependencyExtractor.extractDependecies(criteria,
				"E:\\D\\Amdocs\\KenanRest\\KenanRest");

		return reportFilePath;

	}

	public static void main(String args[]) {

		AnalyzerDto analyzerDto = new AnalyzerDto();
		analyzerDto.setDeepSearchRequired("false");
		analyzerDto.setExtractionTimeout("1000");
		analyzerDto.setSearchPattern("/rest");
		

		Main.extractAndReportBasedOnPropsFile(analyzerDto);
	}

}
