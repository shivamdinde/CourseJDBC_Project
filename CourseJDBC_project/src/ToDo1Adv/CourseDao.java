package ToDo1Adv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class CourseDao implements DaoInterface<CourseProject, Integer> {

	@Override
	public Collection<CourseProject> getAll() {
		String sqlQuery = "select course_id, course_title, course_duration, course_provider,course_fees from CourseProjectAdv_master";
		Collection<CourseProject> allCourses = new ArrayList<>();
		try(
				Connection conn = JdbcUtils.buildConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlQuery);
				){
			while(rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				int duration = rs.getInt(3);
				String provider = rs.getString(4);
				int fees = rs.getInt(5);
				
				//Populating a Restaurant object based upon the values fetched from the record.
				CourseProject currentCourse = new CourseProject(id, title, duration, provider,fees);
				//Adding this Restaurant object into the Collection
				allCourses.add(currentCourse);
				
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		 
		return allCourses;
	}

	@Override
	public CourseProject getOne(Integer cid) {
		// TODO Auto-generated method stub
		CourseProject foundCourse = null;
		try(
				Connection conn = JdbcUtils.buildConnection();
				){
			String sqlQuery = "select course_id, course_title, course_duration, course_provider,course_fees from CourseProjectAdv_master where course_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
			pstmt.setInt(1, cid);
			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				int duration = rs.getInt(3);
				String provider = rs.getString(4);
				int fees = rs.getInt(5);
				
				foundCourse = new CourseProject(id, title, duration, provider,fees);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return foundCourse;
	}

}
