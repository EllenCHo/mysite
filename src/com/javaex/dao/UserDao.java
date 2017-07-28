package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

//이메일과 비밀번호가 맞는 회원의 no, name을 UserVo에 넣어서 리턴
public class UserDao {
	public int Update(UserVo vo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = -1;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			if(vo.getPassword().length() == 0 ) {
				String query = "update users "
								+ "set name = ?, gender = ? "
								+ "where no = ?";
				pstmt = conn.prepareStatement(query);
	
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setInt(3, vo.getNo());
			} else {
				String query = "update users "
						+ "set name = ?, password = ?, gender = ? "
						+ "where no = ?";
				pstmt = conn.prepareStatement(query);
				System.out.println("비번수정");
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getGender());
				pstmt.setInt(4, vo.getNo());
			}
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 처리");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return 1;
	}

	public UserVo getUser(int no) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		UserVo vo = null;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select no, name, email, gender " + "from users " + "where no = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			// 4.결과처리
			if (rs.next()) {
				vo = new UserVo();
				vo.setNo(rs.getInt("no"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setGender(rs.getString("gender"));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return vo;
	}

	public UserVo getUser(String email, String password) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		UserVo vo = null;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select no, name from users where email = ? and password = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			// 4.결과처리
			if (rs.next()) {
				vo = new UserVo();
				vo.setNo(rs.getInt("no"));
				vo.setName(rs.getString("name"));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return vo;
	}

	public int insert(UserVo vo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = -1;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into users values (seq_users_no.nextval, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 처리");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return count;
	}

}
