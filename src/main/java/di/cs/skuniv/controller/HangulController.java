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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


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
	@RequestMapping(value = "/hangul_input_complete")
	public String hangul_input_complete(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		Gson gson=new Gson();
		String input=request.getParameter("input");
		JsonObject jsonObject=hangulservice.getHangul(input);
		
		String str_HangulVO=gson.toJson(jsonObject);
		request.setAttribute("str_HangulVO", str_HangulVO);
		return "hangul_input_complete";
		
	}
	
}
