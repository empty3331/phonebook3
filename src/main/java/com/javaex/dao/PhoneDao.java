package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {
	
		@Autowired
		private SqlSession sqlSession;
	
	
		// 사람 리스트(검색할때)
		public List<PersonVo> getPersonList() {
			List<PersonVo> personList = sqlSession.selectList("phonebook.getList");
			System.out.println(personList.toString());
			return personList;
		}

		// 사람 추가
		public int personInsert(PersonVo personVo) {
			int count = sqlSession.insert("phonebook.insert", personVo);
			return count;
		}
		
		// 사람 추가2
		public int personInsert2(String name, String hp, String company) {
			Map<String, Object> pMap = new HashMap<String, Object>();
			pMap.put("name", name);
			pMap.put("hp", hp);
			pMap.put("company", company);
			int count = sqlSession.insert("phonebook.insert2", pMap);
			return count;
		}
		
		// 사람정보
		public PersonVo getPerson(int personId) {
			System.out.println("doo.getPerson");
			PersonVo personVo = sqlSession.selectOne("phonebook.selectById",personId);
			System.out.println(personVo.toString());
			return personVo;

		}
		
		// 사람정보2
		public Map<String,Object> getPerson2(int personId){
			System.out.println(personId);
			Map<String,Object> personMap =sqlSession.selectOne("phonebook.selectById2", personId);
			
			System.out.println(personMap.get("PERSON_ID"));
			System.out.println(personMap.get("NAME"));
			System.out.println(personMap.get("HP"));
			System.out.println(personMap.get("COMPANY"));
			return personMap;
		}
		
		
		// 사람 수정
		public int personUpdate(PersonVo personVo) {
			int count = sqlSession.update("phonebook.update", personVo);
			return count;
		}
		
		// 사람 삭제
		public int personDelete(int personId) {
			System.out.println(personId);
			int count = sqlSession.delete("phonebook.delete",personId);
			
			System.out.println(count);
			return count;
		}
		
		/*
		
		// 사람 수정
		public int personUpdate(PersonVo personVo) {
			int count = 0;
			getConnection();

			try {

				// 3. SQL문 준비 / 바인딩 / 실행
				String query = ""; // 쿼리문 문자열만들기, ? 주의
				query += " update person ";
				query += " set name = ? , ";
				query += "     hp = ? , ";
				query += "     company = ? ";
				query += " where person_id = ? ";

				pstmt = conn.prepareStatement(query); // 쿼리로 만들기

				pstmt.setString(1, personVo.getName()); // ?(물음표) 중 1번째, 순서중요
				pstmt.setString(2, personVo.getHp()); // ?(물음표) 중 2번째, 순서중요
				pstmt.setString(3, personVo.getCompany()); // ?(물음표) 중 3번째, 순서중요
				pstmt.setInt(4, personVo.getPersonId()); // ?(물음표) 중 4번째, 순서중요

				count = pstmt.executeUpdate(); // 쿼리문 실행

				// 4.결과처리
				// System.out.println(count + "건 수정되었습니다.");

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			close();
			return count;
		}

		// 사람 삭제
		public int personDelete(int personId) {
			int count = 0;
			getConnection();

			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = ""; // 쿼리문 문자열만들기, ? 주의
				query += " delete from person ";
				query += " where person_id = ? ";
				pstmt = conn.prepareStatement(query); // 쿼리로 만들기

				pstmt.setInt(1, personId);// ?(물음표) 중 1번째, 순서중요

				count = pstmt.executeUpdate(); // 쿼리문 실행

				// 4.결과처리
				// System.out.println(count + "건 삭제되었습니다.");

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			close();
			return count;
		}
		//사람정보
		public PersonVo getPerson(int personId) {
			
			PersonVo personVo = null;
			getConnection();
			try {
				String query = "";
				query += " SELECT person_id, ";
				query += "        name, ";
				query += "        hp, ";
				query += "        company ";
				query += " FROM person ";
				query += " WHERE person_id = ? ";
				
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, personId);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					int personId1 = rs.getInt("person_id");
					String name = rs.getString("name");
					String hp = rs.getString("hp");
					String company = rs.getString("company");

					personVo = new PersonVo(personId1, name, hp, company);
				}
				
			}
			catch (SQLException e) {
				System.out.println("error:" + e);
			}
			close();
			return personVo;
			
		}
		
		*/

}
