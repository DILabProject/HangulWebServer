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
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public @ResponseBody boolean login(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("login");
		
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
		boolean login=hangulservice.login(id,password);
		System.out.println(login);
		return login;
	}
	
}
