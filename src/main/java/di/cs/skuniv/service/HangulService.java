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

@Service("HangulService")
public class HangulService {
	
	private static final char[] CHO= {0x3131,0x3132,0x3134,0x3137,0x3138,0x3139,0x3141,0x3142,
			0x3143,0x3145,0x3146,0x3147,0x3148,0x3149,0x314a,0x314b,0x314c,0x314d,0x314e};
		/*�� �� �� �� �� �� �� �� �� �� �� �� ��  �� �� �� �� �� ��*/
	private static final char[] JUN= {0x314f,0x3150,0x3151,0x3152,0x3153,0x3154,0x3155,0x3156,0x3157,0x3158,
			0x3159,0x315a,0x315b,0x315c,0x315d,0x315e,0x315f,0x3160,0x3161,0x3162,0x3163};
		/*�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��*/
	private static final char[] JON= {0x0000,0x3131,0x3132,0x3133,0x3134,0x3135,0x3136,0x3137,0x3139,0x313a,0x313b,0x313c,0x313d,0x313e,0x313f,
			0x3140,0x3141,0x3142,0x3144,0x3145,0x3146,0x3147,0x3148,0x314a,0x314b,0x314c,0x314d,0x314e};
		/*X �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��*/
	
	
	@Resource(name="HangulDao")
	private HangulDao hanguldao;

	public List<Map<String,JsonArray>> getHangul(String input) {
		List<Map<String,JsonArray>> return_list=new ArrayList<Map<String,JsonArray>>();
		
		String tempStr=input;
		String lastStr="";
		
		System.out.println(tempStr);
		
		for(int i=0;i<tempStr.length();i++) {
			
			char test=tempStr.charAt(i);
			
			if(test>=0xAC00) {
				
				Map<String,JsonArray> return_map=new HashMap<String, JsonArray>();
				
				char uniVal = (char) (test - 0xAC00);
				char cho =(char)(((uniVal-(uniVal%28))/28)/21);
				char jun =(char)(((uniVal-(uniVal%28))/28)%21);
				char jon=(char)(uniVal%28);
			
				System.out.println(""+test+"// 0x"+Integer.toHexString((char)test));
				System.out.println(""+CHO[cho]+"// 0x"+Integer.toHexString((char)cho));
				System.out.println(""+JUN[jun]+"// 0x"+Integer.toHexString((char)jun));
				if((char)jon!=0x0000)
				System.out.println(""+JON[jon]+"// 0x"+Integer.toHexString((char)jon));
				
				/*return_map.put("cho",(int)cho);
				return_map.put("jun",(int)jun);
				return_map.put("jon",(int)jon);*/
				
				//������ ���̽����� �ʼ�, �߼�, ������ �������� �׸�.
				List<Map<String,Object>> return_db;
				
				
				//�ʼ�
				
				return_db=hanguldao.getCho((int)cho);				
				return_map.put("cho", process(return_db));
				
				
				//�߼�
				return_db=hanguldao.getJun((int)jun);				
				return_map.put("jun", process(return_db));
				
				
				//����
				if((int)jon!=0) {
					return_db=hanguldao.getJon((int)jon);
					return_map.put("jon", process(return_db));
				}
				return_list.add(return_map);
			}
		}
		for(int i=0;i<return_list.size();i++) {
			System.out.println(return_list.get(i));
		}
		return return_list;
	}
	private JsonArray process(List<Map<String,Object>> return_db) {
		JsonArray ja=new JsonArray();
		for(int j=0;j<return_db.size();j++) {
			JsonObject jo=new JsonObject();
			jo.addProperty("x1",  return_db.get(j).get("x1")+"");
			jo.addProperty("y1",  return_db.get(j).get("y1")+"");
			jo.addProperty("x2",  return_db.get(j).get("x2")+"");
			jo.addProperty("y2",  return_db.get(j).get("y2")+"");
			ja.add(jo);
		}
		return ja;
	}

}
