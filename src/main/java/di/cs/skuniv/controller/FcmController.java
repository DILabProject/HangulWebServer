package di.cs.skuniv.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

@Controller
public class FcmController {
	private static final Logger logger = LoggerFactory.getLogger(FcmController.class);
	@RequestMapping(value = "/fcm")
	public void sender(HttpServletRequest request,HttpServletResponse response) throws IOException {		
		 
		request.setCharacterEncoding("UTF-8");
		String msg=request.getParameter("msg");		
		String to_token=request.getParameter("to_token");
		String sender=request.getParameter("sender");
		String action=request.getParameter("action");
		System.out.println(msg);
		String result = "";
		String title;
		
		System.out.println("fcm");		
		System.out.println(to_token);
		System.out.println(msg);
		System.out.println(sender);
		System.out.println(action);
		String[] tokens=to_token.split(",");
		
		
		// 1. fcm 서버정보 세팅
		String fcm_url = "https://fcm.googleapis.com/fcm/send";
		String content_type = "application/json";
		String server_key = "AAAAnIkxDv0:APA91bEMNlEPRKSkU1X4KFF7ziPNbphtbUjx6S6s8pK4cF-6usLrDe7YXk7OAGB3jvNeK3VVCH_iPkHMCwkS2JnYRZBKzeMuh1uIG_5aEadzKUb2HFOFiqqT_IDxgCGObdrCeaEQI2HT";

		// 2. 메시지정보를 클라이언트(핸드폰)로 부터 수신
		// 위의 함수에 정의된 파라미터에서 값을 받게 된다.
		if(msg.equals("confirm_schedule")) {
			title    = sender;
		}else {
			title    = " 보낸 사람:"+sender;
		}
		String point = "823510746220";		
		

		// 3. fcm 서버로 메시지를 전송
		// 3.1 수신한 메시지를 json 형태로 변경해준다.
		for(int i=0;i<tokens.length;i++) {
			/*Msg data = new Msg();
			data.setTo(tokens[i]);
			data.getNotification().setTitle(title);
			data.getNotification().setBody(msg);
			data.getNotification().setClick_action(action);*/
			Gson gson = new Gson();
			
			// Msg 데이터를 json 스트링으로 변경
			//String json_string = gson.toJson(data);
			//System.out.println(json_string);
			
			try{
				// 3.2 HttpUrlConnection 을 사용해서 FCM서버측으로 메시지를 전송한다
					//a.서버연결
				URL url = new URL(fcm_url);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
					//b.header 설정
				con.setRequestMethod("POST");
				con.setRequestProperty("Authorization","key="+server_key);
				con.setRequestProperty("Content-Type",content_type);
					//c.POST데이터(body) 전송
				con.setDoOutput(true);
				OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
				//os.write(json_string.toString());
				os.flush();
				os.close();
				//d.전송후 결과처리
				int responseCode = con.getResponseCode();
				if(responseCode == HttpURLConnection.HTTP_OK){ // code 200
					// 결과처리후 FCM 서버측에서 발송한 결과메시지를 꺼낸다.
					BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String dataLine = "";
					// 메시지를 한줄씩 읽어서 result 변수에 담아두고 
					while((dataLine = br.readLine()) != null){
						result = result + dataLine;
					}
					br.close();
				}
			}catch(Exception e){
				result = e.toString();
			}
		}
		to_token="";
	}
}
