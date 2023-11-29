package Todo2Adv;

import java.util.Collection;

public class CourseCRUD_Main {
	
	private static void showOneCourse() {
		DaoInterface<CourseProject, Integer> idao = new CourseDao();
		CourseProject cst = idao.getOne(102);
		if(cst != null)
			System.out.println(cst);
		else
			System.out.println("Course Object with this ID does not exist");
	}
	private static void showAllCourse() {
		DaoInterface<CourseProject, Integer> idao = new CourseDao();
		Collection<CourseProject> allAvailableCourses  = idao.getAll();
		for(CourseProject cst : allAvailableCourses)
			System.out.println(cst);
	}
	private static void createNewCourse() {
		DaoInterface<CourseProject, Integer> idao = new CourseDao();
		CourseProject cst = new CourseProject(104,"DataStructure",60,"Udemy",25000);
		idao.create(cst);
	}
	private static void updateCourse() {
		DaoInterface<CourseProject, Integer> idao = new CourseDao();
		//Obtaining the CourseProject which is to be modified
		CourseProject currentCourse = idao.getOne(104);
		//Making some changes using setters
		currentCourse.setProvider("Coursera");
		currentCourse.setFees(666666);
		//Sending this modified state to update() method to reflect the changes into the DB
		idao.update(currentCourse);
	}
	
	private static void deleteCourse() {
		DaoInterface<CourseProject, Integer> idao = new CourseDao();
		idao.deleteOne(103);
	}
	
	
	public static void main(String[] args) {
		//showAllCourse();
		//showOneCourse();
		//createNewCourse();
		//updateCourse();
		deleteCourse();
		
	}
}
