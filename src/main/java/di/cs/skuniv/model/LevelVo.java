package di.cs.skuniv.model;

public class LevelVo {
	private String id;
	private int day;
	private int word_count;
	private int check_count;
	
	public LevelVo() {
		
	}
	
	public LevelVo(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getWord_count() {
		return word_count;
	}
	public void setWord_count(int word_count) {
		this.word_count = word_count;
	}
	public int getCheck_count() {
		return check_count;
	}
	public void setCheck_count(int check_count) {
		this.check_count = check_count;
	}
}
