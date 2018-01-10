package di.cs.skuniv.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import di.cs.skuniv.dao.HangulDao;

@Service("HangulService")
public class HangulService {
	
	private static final char[] CHO= {0x3131,0x3132,0x3134,0x3137,0x3138,0x3139,0x3141,0x3142,
			0x3143,0x3145,0x3146,0x3147,0x3148,0x3149,0x314a,0x314b,0x314c,0x314d,0x314e};
		/*ぁ あ い ぇ え ぉ け げ こ さ ざ し じ  す ず せ ぜ そ ぞ*/
	private static final char[] JUN= {0x314f,0x3150,0x3151,0x3152,0x3153,0x3154,0x3155,0x3156,0x3157,0x3158,
			0x3159,0x315a,0x315b,0x315c,0x315d,0x315e,0x315f,0x3160,0x3161,0x3162,0x3163};
		/*た だ ち ぢ っ つ づ て で と ど な に ぬ ね の は ば ぱ ひ び*/
	private static final char[] JON= {0x0000,0x3131,0x3132,0x3133,0x3134,0x3135,0x3136,0x3137,0x3139,0x313a,0x313b,0x313c,0x313d,0x313e,0x313f,
			0x3140,0x3141,0x3142,0x3144,0x3145,0x3146,0x3147,0x3148,0x314a,0x314b,0x314c,0x314d,0x314e};
		/*X ぁ あ ぃ い ぅ う ぇ ぉ お か が き ぎ く ぐ け げ ご さ ざ し じ ず せ ぜ そ ぞ*/
	
	
	@Resource(name="HangulDao")
	private HangulDao hanguldao;

	public List<Map<String,Integer>> getHangul(String input) {
		List<Map<String,Integer>> list=new ArrayList<Map<String ,Integer>>();
		String tempStr=input;
		String lastStr="";
		
		System.out.println(tempStr);
		
		for(int i=0;i<tempStr.length();i++) {
			Map<String , Integer> map=new HashMap<String,Integer>();
			char test=tempStr.charAt(i);
			
			if(test>=0xAC00) {
				char uniVal = (char) (test - 0xAC00);
				char cho =(char)(((uniVal-(uniVal%28))/28)/21);
				char jun =(char)(((uniVal-(uniVal%28))/28)%21);
				char jon=(char)(uniVal%28);
			
				System.out.println(""+test+"// 0x"+Integer.toHexString((char)test));
				System.out.println(""+CHO[cho]+"// 0x"+Integer.toHexString((char)cho));
				System.out.println(""+JUN[jun]+"// 0x"+Integer.toHexString((char)jun));
				if((char)jon!=0x0000)
				System.out.println(""+JON[jon]+"// 0x"+Integer.toHexString((char)jon));
				
				map.put("cho",(int)cho);
				map.put("jun",(int)jun);
				map.put("jon",(int)jon);
				list.add(map);
			}
		}
		
		for(int i=0;i<list.size();i++) {
			int a=(int)(list.get(i)).get("cho");
			int b=(int)(list.get(i)).get("jun");
			int c=(int)(list.get(i)).get("jon");
			
			char temp=(char)(0xAC00+28*21*(a)+28*(b)+(c));
			
			lastStr=lastStr.concat(String.valueOf(temp));
		}
		
		System.out.println(""+lastStr);
		
		for(int i=0;i<list.size();i++) {
			System.out.println(""+((Map)(list.get(i))).get("cho"));
			System.out.println(""+((Map)(list.get(i))).get("jun"));
			System.out.println(""+((Map)(list.get(i))).get("jon"));
		}
	
		return list;
	}

}
