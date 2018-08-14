package com.bfm.analyzer;

import java.util.List;
import java.util.Map;

public interface DependencyExtractor {

	public Map<String, List<DependencyExtractorResponse>> extractDependecies(Criteria criteria);

	public String reportDependencies(Map<String, List<DependencyExtractorResponse>> dependencies, String reportPath);

}
