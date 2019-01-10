package com.assignment.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.assignment.bean.Grade;
import com.assignment.dao.GradeDAO;
import com.assignment.utilities.ConnFactory;

public class GradeImpl implements GradeDAO {
	public static ConnFactory cf = ConnFactory.getInstance();
	@Override
	public void createGrade(int rei_id, String gradeFormat)throws SQLException {
		Connection conn = cf.getConnection();
		ReimbursementImpl rimpl = new ReimbursementImpl();
		int gerdfermert = 0;
		switch (gradeFormat) {
		case "Letter Grade":
			gerdfermert = 1;
			break;
		case "Pass Fail":
			gerdfermert = 2;
			break;
		case "Presentation":
			gerdfermert = 3;
			break;
		case "Other":
			gerdfermert = 4;
			break;
		}
		String sql = "INSERT INTO grade (grade_id, re_id, grade_format) VALUES (GRADE_ID_SEQ.NEXTVAL, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, rei_id);
		ps.setInt(2, gerdfermert);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\t Grade CREATED");
		Grade g = readGrade(rei_id);
		tempI = g.getGradeID();
		rimpl.updateGradeID(rei_id, tempI);//adds new grade into reimbursement table	
	}

	@Override
	public Grade readGrade(int rei_id)throws SQLException {
		Connection conn = cf.getConnection();
		Grade g = null;
		String sql = "SELECT * FROM grade_view WHERE rei_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, rei_id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			g = new Grade(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
		}		
		return g;
	}

	@Override
	public void updateGrade(int rei_id, int satisfactory, int evalID) throws SQLException{
		Connection conn = cf.getConnection();
		String sql = "UPDATE grade SET EVAL_ID = ?, SATISFACTORY = ? WHERE rei_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, evalID);
		ps.setInt(2, satisfactory);
		ps.setInt(3, rei_id);
		int tempI = ps.executeUpdate();// if this doesn't work the world will blow up
		System.out.println(tempI + "\t grade UPDATED");
	}

}
