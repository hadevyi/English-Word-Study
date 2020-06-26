package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.VocaDAO.VocaDTO;
/**
 * 단어 DAO
 * */
public class VocaDAO extends DAO {
	/*모든sub DAO클래스는 DAO클래스를 상속받는다.
	 * DAO는 CRUD작업 및 DB연결을 위한 추상클래스이다.
	 * */
	public VocaDAO() {
		super();
	}
	public static class VocaDTO{
		private String voca, mean;
		private int idx;
		public VocaDTO(int idx,String voca, String mean) {
			super();
			this.voca = voca;
			this.mean = mean;
			this.idx =idx;
		}
	
		public int getIdx() {
			return idx;
		}
		public void setIdx() {
			this.idx=idx;
		}
		public String getVoca() {
			return voca;
		}

		public void setVoca(String voca) {
			this.voca = voca;
		}

		public String getMean() {
			return mean;
		}

		public void setMean(String mean) {
			this.mean = mean;
		}
	}

	@Override
	public void create(Object voca) {
		VocaDTO theVoca= (VocaDTO)voca;
		String sql ="INSERT INTO voca(voca,mean) VALUES('"+theVoca.voca+"','"+theVoca.mean+"');";
		try {
			int result = st.executeUpdate(sql);

			System.out.println("등록된 데이터 갯수 : "+result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public VocaDTO update(String sql) {
		
		try {
			int result = st.executeUpdate(sql);
			
			System.out.println("업데이트 row "  + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	/** type : 읽기종류 default : 공부할것읽기 failed : 틀린거읽기 */
	public ArrayList<VocaDTO> read(String type) {
		ArrayList<VocaDTO> result= new ArrayList<VocaDTO>();
		try {
			String sql ="";
			
			if(type.equals("default")) {
				//학습 안 한 단어를 랜덤으로 추출
				sql="SELECT * FROM voca WHERE passed is null or passed = 1 order by rand() limit 30;";
			}else if(type.equals("failed")){
				//틀린 단어
				sql="SELECT * FROM voca WHERE passed = 0;";
			}else if(type.equals("random")) {
				//틀린 단어를 랜덤으로 추출
				sql="SELECT * FROM voca WHERE passed = 0 order by rand() limit 30;";	
			}
			else {
				//단어검색
				sql="SELECT * FROM voca WHERE voca LIKE '"+type+"';";
			}
			rs = st.executeQuery(sql);

			//결과를 저장할 변수
			
			while (rs.next()) {			
				result.add(new VocaDTO(rs.getInt("idx"), rs.getString("voca"),  rs.getString("mean")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void delete(String sql) {

	}
}
