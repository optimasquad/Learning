package extractor.impl.util;

/**
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import beans.Criteria;
import beans.DependencyExtractorResponse;
import exception.UnableToExtractException;
import extract.DependencyExtractor;

/**
 * @author jatin
 *
 */
public class FileParsingDependencyExtractor implements DependencyExtractor {

	private static final String JAVA_FILE_EXTENSION = ".java";
	String dependencyMatchRegEx = null;
	private String methodDependencyMatchRegEx = null;
	private static final boolean isPosix = FileSystems.getDefault().supportedFileAttributeViews().contains("posix");

	public Map<String, List<DependencyExtractorResponse>> extractDependenciesMap = new HashMap<String, List<DependencyExtractorResponse>>();

	@Override
	public Map<String, List<DependencyExtractorResponse>> extractDependecies(final Criteria criteria,
			final String startingDir) {

		this.dependencyMatchRegEx = criteria.getDependencyMatchRegEx();
		this.methodDependencyMatchRegEx = criteria.getMethodDependencyRegEx();
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		final Future<Map<String, List<DependencyExtractorResponse>>> future = executor
				.submit(new Callable<Map<String, List<DependencyExtractorResponse>>>() {

					@Override
					public Map<String, List<DependencyExtractorResponse>> call() {

						final Map<String, List<DependencyExtractorResponse>> extractDependecies = FileParsingDependencyExtractor.this
								.extractDependencies(criteria.getSourceFileNameRegExList(), startingDir);

						FileParsingDependencyExtractor.this.getExtractedDependenciesMap().putAll(extractDependecies);

						if (criteria.isDeepSearchRequired()) {
							FileParsingDependencyExtractor.this.getExtractedDependenciesMap()
									.putAll(FileParsingDependencyExtractor.this
											.extractNextLevelDependencies(extractDependecies, startingDir));
						}

						return FileParsingDependencyExtractor.this.getExtractedDependenciesMap();
					}
				});
		Map<String, List<DependencyExtractorResponse>> dependencies = null;

		try {
			System.out.println("Started..");
			dependencies = future.get(criteria.getExtractionTimeout(), TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();

			this.shutdownAndAwaitTermination(executor, criteria.getExtractionTimeout());
			throw new UnableToExtractException(e);
		}
		this.shutdownAndAwaitTermination(executor, criteria.getExtractionTimeout());
		System.out.println("Extract Complete");
		return dependencies;
	}

	private Map<String, List<DependencyExtractorResponse>> extractNextLevelDependencies(
			final Map<String, List<DependencyExtractorResponse>> currentLevelDependencies, final String startingDir) {

		final Map<String, List<DependencyExtractorResponse>> extractDependenciesNextLevelToReturn = new HashMap<String, List<DependencyExtractorResponse>>();
		// When no more dependencies to analyse return back
		if ((currentLevelDependencies == null) || currentLevelDependencies.isEmpty()) {
			return extractDependenciesNextLevelToReturn;
		}

		Map<String, List<DependencyExtractorResponse>> extractDependenciesNextLevelForOneKey = null;

		for (final String key : currentLevelDependencies.keySet()) {
			System.out.println("Analysing dependencies for key: " + key);

			final List<String> extractDependenciesNextLevelForTheKey = new ArrayList<String>();

			for (final DependencyExtractorResponse dependencyClasses : currentLevelDependencies.get(key)) {
				extractDependenciesNextLevelForTheKey.add(dependencyClasses.getMatchedDependencyFileName()
						+ FileParsingDependencyExtractor.JAVA_FILE_EXTENSION);
			}
			extractDependenciesNextLevelForOneKey = this.extractDependencies(extractDependenciesNextLevelForTheKey,
					startingDir);

			extractDependenciesNextLevelToReturn.putAll(extractDependenciesNextLevelForOneKey);

		}
		// go for the next depth for recursive call

		this.getExtractedDependenciesMap().putAll(extractDependenciesNextLevelToReturn);
		this.extractNextLevelDependencies(extractDependenciesNextLevelToReturn, startingDir);

		return extractDependenciesNextLevelToReturn;
	}

	protected Map<String, List<DependencyExtractorResponse>> extractDependencies(final List<String> fileNameRegExList,
			final String startingDir) {

		final Map<String, List<DependencyExtractorResponse>> extractedDependencies = new HashMap<String, List<DependencyExtractorResponse>>();
		Map<String, List<DependencyExtractorResponse>> extractedDependenciesForOneRegEx = null;
		for (final String fileNameRegEx : fileNameRegExList) {
			if (this.isDependenciesAlreadyExtracted(fileNameRegEx)) {
				continue;
			}
			extractedDependenciesForOneRegEx = this.extractDependencies(fileNameRegEx, startingDir);
			extractedDependencies.putAll(extractedDependenciesForOneRegEx);

		}
		return extractedDependencies;

	}

	protected Map<String, List<DependencyExtractorResponse>> extractDependencies(final String fileNameRegEx,
			final String startingDir) {

		final Path startingDirPath = Paths.get(startingDir);

		final Find.Finder finder = new Find.Finder(fileNameRegEx);

		try {
			Files.walkFileTree(startingDirPath, finder);
		} catch (final IOException e) {
			e.printStackTrace();
			throw new UnableToExtractException(e);
		}
		final List<Path> listOfMatchingSourceFilePaths = finder.getListOfPathMatched();

		final Map<String, List<DependencyExtractorResponse>> extractDependencies = this
				.extractDependencies(listOfMatchingSourceFilePaths);
		return extractDependencies;

	}

	protected Map<String, List<DependencyExtractorResponse>> extractDependencies(final List<Path> listOfSourceFilePaths)

	{
		final Map<String, List<DependencyExtractorResponse>> extractDependencies = new HashMap<String, List<DependencyExtractorResponse>>();

		for (final Path fileNamePath : listOfSourceFilePaths) {
			if (!this.isDependenciesAlreadyExtracted(fileNamePath.toString())) {
				extractDependencies.put(fileNamePath.toString(), this.extractDependencies(fileNamePath));
			}
		}
		return extractDependencies;
	}

	private boolean isDependenciesAlreadyExtracted(final String fileName) {
		if (null == fileName) {
			return false;
		}
		final String searchString = fileName;

		return this.getExtractedDependenciesMap().containsKey(searchString);
	}

	private void shutdownAndAwaitTermination(final ExecutorService pool, final long timeout) {

		pool.shutdown();// Disable new tasks from being submitted

		try {
			// Wait for a while for existing tasks to terminate
			if (!pool.awaitTermination(timeout, TimeUnit.MILLISECONDS)) {
				pool.shutdownNow();// Cancel currently executing tasks
				// Wait for a while for a task to respond to being cancelled
				if (!pool.awaitTermination(timeout, TimeUnit.MILLISECONDS)) {
					System.err.println("Pool did not terminate");
				}
			}

		} catch (final InterruptedException e) {
			e.printStackTrace();
			// (Re-) cancel if current thread also interrupted
			pool.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}

	}

	/**
	 * 
	 * 
	 * @param sourceFilePath
	 * @return
	 */
	protected List<DependencyExtractorResponse> extractDependencies(final Path sourceFilePath) {

		if (this.isDependenciesAlreadyExtracted(sourceFilePath.toString())) {
			return null;
		}
		final List<DependencyExtractorResponse> extractDependencies = new ArrayList<DependencyExtractorResponse>();
		if (this.dependencyMatchRegEx == null) {
			throw new UnableToExtractException(new IllegalStateException("Error: dependencyMatchRegEx is null"));
		}
		final Pattern p = Pattern.compile(this.dependencyMatchRegEx);
		final Pattern pMethod = Pattern.compile(this.methodDependencyMatchRegEx);
		try (BufferedReader bufferedReader = Files.newBufferedReader(sourceFilePath, Charset.forName("UTF-8"))) {
			LineNumberReader reader = new LineNumberReader(bufferedReader);
			int cnt = 0;
			int countOfMethod = 0;
			String line = null;
			FileInputStream fstream = null;
			String lineMethod = null;
			final String sourceFileName = sourceFilePath.getFileName().toString();

			while ((line = bufferedReader.readLine()) != null) {
				final Matcher matcher = p.matcher(line);

				while (matcher.find()) {
					final String matchedDependency = matcher.group();
					if (!extractDependencies.contains(matchedDependency) && !sourceFileName
							.equals(matchedDependency + FileParsingDependencyExtractor.JAVA_FILE_EXTENSION)) {

						DependencyExtractorResponse dependencyExtractorResponse = new DependencyExtractorResponse();
						fstream = new FileInputStream(sourceFilePath.toString());
						DataInputStream in = new DataInputStream(fstream);
						BufferedReader brNew = new BufferedReader(new InputStreamReader(in));
						while ((lineMethod = brNew.readLine()) != null) {
							final Matcher mactherMethod = pMethod.matcher(lineMethod.trim());
							while (mactherMethod.find()) {
								final String matchedDependencyMethod = mactherMethod.group();
								if (null != matchedDependencyMethod) {
									countOfMethod += 1;
								}
							}
						}
						dependencyExtractorResponse.setNumberOfMethods(String.valueOf(countOfMethod - 1));

						while (reader.readLine() != null) {
							cnt = reader.getLineNumber();
						}
						dependencyExtractorResponse.setNumberOfLines(String.valueOf(cnt));
						dependencyExtractorResponse.setMatchedDependencyFileName(matchedDependency);
						extractDependencies.add(dependencyExtractorResponse);
					}

				}

			}
		} catch (final FileNotFoundException fe) {
			fe.printStackTrace();
			throw new UnableToExtractException(fe);

		}

		catch (final IOException io) {
			io.printStackTrace();
			throw new UnableToExtractException(io);

		}
		return extractDependencies;
	}

	@Override
	public String reportDependencies(final Map<String, List<DependencyExtractorResponse>> dependencies,
			final String reportDir) {
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
					throw new UnableToExtractException(
							"the report path is neither found nor we are able to create is new");
				}
			} else {
				reportPath.toFile().mkdir();
			}
		} else if (!Files.isDirectory(reportPath)) {
			throw new UnableToExtractException("the report path is either does'nt exit or is not a directory");
		} else if (!Files.isWritable(reportPath)) {
			throw new UnableToExtractException("the report directory either does'nt exit or is not writable");
		}

		final Path reportFile = Paths.get(reportDir + "/DependenciesReport_"
				+ new SimpleDateFormat("dd-MM-yyyyHH_mm_ss.S").format(new Date() + ".csv"));

		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(reportFile, Charset.defaultCharset(),
				StandardOpenOption.CREATE_NEW)) {

			bufferedWriter.write("Source File Name,Dependenc File Name,Number Of Lines,Number of Methods \n");
			bufferedWriter.newLine();
			for (final String sourceFileName : dependencies.keySet()) {
				final List<DependencyExtractorResponse> depedencies = dependencies.get(sourceFileName);

				for (final DependencyExtractorResponse dependency : depedencies) {
					bufferedWriter.write(sourceFileName + ", " + dependency.getMatchedDependencyFileName() + " ,"
							+ dependency.getNumberOfLines() + " ," + dependency.getNumberOfMethods());
					bufferedWriter.newLine();
					System.out.println(sourceFileName + " ," + dependency.getMatchedDependencyFileName() + " ,"
							+ dependency.getNumberOfLines() + " ," + dependency.getNumberOfMethods());
				}
			}

		} catch (final IOException io) {
			io.printStackTrace();
			throw new UnableToExtractException(io);

		}
		System.out.println("Report generation complete at Path:" + reportFile.toString());
		return reportFile.toString();
	}

	public Map<String, List<DependencyExtractorResponse>> getExtractedDependenciesMap() {
		return this.extractDependenciesMap;
	}

}
