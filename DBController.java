package studentProgram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


import java.sql.ResultSet;


public class DBController {
	//데이터 입력 : insert 쿼리문
	public static int dataInsert(StudentModel sd) {
		//데이터베이스 접속
		Connection con = DBUtility.getConnection();
		
		//데이터베이스 입력 반환값
		int result = 0;
		
		//데이터베이스 연결 확인
		if(con==null) System.out.println("MySQL 연결에 실패하였습니다.");
		
		//삽입 명령문 전달(학번, 이름, 성별, 국어, 영어, 수학, 총점, 평균, 등급)
		String insertQuery = "insert into studentprogram.studentdatatbl values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			//insert query binding(id, name, gender, kor, eng, math, total, avr, grade)
			ps = (PreparedStatement)con.prepareStatement(insertQuery);
			ps.setString(1, sd.getId());
			ps.setString(2, sd.getName());
			ps.setString(3, sd.getGender());
			ps.setInt(4, sd.getKor());
			ps.setInt(5, sd.getEng());
			ps.setInt(6, sd.getMath());
			ps.setInt(7, sd.getTotal());
			ps.setDouble(8, sd.getAvr());
			//등급 : 문자형을 String 값으로 변환
			ps.setString(9, String.valueOf(sd.getGrade()));		
			
			//쿼리 실행 명령
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//결과값 반환
		return result;
	}
	
	//데이터 조회 : select 쿼리문
	public static List<StudentModel> dataSearch(String searchData, int num) {
		final int  NAME=1, ID=2;
		//데이터베이스 접속
		Connection con = DBUtility.getConnection();

		//데이터베이스 연결 확인
		if(con==null) System.out.println("MySQL 연결에 실패하였습니다.");
		
		//데이터베이스 입력 반환값
		List<StudentModel> list = new ArrayList<StudentModel>();
		ResultSet resultSet = null;
		
		//조회 명령문 전달(학번, 이름)
		String searchQuery = null; 
		PreparedStatement ps = null;
		
		try {
			switch(num) {
			case NAME : 
				searchQuery = "select* from studentprogram.studentdatatbl where name=?";	
				ps = (PreparedStatement)con.prepareStatement(searchQuery);
				ps.setString(1, searchData); break;
			case ID : 
				searchQuery = "select* from studentprogram.studentdatatbl where id=?"; 
				ps = (PreparedStatement)con.prepareStatement(searchQuery);
				ps.setInt(2, Integer.parseInt(searchData));	break;
			}
			//쿼리 실행 명령
			resultSet = ps.executeQuery();
			
			//resultSet을 list에 저장함수 호출
			resultSetToList(resultSet, list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close(); ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public static List<StudentModel> dataSelect() {
		Connection con = DBUtility.getConnection();

		if(con==null) System.out.println("MySQL 연결에 실패하였습니다.");
		
		List<StudentModel> list = new ArrayList<StudentModel>();
		ResultSet resultSet = null;
		
		String selectQuery = null; 
		PreparedStatement ps = null;
		
		try {
			selectQuery = "select* from studentprogram.studentdatatbl"; 
			ps = (PreparedStatement)con.prepareStatement(selectQuery);
			
			//쿼리 실행 명령
			resultSet = ps.executeQuery();
			
			//resultSet을 list에 저장함수 호출
			resultSetToList(resultSet, list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close(); ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//결과값 반환
		return list;
	}

	public static List<StudentModel> dataSort(int menu) {
		final int NAME=1, TOTAL=2;
		//데이터베이스 접속
		Connection con = DBUtility.getConnection();

		//데이터베이스 연결 확인
		if(con==null) System.out.println("MySQL 연결에 실패하였습니다.");
		
		//데이터베이스 입력 반환값
		List<StudentModel> list = new ArrayList<StudentModel>();
		ResultSet resultSet = null;
		
		String sortQuery = null; 
		PreparedStatement ps = null;
		
		switch(menu) {
		case NAME: sortQuery = "select* from studentprogram.studentdatatbl order by name"; break;
		case TOTAL:	sortQuery = "select* from studentprogram.studentdatatbl order by total desc"; break;
		}
		
		try {
			//쿼리 실행 명령
			ps = (PreparedStatement)con.prepareStatement(sortQuery);
			resultSet = ps.executeQuery();
			
			//resultSet을 list에 저장함수 호출
			resultSetToList(resultSet, list);
			
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close(); ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//결과값 반환
		return list;
	}
	
	//resultSet을 list에 저장
	private static void resultSetToList(ResultSet resultSet, List<StudentModel> list) {
		try {
			while(resultSet.next()) {
				String id = resultSet.getString(1);
				String name = resultSet.getString(2);
				String gender = resultSet.getString(3);
				int kor = resultSet.getInt(4);
				int eng = resultSet.getInt(5);
				int math = resultSet.getInt(6);
				int total = resultSet.getInt(7);
				double avr = resultSet.getDouble(8);
				char grade = resultSet.getString(9).charAt(0);
				

				StudentModel sd = new StudentModel(id, name, gender, kor, eng, math, total, avr, grade);
				list.add(sd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//데이터 수정
	public static int dataUpdate(StudentModel sd, int menu) {
		final int KOR=1, ENG=2, MATH=3;
		//데이터베이스 접속
		Connection con = DBUtility.getConnection();
		
		//데이터베이스 입력 반환값
		int result = 0;
		
		//데이터베이스 연결 확인
		if(con==null) System.out.println("MySQL 연결에 실패하였습니다.");
		
		//수정 명령문 전달
		String updateQuery = null;
		PreparedStatement ps = null;

		try {
			//update query binding
			switch(menu) {
			case KOR: 
				updateQuery = "update studentprogram.studentdatatbl set kor=?, total=?, avr=?, grade=? where id=?"; 
				ps = (PreparedStatement)con.prepareStatement(updateQuery);
				ps.setInt(1, sd.getKor()); break;
			case ENG: 
				updateQuery = "update studentprogram.studentdatatbl set eng=?, total=?, avr=?, grade=? where id=?";
				ps = (PreparedStatement)con.prepareStatement(updateQuery);
				ps.setInt(1, sd.getEng()); break;
			case MATH: 
				updateQuery = "update studentprogram.studentdatatbl set math=?, total=?, avr=?, grade=? where id=?"; 
				ps = (PreparedStatement)con.prepareStatement(updateQuery);
				ps.setInt(1, sd.getMath());	break;
			}
			
			ps.setInt(2, sd.getTotal());
			ps.setDouble(3, sd.getAvr());
			ps.setString(4, String.valueOf(sd.getGrade()));
			ps.setString(5, sd.getId());
			
			//쿼리 실행 명령
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close(); ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//결과값 반환
		return result;
	}
	
	//데이터 삭제
	public static int dataDelete(String id) {
		//데이터베이스 접속
		Connection con = DBUtility.getConnection();
		
		//데이터베이스 입력 반환값
		int result = 0;
		
		//데이터베이스 연결 확인
		if(con==null) System.out.println("MySQL 연결에 실패하였습니다.");
		
		//삭제 명령문 전달(학번)
		String deleteQuery = "delete from studentprogram.studentdatatbl where id=?";
		PreparedStatement ps = null;
		
		try {
			//delete query binding
			ps = (PreparedStatement)con.prepareStatement(deleteQuery);
			ps.setString(1, id);
			
			//쿼리 실행 명령
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close(); ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//결과값 반환
		return result;
	}
	
}

