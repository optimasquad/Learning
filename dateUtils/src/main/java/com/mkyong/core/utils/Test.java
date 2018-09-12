package com.mkyong.core.utils;

import java.util.concurrent.CompletableFuture;

public class Test {

	public static void main(String args[]) {

		CompletableFuture<String> future = new CompletableFuture<>();
		future.thenApply(str -> {
			System.out.println("Stage 1: " + str);
			return "bar";
		});
		future.thenApply(str -> {
			System.out.println("Stage 2: " + str);
			throw new RuntimeException();
		});
		future.thenApply(str -> {
			System.out.println("Stage 3: " + str);
			return "abc";
		});
		future.exceptionally(e -> {
			System.out.println("Exceptionally");
			return null;
		});
		future.complete("foo123");

	}

}
