package Todo2Adv;

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
				
				//Populating a CourseProject object based upon the values fetched from the record.
				CourseProject currentCourse = new CourseProject(id, title, duration, provider,fees);
				//Adding this CourseProject object into the Collection
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
	
	@Override
	public void create(CourseProject newCourse) {
		String sqlQuery =
		"insert into CourseProjectAdv_master(course_id, course_title, course_duration, course_provider,course_fees) values(?,?,?,?,?)";
		try(
				Connection conn = JdbcUtils.buildConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				){
				//Extracting the values from the incoming CourseProject object
				int id = newCourse.getCourseId();
				String title = newCourse.getTitle();
				int duration = newCourse.getDuration();
				String provider = newCourse.getProvider();
				int fees = newCourse.getFees();
				//Substituting the values for unknown parameters
				pstmt.setInt(1, id);
				pstmt.setString(2, title);
				pstmt.setInt(3, duration);
				pstmt.setString(4, provider);
				pstmt.setInt(5, fees);
				
				int count = pstmt.executeUpdate();
				System.out.println(count + " record inserted");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void update(CourseProject modifiedCourse) {
		String sqlQuery = "update CourseProjectAdv_master set course_title = ?, course_duration = ?, course_provider=?, course_fees=? where course_id = ?";
		try(
				Connection conn = JdbcUtils.buildConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				){
			int id = modifiedCourse.getCourseId();
			String title = modifiedCourse.getTitle();
			int duration = modifiedCourse.getDuration();
			String provider = modifiedCourse.getProvider();
			int fees = modifiedCourse.getFees();
			
			pstmt.setInt(5, id);
			pstmt.setString(1, title);
			pstmt.setInt(2, duration);
			pstmt.setString(3, provider);
			pstmt.setInt(4, fees);
			
			int count = pstmt.executeUpdate();
			System.out.println(count + " record updated");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void deleteOne(Integer id) {
		String sqlQuery = "delete from CourseProjectAdv_master where course_id = ?";
		try(
				Connection conn = JdbcUtils.buildConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				){
			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			System.out.println(count + " record deleted");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
