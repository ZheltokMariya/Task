package com.example.task;

import com.example.task.file.WorkWithFile;
import com.example.task.model.Number;
import com.example.task.repo.NumberRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class TaskApplication {

	private static final Logger log = LoggerFactory.getLogger(TaskApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(NumberRepo numberRepo) {
		return (args) -> {
			WorkWithFile.writeRandomNumbersIntoFile();
			String[] array = WorkWithFile.readFromFile().split(",");
			int length = array.length;
			CommonResource resource = new CommonResource(numberRepo);
			UseDataBaseThread thread1 = new UseDataBaseThread("First", resource, array, 0, (length/2)-1);
			UseDataBaseThread thread2 = new UseDataBaseThread("Second", resource, array, length/2, length-1);
			thread1.start();
			thread2.start();
		};
	}
}
