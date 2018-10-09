package di.cs.skuniv.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import di.cs.skuniv.model.DayVo;
import di.cs.skuniv.model.DayWordVo;
import di.cs.skuniv.model.HangulVO;
import di.cs.skuniv.model.LevelVo;
import di.cs.skuniv.model.StudyListVo;
import di.cs.skuniv.model.UserVo;
import di.cs.skuniv.model.WrongCountVo;
import di.cs.skuniv.service.HangulService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HangulController {
	
	@Autowired
	private Gson gson;
	
	@Resource(name="HangulService")
	private HangulService hangulservice;
	
	@RequestMapping(value = "/hangul_input")
	public String hangul_input(HttpServletRequest request) {
		List<DayVo> list = hangulservice.getDayList();
		request.setAttribute("list", list);
		return "hangul_input";
	}
	@RequestMapping(value = "/add-day/{size}")
	public String addDay(HttpServletRequest request,@PathVariable("size") int size) {
		hangulservice.createDay(size+1);
		List<DayVo> list = hangulservice.getDayList();
		request.setAttribute("list", list);
		return "redirect:/hangul_input";
		
	}
	@RequestMapping(value = "/addWord")
	public String addWord(HttpServletRequest request) {
		int day= Integer.parseInt(request.getParameter("day"));
		String word=request.getParameter("word");
		hangulservice.addWord(new DayWordVo(day, word));
		
//		hangulservice.addStudyUserVo(new StudyListVo("aaa",day,word));
		List<DayVo> list = hangulservice.getDayList();
		request.setAttribute("list", list);
		return "redirect:/hangul_input";
	}
	@RequestMapping(value = "/deleteWord")
	public String deleteWord(HttpServletRequest request) {
		int day= Integer.parseInt(request.getParameter("day"));
		String word=request.getParameter("word");
		hangulservice.deleteWord(new DayWordVo(day, word));
		hangulservice.deleteWord(new StudyListVo(day,word));
		List<DayVo> list = hangulservice.getDayList();
		request.setAttribute("list", list);
		return "redirect:/hangul_input";
	}
	@RequestMapping(value = "/selectDay/{day}",produces="text/plain;charset=UTF-8")
	public @ResponseBody String selectDay(HttpServletRequest request,@PathVariable("day") String day) {
		List<DayWordVo> list = hangulservice.getDayWordList(day);
		return gson.toJson(list);
		
	}
	
	
	
	@RequestMapping(value = "/hangul_input_complete",method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public @ResponseBody String hangul_input_complete(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("hangul");
		
		String word=request.getParameter("word");
		HangulVO hangulVO=hangulservice.getHangul(word);		
		String str_HangulVO=gson.toJson(hangulVO);
		
		System.out.println(str_HangulVO);		
		return str_HangulVO;
		
	}
	//로그인을 위한 웹서버 매핑
	@RequestMapping(value = "/signIn",method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public @ResponseBody String signin(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("signin");
		//사용자의 아이디와 비밀번호를 전달 받는다.
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		System.out.println(id +"=="+password);
		
		Map<String, Object> user=hangulservice.login(id,password);
		
		//해당하는 사용자가 있을경우 정보를 줌, 없으면 null값이다.
		String strUser=gson.toJson(user);
		System.out.println(strUser);
		
		if(!strUser.equals("null")) {
			System.out.println("1");
			return "1";
		}else {
			System.out.println("0");
			return "0";
		}
		
	}
	
	//회원가입 서버 매핑
	@RequestMapping(value = "/signUp",method = RequestMethod.POST)
	public @ResponseBody void signup(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("signup");
		
		String strUser=request.getParameter("userVO");
		UserVo user=gson.fromJson(strUser, UserVo.class);
		
		//전달받은 값을 DB에 추가한다.
		hangulservice.signUp(user);
	}
	
	@RequestMapping(value = "/wrongCount",method = RequestMethod.POST)
	public @ResponseBody void wrongcount(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("wrongcount");
		
		
		//사용자의 아이디, 문자, 횟수를 받는다.
		String id=request.getParameter("id");
		String letter=request.getParameter("letter");
		WrongCountVo wrongCountVo=new WrongCountVo();
		wrongCountVo.setId(id);
		wrongCountVo.setLetter(letter);
		hangulservice.wrongCount(wrongCountVo);
	}
	
	@RequestMapping(value = "/studyList",method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public @ResponseBody String userLearningDay(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("studyList");		
		StudyListVo studyListVo = new StudyListVo();
		//사용자의 아이디, 문자, 횟수를 받는다.
		
		String id=request.getParameter("id");
		int day = Integer.parseInt(request.getParameter("day"));
		studyListVo.setId(id);
		studyListVo.setDay(day);
		System.out.println(id);
		List<StudyListVo> stydyListVoList=hangulservice.getDateLearningWordList(studyListVo);
		
		String strstydyListVo=gson.toJson(stydyListVoList);
		
		System.out.println(strstydyListVo);
		return (strstydyListVo);
		
	}
	@RequestMapping(value = "/finishStudy",method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public @ResponseBody void finishStudy(HttpServletRequest request) throws UnsupportedEncodingException{
		System.out.println("finishStudy");
		request.setCharacterEncoding("UTF-8");
		String word = request.getParameter("word");
		String id = request.getParameter("id");
		int day= Integer.parseInt(request.getParameter("day"));
		StudyListVo studyListVo = new StudyListVo(id, day, word);
		hangulservice.updateStudyCheck(studyListVo);
	}
	
	
	@RequestMapping(value="/select_date")
	public ModelAndView selectDate(@RequestParam("date") String date,ModelAndView modelAndView) {
		modelAndView.setViewName("hangul_input_complete");
		StudyListVo studyListVo = new StudyListVo();
		studyListVo.setDay(1);
		List<StudyListVo> dateLearningWordList = hangulservice.getDateLearningWordListByDate(studyListVo);
		
		modelAndView.addObject("dateWordList", dateLearningWordList);
		modelAndView.addObject("date", date);
		return modelAndView;
	}	
	
	@RequestMapping(value = "/filedown")
	public String filedown(HttpServletRequest request) {
		return "file_down";
	}
	
	@RequestMapping(value = "/filedown-hangul")
	public String filedownHangul(HttpServletRequest request) {
		return "filedown_hangul";
	}
	
	@RequestMapping(value = "/getLevelList")
	public @ResponseBody String getLevelList(HttpServletRequest request) {
		System.out.println("getLevelList");
		String id = request.getParameter("id");
		System.out.println(id);
		List<LevelVo> levelVos = hangulservice.getLevelList(id);
		System.out.println(gson.toJson(levelVos));
		return gson.toJson(levelVos);
	}
	
	@RequestMapping(value = "/getWordList")
	public @ResponseBody String getWordList(HttpServletRequest request) {
		System.out.println("getWordList");
		String id = request.getParameter("id");
		int day = Integer.parseInt(request.getParameter("day"));
		StudyListVo studyListVo = new StudyListVo();
		studyListVo.setId(id);
		studyListVo.setDay(day);
		List<StudyListVo> studyListVos = hangulservice.getWordList(studyListVo);
		return gson.toJson(studyListVos);
	}
//	@RequestMapping(value="/hangul_input_complete")
//	public ModelAndView hangulInputComplete(@RequestParam("date") String date,@RequestParam("word") String word,ModelAndView modelAndView) {
//		modelAndView.setViewName("hangul_input_complete");
//		System.out.println("create_word_by_date");
//		StudyListVo studyListVo = new StudyListVo();
//		studyListVo.setDay(date);
//		studyListVo.setWord(word);
//		List<StudyListVo> dateLearningWordList = hangulservice.createWordWithSelectWordList(studyListVo);
//		
//		modelAndView.addObject("dateWordList", dateLearningWordList);
//		modelAndView.addObject("date", date);
//		return modelAndView;
//	}	
}
