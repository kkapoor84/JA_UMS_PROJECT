package com.ja.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseUtil {

	public String getClassID() {
		String classId = "";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager
					.getConnection("jdbc:sqlserver://172.20.2.111;DatabaseName=JAG_QA;user=sa;password=Tpg@1234");
			Statement stmt = con.createStatement();
			String query = "select newId();";
			ResultSet result = stmt.executeQuery(query);
			while (result.next()) {
				classId = result.getString(1);
				// System.out.println(classId);
			}
			con.close();
		} catch (Exception e) {

		}
		return classId;
	}

	public int generateRandomNumber() {

		int random = (int) (Math.random() * 50) + 1;
		// System.out.println(random);
		return random;

	}

	public void DeleteRecord(String classId) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager
					.getConnection("jdbc:sqlserver://172.20.2.111;DatabaseName=JAG_QA;user=sa;password=Tpg@1234");

			Statement stmt = con.createStatement();
			String query1 = "delete from [SessionEnrollment] where ProgramSessionId in ( select max (programsessionid) from programsession)";
			String query2 = "delete from ProgramSession where classnumber ='" + classId + "'"	;
			String query3 = "delete from SessionRequest where ProgramId=12";

			int a = stmt.executeUpdate(query1);
			int b = stmt.executeUpdate(query2);
			int c = stmt.executeUpdate(query3);

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("throwing exception");
			
		}
	}

	

}
