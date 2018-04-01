package di.cs.skuniv.model;

import java.util.List;

public class HangulVO {
	List<List<DrawVO>> word;
	List<List<LetterVo>> stroke;
	
	
	public List<List<DrawVO>> getWord() {
		return word;
	}
	public void setWord(List<List<DrawVO>> word) {
		this.word = word;
	}
	public List<List<LetterVo>> getStroke() {
		return stroke;
	}
	public void setStroke(List<List<LetterVo>> stroke) {
		this.stroke = stroke;
	}
	
}
