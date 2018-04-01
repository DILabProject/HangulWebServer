package di.cs.skuniv.controller;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import di.cs.skuniv.model.HangulVO;
import di.cs.skuniv.model.StudyListVo;
import di.cs.skuniv.model.UserVo;
import di.cs.skuniv.model.WrongCountVo;
import di.cs.skuniv.service.HangulService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HangulController {
	
	@Resource(name="HangulService")
	private HangulService hangulservice;
	
	
	@RequestMapping(value = "/hangul_input")
	public String hangul_input(HttpServletRequest request) {
		return "hangul_input";
		
	}
	@RequestMapping(value = "/hangul_input_complete",method = RequestMethod.POST,produces="text/plain;charset=UTF-8")

	public @ResponseBody String hangul_input_complete(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("hangul");
		Gson gson=new Gson();
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
		Gson gson=new Gson();
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
		Gson gson=new Gson();
		
		
		String strUser=request.getParameter("userVO");
		UserVo user=gson.fromJson(strUser, UserVo.class);
		
		//전달받은 값을 DB에 추가한다.
		hangulservice.signUp(user);
	}
	
	@RequestMapping(value = "/wrongCount",method = RequestMethod.POST)
	public @ResponseBody void wrongcount(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("wrongcount");
		Gson gson=new Gson();
		
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
		Gson gson=new Gson();
		
		//사용자의 아이디, 문자, 횟수를 받는다.
		String id=request.getParameter("id");
		List<StudyListVo> stydyListVo=hangulservice.getUserLearningList(id);
		
		String strstydyListVo=gson.toJson(stydyListVo);
		
		System.out.println(strstydyListVo);
		return (strstydyListVo);
		
	}
	@RequestMapping(value = "/finishStudy",method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public @ResponseBody void finishStudy(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("finishStudy");
		Gson gson=new Gson();
		
		//사용자의 아이디, 문자, 횟수를 받는다.
		String id=request.getParameter("id");
		String day=request.getParameter("day");
		
		StudyListVo studyListVo=new StudyListVo();
		studyListVo.setId(id);
		studyListVo.setDay(day);
		
		hangulservice.updateStudyCheck(studyListVo);
		
		
		
	}
	
}
