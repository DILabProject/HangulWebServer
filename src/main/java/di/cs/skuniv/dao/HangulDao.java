package di.cs.skuniv.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import di.cs.skuniv.connect.ConnectDB;
import di.cs.skuniv.model.JudgeVo;
import di.cs.skuniv.model.StudyListVo;
import di.cs.skuniv.model.UserVo;
import di.cs.skuniv.model.WrongCountVo;

import di.cs.skuniv.model.UserVo;

@SuppressWarnings("unchecked")
@Repository("HangulDao")
public class HangulDao extends ConnectDB {

	public List<Map<String, Object>> getCho(JudgeVo judgeVo) {
		return (List<Map<String, Object>>) selectList("cho.select_cho_List", judgeVo);
	}
 
	public List<Map<String, Object>> getJun(JudgeVo judgeVo) {
		return (List<Map<String, Object>>) selectList("jun.select_jun_List", judgeVo);
	}

	public List<Map<String, Object>> getJon(JudgeVo judgeVo) {
		return (List<Map<String, Object>>) selectList("jon.select_jon_List", judgeVo);
	}

	public Map<String, Object> login(UserVo userVo) {

		return (Map<String, Object>) selectOne("User.signIn", userVo);
	}

	public void signUp(UserVo userVo) {
		insert("User.signUp", userVo);
		
	}

	
	public Map<String, Object> getWrongcount(WrongCountVo wrongCount) {
		return (Map<String, Object>) selectOne("WrongCount.selectWrongCount", wrongCount);
		
	}

	public void insertWrongcount(WrongCountVo wrongCount) {
		insert("WrongCount.insertWrongCount",wrongCount);
		
	}

	public void createUserLearning(UserVo userVo) {
		insert("User.createUserLearning",userVo);
		
	}

	public List<Map<String, Object>> getUserLearningList(String id) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("LearningDay.getUserLearningList", id);
	}

	public void updateStudyCheck(StudyListVo studyListVo) {
		update("LearningDay.updateStudyCheck",studyListVo);
		
	}

	

	

}
