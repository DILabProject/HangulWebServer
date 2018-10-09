package di.cs.skuniv.model;

public class DayWordVo {
	
	private int num;
	private String word;

	public DayWordVo() {
		
	}
	
	public DayWordVo(int num, String word) {
		this.num = num;
		this.word = word;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	
}
