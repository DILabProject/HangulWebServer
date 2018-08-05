package di.cs.skuniv.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import di.cs.skuniv.dao.HangulDao;
import di.cs.skuniv.model.DrawVO;
import di.cs.skuniv.model.HangulVO;
import di.cs.skuniv.model.JudgeVo;
import di.cs.skuniv.model.LetterVo;
import di.cs.skuniv.model.StudyListVo;
import di.cs.skuniv.model.StudyListVo;
import di.cs.skuniv.model.UserVo;
import di.cs.skuniv.model.WrongCountVo;
import di.cs.skuniv.model.UserVo;

@Service("HangulService")
public class HangulService {

	private static final char[] CHO = { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145,
			0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
	/* ㄱ ㄲ ㄴ ㄷ ㄸ ㄹ ㅁ ㅂ ㅃ ㅅ ㅆ ㅇ ㅈ ㅉ ㅊ ㅋ ㅌ ㅍ ㅎ */
	private static final char[] JUN = { 0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158,
			0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163 };
	/* ㅏ ㅐ ㅑ ㅒ ㅓ ㅔ ㅕ ㅖ ㅗ ㅘ ㅙ ㅚ ㅛ ㅜ ㅝ ㅞ ㅟ ㅠ ㅡ ㅢ ㅣ */
	private static final char[] JON = { 0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a,
			0x313b, 0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147, 0x3148,
			0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
	/* X ㄱ ㄲ ㄳ ㄴ ㄵ ㄶ ㄷ ㄹ ㄺ ㄻ ㄼ ㄽ ㄾ ㄿ ㅀ ㅁ ㅂ ㅄ ㅅ ㅆ ㅇ ㅈ ㅊ ㅋ ㅌ ㅍ ㅎ */

	@Resource(name = "HangulDao")
	private HangulDao hanguldao;

	public HangulVO getHangul(String input) {

		HangulVO hangulVO = new HangulVO();

		List<List<DrawVO>> word = new ArrayList<List<DrawVO>>();
		List<List<LetterVo>> stroke = new ArrayList<List<LetterVo>>();

		String tempStr = input;
		String lastStr = "";

		System.out.println(tempStr);
		
		for (int i = 0; i < tempStr.length(); i++) {

			List<DrawVO> word_unit_jsonArray = new ArrayList<DrawVO>();
			List<LetterVo> stroke_unit = new ArrayList<LetterVo>();

			char test = tempStr.charAt(i);

			if (test >= 0xAC00) {

				char uniVal = (char) (test - 0xAC00);
				char cho = (char) (((uniVal - (uniVal % 28)) / 28) / 21);
				char jun = (char) (((uniVal - (uniVal % 28)) / 28) % 21);
				char jon = (char) (uniVal % 28);

				int intJun = (int) jun;
				int intCho = (int) cho;
				int intJon = (int) jon;
				System.out.println("" + test + "// 0x" + Integer.toHexString((char) test));
				System.out.println("" + CHO[cho] + "// 0x" + Integer.toHexString((char) cho));
				System.out.println("" + JUN[jun] + "// 0x" + Integer.toHexString((char) jun));

				// 종성이 있다면
				if ((char) jon != 0x0000) {
					System.out.println("" + JON[jon] + "// 0x" + Integer.toHexString((char) jon));
					judgmentJun(word_unit_jsonArray,stroke_unit,word,stroke,intCho,intJun,intJon,1);
					
				} else {
					judgmentJun(word_unit_jsonArray,stroke_unit,word,stroke,intCho,intJun,intJon,0);
				}
				
			}
		}
		hangulVO.setWord(word);
		hangulVO.setStroke(stroke);

		System.out.println(hangulVO);
		return hangulVO;
	}

	private void process(List<Map<String, Object>> return_db, List<DrawVO> ja) {
		for (int j = 0; j < return_db.size(); j++) {
			DrawVO jo = new DrawVO();
			jo.setX1(return_db.get(j).get("x1") + "");
			jo.setY1(return_db.get(j).get("y1") + "");
			jo.setX2(return_db.get(j).get("x2") + "");
			jo.setY2(return_db.get(j).get("y2") + "");
			ja.add(jo);
		}
	}

	public Map<String, Object> login(String id, String password) {
		// id,password가 있는 loginModel을 세운다.
		UserVo userVo = new UserVo();
		userVo.setId(id);
		userVo.setPassword(password);
		return hanguldao.login(userVo);
	}

	public void signUp(UserVo userVo) {
		hanguldao.signUp(userVo);
		hanguldao.createUserLearning(userVo);

	}

	public void wrongCount(WrongCountVo wrongCount) {
		hanguldao.insertWrongcount(wrongCount);
	}

	public List<StudyListVo> getUserLearningList(String id) {
		List<StudyListVo> studyVoList = new ArrayList<StudyListVo>();
		List<Map<String, Object>> list = hanguldao.getUserLearningList(id);
		StudyListVo studyListVo;
		for (int i = 0; i < list.size(); i++) {
			studyListVo = new StudyListVo();
			studyListVo.setCheckword(list.get(i).get("checkword").toString());
			studyListVo.setDay(list.get(i).get("day").toString());
			studyListVo.setId(list.get(i).get("id").toString());
			studyListVo.setWord(list.get(i).get("word").toString());
			studyVoList.add(studyListVo);
		}

		return studyVoList;
	}

	public void updateStudyCheck(StudyListVo studyListVo) {
		hanguldao.updateStudyCheck(studyListVo);

	}
	private void judgement(List<DrawVO> word_unit_jsonArray ,List<LetterVo> stroke_unit, List<List<DrawVO>> word,List<List<LetterVo>> stroke,int intCho,int intJun,int intJon,int judge,int junDataBaseNum) {
		List<Map<String, Object>> return_db;
		JudgeVo judgeVo;
		if(judge==0) {
			System.out.println(intCho);
			judgeVo=new JudgeVo(intCho, junDataBaseNum, 0);	
		}else {
			System.out.println(intCho);
			judgeVo=new JudgeVo(intCho, junDataBaseNum, 1);
		}
		
		// 초성
		return_db = hanguldao.getCho(judgeVo);
		LetterVo letterVo;
		String letter[];

		letter = return_db.get(0).get("stroke_amount").toString().split(",");

		for (int j = 0; j < letter.length; j++) {
			letterVo = new LetterVo();
			letterVo.setLetter(return_db.get(0).get("cho_shape").toString());
			letterVo.setStrokeNum(Integer.parseInt(letter[j]));
			stroke_unit.add(letterVo);
		}

		process(return_db, word_unit_jsonArray);

		
		if(judge==0) {
			System.out.println(intJun);
			judgeVo=new JudgeVo(0, intJun, 0);
			System.out.println(intJun);
		}else {
			judgeVo=new JudgeVo(0, intJun, 1);
		}
		
		// 중성
		return_db = hanguldao.getJun(judgeVo);
		letter = return_db.get(0).get("stroke_amount").toString().split(",");

		for (int j = 0; j < letter.length; j++) {
			letterVo = new LetterVo();
			letterVo.setLetter(return_db.get(0).get("jun_shape").toString());
			letterVo.setStrokeNum(Integer.parseInt(letter[j]));
			stroke_unit.add(letterVo);

		}

		process(return_db, word_unit_jsonArray);

		// 종성이 있으면
		if (judge == 1) {
			System.out.println(intJon);
			judgeVo=new JudgeVo(0, junDataBaseNum, intJon);	
			
			return_db = hanguldao.getJon(judgeVo);
			letter = return_db.get(0).get("stroke_amount").toString().split(",");

			for (int j = 0; j < letter.length; j++) {
				letterVo = new LetterVo();
				letterVo.setLetter(return_db.get(0).get("jon_shape").toString());
				letterVo.setStrokeNum(Integer.parseInt(letter[j]));
				stroke_unit.add(letterVo);

			}
			process(return_db, word_unit_jsonArray);

		}
		
		
		stroke.add(stroke_unit);
		word.add(word_unit_jsonArray);
	}
	private void judgmentJun(List<DrawVO> word_unit_jsonArray ,List<LetterVo> stroke_unit, List<List<DrawVO>> word,List<List<LetterVo>> stroke,int intCho,int intJun,int intJon,int judge) {
	
		// ㅏ ㅐ ㅑ ㅒ ㅓ ㅔ ㅕ ㅖ ㅣ -> DB 중성 종류 1
		if (intJun == 0 || intJun == 1 || intJun == 2 || intJun == 3 || intJun == 4 || intJun == 5
				|| intJun == 6 || intJun == 7 || intJun == 20) {
			judgement(word_unit_jsonArray,stroke_unit,word,stroke,intCho,intJun,intJon,judge,1);
		}// ㅗ ㅛ ㅡ  -> DB 중성 종류 2
		else if (intJun == 8 || intJun == 12 || intJun == 18) {
			judgement(word_unit_jsonArray,stroke_unit,word,stroke,intCho,intJun,intJon,judge,2);
		} // ㅜ ㅠ -> DB 중성 종류 3
		else if (intJun == 13 || intJun == 17) {
			judgement(word_unit_jsonArray,stroke_unit,word,stroke,intCho,intJun,intJon,judge,3);
		} // ㅟ ㅞ ㅝ -> DB 중성 종류 4
		else if (intJun == 16 || intJun == 15 || intJun == 14) {
			judgement(word_unit_jsonArray,stroke_unit,word,stroke,intCho,intJun,intJon,judge,4);
		} // ㅚ ㅙ ㅘ ㅢ -> DB 중성 종류 5
		else if (intJun == 10 || intJun == 11 || intJun == 9 || intJun == 19) {
			judgement(word_unit_jsonArray,stroke_unit,word,stroke,intCho,intJun,intJon,judge,5);
		}
	}

	public List<StudyListVo> getDateLearningWordList(StudyListVo studyListVo) {
		return hanguldao.getDateLearningWordList(studyListVo);
	}
}
