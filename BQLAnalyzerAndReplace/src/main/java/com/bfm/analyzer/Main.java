package com.bfm.analyzer;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * 
 * @author hp
 * 
 *         This is the main class for the dependency
 *
 */
public class Main {

	public static void main(String args[]) {
		AnnotationConfigApplicationContext context = null;
		try {
			context = new AnnotationConfigApplicationContext(AppConfig.class);
			BQLAnalyzerAndReplace BqlAnalyzerAndReplace = context.getBean(BQLAnalyzerAndReplace.class);

			Criteria criteria = new Criteria();
			criteria.setColumnName("title");
			criteria.setTableName("articles");
			criteria.setExtractionTimeout(1000000l);
			Map<String, List<DependencyExtractorResponse>> dependencyResults = BqlAnalyzerAndReplace
					.extractDependecies(criteria);

			for (Map.Entry<String, List<DependencyExtractorResponse>> results : dependencyResults.entrySet()) {

				System.out.println("The Key is " + results.getKey());

				for (DependencyExtractorResponse dependencyExtractorResponse : results.getValue()) {
					System.out.println("Article Id " + dependencyExtractorResponse.getArticleId()+" "
							+ " Category "+dependencyExtractorResponse.getCategory()+" "+" Title " + dependencyExtractorResponse.getTitle());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			context.close();
		}

	}

}
