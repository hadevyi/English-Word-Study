package dao;

import java.util.ArrayList;

import dao.StudyListDAO.StudyListDTO;

public class StudyListDAO extends DAO {

/**
 * 문제풀이결과 DAO
 * */
	public static class StudyListDTO{
		
	}
	
	public StudyListDAO() {
		super();
	}

	@Override
	public void create(Object sql) {
		System.out.println("dd");
	}

	@Override
	public StudyListDTO update(String sql) {
		return null;
	}

	@Override
	public ArrayList<StudyListDTO> read(String sql) {
		return null;

	}

	@Override
	public void delete(String sql) {
	}
}
