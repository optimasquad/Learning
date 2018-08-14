/**
 * 
 */
package com.bfm.analyzer;

import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hp
 *
 */
@Component
public class BQLAnalyzerAndReplace implements DependencyExtractor {

	@Autowired
	IArticleService articleService;

	private static final boolean isPosix = FileSystems.getDefault().supportedFileAttributeViews().contains("posix");

	public Map<String, List<DependencyExtractorResponse>> extractDependenciesMap = new HashMap<String, List<DependencyExtractorResponse>>();

	@Override
	public Map<String, List<DependencyExtractorResponse>> extractDependecies(Criteria criteria) {

		final ExecutorService executor = Executors.newSingleThreadExecutor();

		final Future<Map<String, List<DependencyExtractorResponse>>> future = executor
				.submit(new Callable<Map<String, List<DependencyExtractorResponse>>>() {

					@Override
					public Map<String, List<DependencyExtractorResponse>> call() {

						// Perform the steps of calling the database and updating the database

						// Various tables need to be called ..If the input is there that particular
						// table need to be called..with the value of the column

						// Step 1 Calling the database for the selected column and the table

						List<String> tableName = Stream.of(criteria.getTableName().split(",")).map(String::trim)
								.collect(Collectors.toList());

						List<String> columnName = Stream.of(criteria.getColumnName().split(",")).map(String::trim)
								.collect(Collectors.toList());

						final Map<String, List<DependencyExtractorResponse>> extractDependecies = BQLAnalyzerAndReplace.this
								.extractDependencies(tableName, columnName);

						BQLAnalyzerAndReplace.this.getExtractedDependenciesMap().putAll(extractDependecies);

						// provide the flag if we need to update and see the results now

						// persist the details in the temp table

						if (criteria.isReplace()) {

							// Update the database table and do the stuff
						}

						return extractDependecies;
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

	@Override
	public String reportDependencies(Map<String, List<DependencyExtractorResponse>> dependencies, String reportPath) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * 
	 * @param tableNameList
	 * @param columnName
	 * @return
	 */
	protected Map<String, List<DependencyExtractorResponse>> extractDependencies(final List<String> tableNameList,
			final List<String> columnName) {

		final Map<String, List<DependencyExtractorResponse>> extractedDependencies = new HashMap<String, List<DependencyExtractorResponse>>();
		Map<String, List<DependencyExtractorResponse>> extractedDependenciesForOneTable = null;
		for (final String tableName : tableNameList) {

			if (this.isDependenciesAlreadyExtracted(tableName)) {
				continue;
			}
			extractedDependenciesForOneTable = this.extractDependencies(tableName, columnName);
			extractedDependencies.putAll(extractedDependenciesForOneTable);
		}
		return extractedDependencies;

	}

	/**
	 * 
	 * 
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	private Map<String, List<DependencyExtractorResponse>> extractDependencies(final String tableName,
			final List<String> columnName) {

		final Map<String, List<DependencyExtractorResponse>> extractedDependencies = new HashMap<String, List<DependencyExtractorResponse>>();
		// calling the database

		List<Article> listOfArticle = articleService.getAllArticles();

		List<DependencyExtractorResponse> response = listOfArticle.stream().filter(article -> article != null)
				.map(p -> new DependencyExtractorResponse(p.getTitle(), p.getCategory(), p.getArticleId()))
				.collect(Collectors.toList());
		extractedDependencies.put("Article", response);
		return extractedDependencies;

	}

	/**
	 * 
	 * 
	 * @param tableName
	 * @return
	 */
	private boolean isDependenciesAlreadyExtracted(final String tableName) {
		if (null == tableName) {
			return false;
		}
		final String searchString = tableName;

		return this.getExtractedDependenciesMap().containsKey(searchString);
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public Map<String, List<DependencyExtractorResponse>> getExtractedDependenciesMap() {
		return this.extractDependenciesMap;
	}

	/**
	 * 
	 * 
	 * @param pool
	 * @param timeout
	 */
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

}
