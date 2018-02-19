package di.cs.skuniv.model;

import java.util.List;

public class HangulVO {
	List<List<DrawVO>> word;
	List<String> stroke;
	
	
	public List<List<DrawVO>> getWord() {
		return word;
	}
	public void setWord(List<List<DrawVO>> word) {
		this.word = word;
	}
	public List<String> getStroke() {
		return stroke;
	}
	public void setStroke(List<String> stroke) {
		this.stroke = stroke;
	}
}
