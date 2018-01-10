package di.cs.skuniv.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import di.cs.skuniv.connect.ConnectDB;

@SuppressWarnings("unchecked")
@Repository("HangulDao")
public class HangulDao extends ConnectDB {

	public List<Map<String, Object>> getCho(int cho) {
		
		return (List<Map<String, Object>>)selectList("cho.select_cho_List", cho);
	}

	public List<Map<String, Object>> getJun(int jun) {
		return (List<Map<String, Object>>)selectList("jun.select_jun_List", jun);
	}

	public List<Map<String, Object>> getJon(int jon) {
		return (List<Map<String, Object>>)selectList("jon.select_jon_List", jon);
	}

}
