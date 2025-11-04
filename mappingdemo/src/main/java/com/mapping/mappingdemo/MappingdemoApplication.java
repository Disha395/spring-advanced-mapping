package com.mapping.mappingdemo;

import com.mapping.mappingdemo.dao.AppDAO;
import com.mapping.mappingdemo.entity.Instructor;
import com.mapping.mappingdemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MappingdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MappingdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			createInstructor(appDAO);
		};

	}

	private void createInstructor(AppDAO appDAO) {
		//create Instructor
		Instructor tempInstructor = new Instructor(
				"Mike",
				"Wazowski",
				"mike@gmail.com"
		);

		//create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"https://youtube.com",
				"Coding"
		);

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//save the instructor
		//NOTE: This will also save the details object
		//because of CASCADEType.ALL

		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done");

	}
}
