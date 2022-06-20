package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;


public class KosmoDao {
	
	
	public KosmoDao() {
		
	}

	// 멤버 필드
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	public KosmoDao(ServletContext context) {
		try {
			// 커넥션풀 사용하기
			Context ctx  = new InitialContext();
			DataSource source=(DataSource)ctx.lookup(context.getInitParameter("JNDI-ROOT")+"/kosmo");
			conn = source.getConnection();	
		} 
		catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
		// 자원 반납
		public void close() {
			try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			// 사용된 커넥션 객체 반납하기
			if(conn !=null) conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		}
		
	
		
		// 회원여부 판단		
		public boolean isLogin(String id, String password) {
			boolean isMember = false;
			String sql="SELECT COUNT(*) FROM member WHERE id=? AND password=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1,id);
				psmt.setString(2,password);
				rs = psmt.executeQuery();
				rs.next();
				if(rs.getInt(1)==0) return false;
			}
			catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}///////////isLogin
		
		// 회원가입
		public int memberInsert(MemberDto dto) {
			int affected=0;
			String sql="INSERT INTO member VALUES(?,?,?,?,?,?,?,DEFAULT)";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getPassword());
				psmt.setString(3, dto.getName());
				psmt.setString(4, dto.getGender());
				psmt.setString(5, dto.getInter());
				psmt.setString(6, dto.getGrade());
				psmt.setString(7, dto.getSelf());
				affected = psmt.executeUpdate();
			}
			catch(SQLException e) {e.printStackTrace();}
			return affected;
		}//////////////insert
		
		/*public List<MemberDto> member(String id) {
			List<MemberDto> lists= new Vector<>();	
			String sql = "select * from member where id=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				rs=psmt.executeQuery();
				if(rs.next()) {
					MemberDto dto = new MemberDto();
					dto.setId(rs.getString(1));
					dto.setPassword(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setGender(rs.getString(4));
					dto.setInter(rs.getString(5));
					dto.setGrade(rs.getString(6));
					dto.setSelf(rs.getString(7));
					lists.add(dto);
					System.out.println("우아아라:"+lists.size());
				}
				else {
					System.out.println();
				}
			}
			catch(SQLException e) {e.printStackTrace();}

			return lists;
		}//////////////insert*/
		
		// 상세보기용
		public MemberDto member(String id) {
			MemberDto dto=null;	
				try {
					// 조회 수 업데이트
					psmt= conn.prepareStatement("select * from member where id=?");
					psmt.setString(1, id);
					rs = psmt.executeQuery();			
					if(rs.next()) {
						dto = new MemberDto();
						dto.setId(rs.getString(1));
						dto.setPassword(rs.getString(2));
						dto.setName(rs.getString(3));
						dto.setGender(rs.getString(4));
						dto.setInter(rs.getString(5));
						dto.setGrade(rs.getString(6));
						dto.setSelf(rs.getString(7));
					}
				}
				catch(SQLException e) {e.printStackTrace();}
				return dto;
			}///////////////////
		
		// 회원정보 수정용
		public int memberUpdate(MemberDto dto) {
			int affected=0;
			String sql = "UPDATE member SET password=?,name=?,gender=?,inter=?,grade=?,self=? WHERE id=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getPassword());
				System.out.println(dto.getPassword());
				psmt.setString(2, dto.getName());
				System.out.println(dto.getName());
				psmt.setString(3, dto.getGender());
				System.out.println(dto.getGender());
				psmt.setString(4, dto.getInter());
				System.out.println(dto.getInter());
				psmt.setString(5, dto.getGrade());
				System.out.println(dto.getGrade());
				psmt.setString(6, dto.getSelf());
				System.out.println(dto.getSelf());
				psmt.setString(7, dto.getId());
				System.out.println(dto.getId());
				psmt.executeQuery();
							
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return affected;
		}
		
		// 전체 목록 가져오기
		public List<BoardDto> selectList(Map map){
			List<BoardDto> records= new Vector<>();
			//String sql="SELECT b.*, name FROM Board b JOIN member m ON b.id=m.id ORDER BY Boardno DESC";
			String sql="SELECT * FROM (SELECT T.*,ROWNUM R FROM (SELECT b.*,name FROM Board b JOIN member m ON b.id=m.id ORDER BY Boardno DESC) T) WHERE R BETWEEN ? AND ?";
			try {
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, map.get("start").toString());			
				psmt.setString(2, map.get("end").toString());
				rs=psmt.executeQuery();
				while (rs.next()) {
					BoardDto dto =new BoardDto();
					dto.setBoardno(rs.getString(1));
					dto.setId(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setHitCount(rs.getString(5));
					dto.setPostDate(rs.getDate(6));
					dto.setName(rs.getString(7));
					records.add(dto);
				}
			} catch (SQLException e) {e.printStackTrace();}
			
			return records;
		}////////////////selectList
		
		// 입력용
		public int insert(BoardDto dto) {
			int affected=0;
			String sql="INSERT INTO Board VALUES(SEQ_BOARD.NEXTVAL,?,?,?,0,DEFAULT)";
			try {
				psmt= conn.prepareStatement(sql, new String[] {"Boardno"}); 
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getTitle());
				psmt.setString(3, dto.getContent());
				affected=psmt.executeUpdate();
				// 입력된 행의 키값 가져오기		
				rs=psmt.getGeneratedKeys();
			}
			catch(SQLException e) {e.printStackTrace();}
			
			return affected;
		}/////////////////
		
		
		// 상세보기용
		public BoardDto selectOne(String boardno) {
			BoardDto dto=null;	
				try {
					// 조회 수 업데이트
					psmt= conn.prepareStatement("UPDATE Board SET hitcount=hitcount+1 WHERE Boardno=?");
					psmt.setString(1, boardno);
					psmt.executeUpdate();
					// 상세보기 
					String sql="SELECT b.*,name FROM Board b JOIN member m ON m.id=b.id WHERE b.Boardno=?";
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, boardno);
					rs = psmt.executeQuery();			
					if(rs.next()) {
						dto = new BoardDto();
						dto.setBoardno(rs.getString(1));
						dto.setId(rs.getString(2));
						dto.setTitle(rs.getString(3));
						dto.setContent(rs.getString(4));
						dto.setHitCount(rs.getString(5));
						dto.setPostDate(rs.getDate(6));
						dto.setName(rs.getString(7));
					}
				}
				catch(SQLException e) {e.printStackTrace();}
				return dto;
			}///////////////////

		// 수정용
		public int update(BoardDto dto) {
			int affected=0;
			String sql="UPDATE Board SET title=?,content=? WHERE Boardno=?";
			try {	
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getTitle());
				psmt.setString(2, dto.getContent());
				psmt.setString(3, dto.getBoardno());
				affected=psmt.executeUpdate();		
				
			}
			catch(SQLException e) {e.printStackTrace();}
			System.out.println(affected);
			return affected;
		}/////////////////
		
		// 삭제용
		public int delete(String boardno) {
			int affected=0;
			String sql="DELETE FROM Board WHERE Boardno=?";
			try {	
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, boardno);
				
				affected=psmt.executeUpdate();			
			}
			catch(SQLException e) {e.printStackTrace();}
			
			return affected;
		}/////////////////
		
		
		
		// 이전글,다음글 얻기
		public Map<String,BoardDto> prevNext(String currentNo){	
			Map<String,BoardDto> map = new HashMap<>();
			try {
				// 이전글 얻기
				String sql="SELECT Boardno,title FROM Board WHERE no=(SELECT MIN(Boardno) FROM Board WHERE Boardno > ?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, currentNo);
				rs = psmt.executeQuery();
				if(rs.next()) { // 이전글이 있는 경우
					map.put("PREV", new BoardDto(rs.getString(1), null, rs.getString(2), null, null, null, null));
				}
				// 다음글 얻기
				sql="SELECT Boardno,title FROM Board WHERE Boardno=(SELECT MAX(Boardno) FROM Board WHERE Boardno < ?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, currentNo);
				rs = psmt.executeQuery();
				if(rs.next()) { // 다음글이 있는 경우
					map.put("NEXT", new BoardDto(rs.getString(1), null, rs.getString(2), null, null, null, null));
				}
				
			}
			catch(SQLException e) {e.printStackTrace();}
			
			return map;
		}
		
		// 총 레코드 수 얻기용
		public int getTotalRecordCount(Map map) {
			int totalRecordCount=0;
			String sql="SELECT COUNT(*) FROM board b JOIN member m ON m.id=b.id ";
			try {
				psmt = conn.prepareStatement(sql);
				rs=psmt.executeQuery();
				rs.next();
				totalRecordCount= rs.getInt(1);
			}
			catch(SQLException e) {e.printStackTrace();}		
			return totalRecordCount;
		}////////////////getTotalRecordCount
		public boolean isCorrectPassword(String boardno, String password) {
			String sql="SELECT COUNT(*) FROM dataroom WHERE no=? AND password=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, boardno);
				psmt.setString(2,password);
				rs=psmt.executeQuery();
				rs.next();
				if(rs.getInt(1)==0) return false;
			} 
			catch (SQLException e) {e.printStackTrace();return false;}
			return true;
		}
		

		
}
