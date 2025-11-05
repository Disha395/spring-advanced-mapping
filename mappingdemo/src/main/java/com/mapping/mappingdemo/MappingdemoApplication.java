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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			deleteInstructorDetail(appDAO);
		};

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 4;
		appDAO.deleteInstructorDetailsById(id);
		System.out.println("Done");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		//get the instructor detail object
		//print the instructor detail
		//print the associated instructor

		int id = 2;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println(instructorDetail);
		System.out.println(instructorDetail.getInstructor());

	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Deleting instructor id : " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id : " + id);

		Instructor tempInstructor = appDAO.findInstructorById(id);
		System.out.println(tempInstructor);
		System.out.println(tempInstructor.getInstructorDetail());
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
