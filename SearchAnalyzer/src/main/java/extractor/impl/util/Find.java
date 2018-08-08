package extractor.impl.util;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Find {

	public static class Finder extends SimpleFileVisitor<Path> {

		private final PathMatcher matcher;

		private int numMatches = 0;

		private List<Path> listOfMatchedPath = null;

		public Finder(final String pattern) {

			this.matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
			this.listOfMatchedPath = new ArrayList();

		}

		// Print total number of matches to standarad out.
		public void done() {
			System.out.println("Matched: " + this.numMatches);
		}

		void find(final Path file) {
			final Path name = file.getFileName();

			if (null != name && this.matcher.matches(name)) {
				this.numMatches++;
				this.listOfMatchedPath.add(file);
			}
		}

		public List<Path> getListOfPathMatched() {
			return this.listOfMatchedPath;
		}

		// Invoke the pattern matching method on each directory
		@Override
		public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) {
			this.find(dir);
			return FileVisitResult.CONTINUE;
		}

		// Invoke the pattern matching method on each file
		@Override
		public FileVisitResult visitFile(final Path dir, final BasicFileAttributes attrs) {
			this.find(dir);
			return FileVisitResult.CONTINUE;
		}

		// Invoke the pattern matching method on each file
		@Override
		public FileVisitResult visitFileFailed(final Path dir, final IOException exc) {
			System.err.println(exc);
			return FileVisitResult.CONTINUE;
		}
	}

	public static void main(final String args[]) throws IOException {

		if ((args.length < 3) || !args[1].equals("-name")) {
			Find.usage();
		}

		final Path startingDir = Paths.get(args[0]);
		final String pattern = args[2];

		final Finder finder = new Finder(pattern);
		Files.walkFileTree(startingDir, finder);
		finder.done();

	}

	static void usage() {
		System.err.println("java Find <path>" + " -name \"<glob_pattern>\"");
		System.exit(-1);
	}

}
