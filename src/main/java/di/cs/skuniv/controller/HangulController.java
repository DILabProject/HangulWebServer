package di.cs.skuniv.controller;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	@RequestMapping(value = "/hangul_input_complete",method = RequestMethod.POST)
	public @ResponseBody String hangul_input_complete(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("hangul");
		Gson gson=new Gson();
		String input=request.getParameter("day");
		HangulVO hangulVO=hangulservice.getHangul("간다");		
		String str_HangulVO=gson.toJson(hangulVO);
		System.out.println(str_HangulVO);		
		return str_HangulVO;
		
	}
	//로그인을 위한 웹서버 매핑
	@RequestMapping(value = "/signIn",method = RequestMethod.POST)
	public @ResponseBody String signin(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("signin");
		Gson gson=new Gson();
		//사용자의 아이디와 비밀번호를 전달 받는다.
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
		
		Map<String, Object> user=hangulservice.login(id,password);
		
		//해당하는 사용자가 있을경우 정보를 줌, 없으면 null값이다.
		String strUser=gson.toJson(user);
		System.out.println(strUser);
		return strUser;
	}
	
	//회원가입 서버 매핑
	@RequestMapping(value = "/signUp",method = RequestMethod.POST)
	public @ResponseBody void signup(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("signup");
		Gson gson=new Gson();
		
		//사용자의 아이디와 비밀번호를 전달 받는다.
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		String age=request.getParameter("age");
		String name=request.getParameter("name");
		
		UserVo user=new UserVo();
		user.setGender(gender);
		user.setId(id);
		user.setName(name);
		user.setPassword(password);
		user.setAge(age);
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
		int count=Integer.parseInt(request.getParameter("count"));
		
		WrongCountVo wrongCount=new WrongCountVo();
		wrongCount.setCount(count);
		wrongCount.setId(id);
		wrongCount.setLetter(letter);
		//전달받은 값을 DB에 추가한다.
		hangulservice.wrongCount(wrongCount);
	}
	
}
