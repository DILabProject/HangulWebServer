package di.cs.skuniv.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import di.cs.skuniv.connect.ConnectDB;
import di.cs.skuniv.model.DayVo;
import di.cs.skuniv.model.DayWordVo;
import di.cs.skuniv.model.JudgeVo;
import di.cs.skuniv.model.LevelVo;
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


	public List<Map<String, Object>> getUserLearningList(String id) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("LearningDay.getUserLearningList", id);
	}

	public void updateStudyCheck(StudyListVo studyListVo) {
		Gson gson = new Gson();
		System.out.println(gson.toJson(studyListVo));
		update("LearningDay.updateStudyCheck",studyListVo);
	}

	
	public List<StudyListVo> getDateLearningWordList(StudyListVo studyListVo) {
		return (List<StudyListVo>) selectList("LearningDay.getDateWordList",studyListVo);
	}

	public List<StudyListVo> getDateLearningWordListByDate(StudyListVo studyListVo) {
		return (List<StudyListVo>) selectList("LearningDay.getDateWordListByDate",studyListVo);
		
	}

	public void createWordByDate(StudyListVo studyListVo) {
		insert("LearningDay.createWordByDate", studyListVo);
		
	}

	public List<DayVo> getDayList() {
		return (List<DayVo>)selectList("Day.getDayList");
	}

	public List<DayWordVo> getDayWordList(String day) {
		return (List<DayWordVo>)selectList("DayWord.getDayWordList",day);
	}

	public void createDay(DayVo dayVo) {
		insert("Day.create", dayVo);
	}

	public void addWord(DayWordVo dayWordVo) {
		insert("DayWord.create", dayWordVo);
	}

	public void deleteWord(DayWordVo dayWordVo) {
		delete("DayWord.delete", dayWordVo);
		
	}

	public void addStudyUserVo(StudyListVo studyListVo) {
		insert("LearningDay.addStudyUser", studyListVo);
	}

	public void removeLearning(StudyListVo studyListVo) {
		delete("LearningDay.delete", studyListVo);
		
	}

	public List<StudyListVo> getLeanrningDay() {
		return (List<StudyListVo>)selectList("LearningDay.getLearningDay");
	}

	public void insertUserOnLeanrningDay(StudyListVo studyListVo) {
		insert("LearningDay.userOnLeanrningDay", studyListVo);
	}

	public List<UserVo> selectUser() {
		return (List<UserVo>)selectList("User.selectUser");
	}

	public List<LevelVo> getLevelList(String id) {
		return (List<LevelVo>)selectList("LearningDay.selectLevel",id);
	}

	public List<StudyListVo> getWordList(StudyListVo studyListVo) {
		return (List<StudyListVo>)selectList("LearningDay.getWordList",studyListVo);
	}
}
