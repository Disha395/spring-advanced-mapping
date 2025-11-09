package com.mapping.mappingdemo;

import com.mapping.mappingdemo.dao.AppDAO;
import com.mapping.mappingdemo.entity.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
			//deleteInstructorDetail(appDAO);
			//createInstructorWithCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCoursesForInstructor(appDAO);
			//findInstructorWithCoursesJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourses(appDAO);
			//deleteCourse(appDAO);
			//createCourseAndReviews(appDAO);
			//retrieveCourseAndReviews(appDAO);
			//deleteCourseAndReviews(appDAO);
			//createCourseAndStudents(appDAO);
			//findCourseAndStudentByCourseId(appDAO);
			//findStudentCourseByStudentId(appDAO);
			//addMoreCoursesForStudent(appDAO);
			//deleteCourse(appDAO);
			deleteStudent(appDAO);
		};

	}

	private void deleteStudent(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting Student id : " + theId);
		appDAO.deleteStudentById(theId);
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int id = 2;
		Student tempStudent = appDAO.findStudentAndCourseByStudentId(id);
		Course course1 = new Course("How to operate doors to reach destination");
		Course course2 = new Course("How to use smoke to hide and vanish");

		tempStudent.addCourse(course1);
		tempStudent.addCourse(course2);

		appDAO.update(tempStudent);

		System.out.println("Done");
	}

	private void findStudentCourseByStudentId(AppDAO appDAO) {
		int id = 2;
		Student student = appDAO.findStudentAndCourseByStudentId(id);
		System.out.println("Student : " + student);
		System.out.println("Courses + " + student.getCourses());
		System.out.println("Done");
	}

	private void findCourseAndStudentByCourseId(AppDAO appDAO) {
		int id = 10;
		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(id);

		System.out.println("Loaded course : " + tempCourse);
		System.out.println("Students : " + tempCourse.getStudents());

		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		//create a course
		Course tempCourse = new Course("Monsters Inc.");

		//Create a student
		Student tempStudent1 = new Student("John", "Doe", "john@mail.com");
		Student tempStudent2 = new Student("Mike", "Wazowski", "mike@gmail.com");

		//add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		//save the course and students
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("Associate students: " + tempCourse.getStudents());

		appDAO.save(tempCourse);

		System.out.println("Done");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		System.out.println("Deleting course id : " + id);
		appDAO.deleteCourseById(id);

		System.out.println("Done");

	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		//get the course and reviews
		int id = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(id);

		//print the course
		System.out.println("Course : " + tempCourse);

		//print the reviews
		System.out.println("Reviews : " + tempCourse.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		//create a course
		Course tempCourse = new Course("How to hide in the dark");

		//add some reviews
		tempCourse.addReview(new Review("Great Course - Learnt a Lot!!"));
		tempCourse.addReview(new Review("Best course out here"));
		tempCourse.addReview(new Review("Really good"));

		//save the course & leverage cascade.ALL
		System.out.println("Saving");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!");

	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;
		System.out.println("Deleting course id : " + id);

		appDAO.deleteCourseById(id);
		System.out.println("Done");
	}


	private void updateCourses(AppDAO appDAO) {
		int id = 10;

		//find course
		System.out.println("Finding Course By ID : " + id);
		Course tempCourse = appDAO.findCourseById(id);

		//update the course
		tempCourse.setTitle("How to be a Super Cool Monster!");

		appDAO.update(tempCourse);

		System.out.println("Done");
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;
		//find instructor
		System.out.println("Finding instructor id : " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		//update the instructor
		System.out.println("Updating ");
		instructor.setLastName("Wazowski");
		appDAO.update(instructor);
		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id : " + id);
		Instructor instructor = appDAO.findInstructorByJoinFetch(id);
		System.out.println("tempInstructor: " + instructor);
		System.out.println("the associated courses : " + instructor.getCourse());
		System.out.println("Done");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding Instructor id : " + id);

		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("tempInstructor : " + tempInstructor);

		//find Course for instructor
		System.out.println("Finding courses for instructor id: " + id);
		List<Course> list = appDAO.findCoursesByInstructorId(id);

		//associate the objects
		tempInstructor.setCourse(list);
		System.out.println("Courses :" + tempInstructor.getCourse());
		System.out.println("Done");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int id = 1;
		System.out.println("Finding Instructor id : " + id);



		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("tempInstructor : " + tempInstructor);
		System.out.println("Associated Courses : " + tempInstructor.getCourse());
		System.out.println("Done");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		Instructor tempInstructor = new Instructor(
				"Randall",
				"Boggs",
				"randall@gmail.com"
		);

		//create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"https://youtube.com",
				"Scaring children"
		);

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		Course tempCourse1 = new Course("How to be a Monster");
		Course tempCourse2 = new Course("How to be Scary");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		//save instructor
		//Because of CascadeType.PERSIST this will save courses also
		System.out.println("Saving instructor " + tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("The course " + tempInstructor.getCourse());

		System.out.println("Done");
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
