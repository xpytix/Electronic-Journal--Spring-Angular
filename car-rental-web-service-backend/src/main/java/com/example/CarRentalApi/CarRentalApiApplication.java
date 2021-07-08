package com.example.CarRentalApi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Arrays;

@SpringBootApplication
public class CarRentalApiApplication {

//	@Autowired
//	private static StudentRepository studentRepository;



	public static void main(String[] args) {
		SpringApplication.run(CarRentalApiApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public static void init()
//	{
//		studentRepository.saveAll(Arrays.asList(
//			new Student("siemka@gmail.com", "Szymon", "Erka", "13.02.1999"),
//				new Student("siemka@gmail.com", "Maciek", "Kowalski", "13.02.1999"),
//				new Student("siemka@gmail.com", "Dominik", "Pieta", "13.02.1999")
//				)
//
//		);
//
//	}
}
