package di.cs.skuniv.model;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;

public class HangulVO {
	private List<Map<String,JsonArray>> hangul_map_list;

	public List<Map<String, JsonArray>> getHangul_map_list() {
		return hangul_map_list;
	}

	public void setHangul_map_list(List<Map<String, JsonArray>> hangul_map_list) {
		this.hangul_map_list = hangul_map_list;
	}
	
	

}
