package extract;

import java.util.List;
import java.util.Map;

import beans.Criteria;
import beans.DependencyExtractorResponse;

public interface DependencyExtractor {

	public Map<String, List<DependencyExtractorResponse>> extractDependecies(Criteria criteria, String startingDir);

	public String reportDependencies(Map<String, List<DependencyExtractorResponse>> dependencies, String reportPath);

}
