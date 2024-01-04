package com.example.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {

	public static void main(String[] args) {

		LocalDateTime ldt = LocalDateTime.parse("2017-02-02 08:59:12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(ldt);

		SpringApplication.run(SecurityApplication.class, args);
		int availableProcessor = Runtime.getRuntime().availableProcessors();
		System.out.println("Available Processor:" + availableProcessor);
		List<Integer> listIntegers = IntStream.rangeClosed(1, 5).boxed().toList();
		System.out.println("Seq Flow");
		printStream(listIntegers.stream());
		System.out.println("----------------------------------------------");
		printStream(listIntegers.parallelStream());
		testThreadPool();
	}

	static void printStream(Stream<Integer> li){
		li.forEach(s ->{
			System.out.println(LocalTime.now() + " Value: " + s + "- Thread :" + Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
	}

	static void testThreadPool(){
		ThreadPoolExecutor executor =
				(ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		executor.submit(() -> {
			Thread.sleep(1000);
			return "Task 1";
		});
		executor.submit(() -> {
			Thread.sleep(1000);
			return "Task 2";
		});
		executor.submit(() -> {
			Thread.sleep(1000);
			return "Task 3";
		});
		executor.submit(() -> {
			Thread.sleep(1000);
			return "Task 4";
		});
		executor.submit(() -> {
			Thread.sleep(1000);
			return "Task 5";
		});
		executor.submit(() -> {
			Thread.sleep(1000);
			return "Task 6";
		});
		executor.submit(() -> {
			Thread.sleep(1000);
			return "Task 7";
		});
		System.out.println("Thread Pool: " + executor.getPoolSize());
		System.out.println("Thread Queue: " + executor.getQueue().size());

	}

}
