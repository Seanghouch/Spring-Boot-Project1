package com.example.security;

import com.example.security.core.CoreBase;
import com.example.security.source.entity.Role;
import com.example.security.source.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {

    public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
		int availableProcessor = Runtime.getRuntime().availableProcessors();
		System.out.println("Available Processor:" + availableProcessor);
		List<Integer> listIntegers = IntStream.rangeClosed(1, 5).boxed().toList();
		System.out.println("Seq Flow");
		printStream(listIntegers.stream());
		System.out.println("----------------------------------------------");
		printStream(listIntegers.parallelStream());
		testThreadPool();
		initialData();
		new RedirectView("../../resource/static/index.html");
	}
	static void initialData(){
		var user = CoreBase.userRepo.findByEmail("super-admin@gmail.com");
		if (user.isEmpty()){
			User users = User
					.builder()
					.firstName("SUPER")
					.lastName("ADMIN")
					.userName("SUPER_ADMIN")
					.email("super-admin@gmail.com")
					.password(CoreBase.passwordEncoder.encode("12345"))
					.imageUrl(null)
					.isActive(true)
					.isForceResetPassword(false)
					.isBuildIn(true)
					.build();
			CoreBase.userRepo.save(users);
		}
		Optional<User> existUserId = CoreBase.userRepo.findByUserName("SUPER_ADMIN");
//		System.out.println(existUserId);
		var role = CoreBase.roleRepo.findByRoleName("SUPER_ADMIN");
		if (role.isEmpty()){
			Role roles = Role
					.builder()
					.roleName("SUPER_ADMIN")
					.description("User Build in")
					.isActive(true)
					.isBuildIn(true)
					.userId(existUserId.get().getUserId())
					.build();
			CoreBase.roleRepo.save(roles);
		}
	}

	static void printStream(Stream<Integer> li){
		li.forEach(s ->{
			System.out.println(LocalTime.now() + " Value: " + s + " - Thread: " + Thread.currentThread().getName());
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
