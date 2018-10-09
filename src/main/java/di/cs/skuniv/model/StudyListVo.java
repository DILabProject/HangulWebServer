package di.cs.skuniv.model;



public class StudyListVo {
    private String id;
	private int day;
	private String word;
    private String checkword;
    
    public StudyListVo() {	
	}

	public StudyListVo(int day, String word) {
		this.day = day;
		this.word = word;
	}
	
	public StudyListVo(String id, int day, String word) {
		this.id = id;
		this.day = day;
		this.word = word;
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

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getCheckword() {
		return checkword;
	}

	public void setCheckword(String checkword) {
		this.checkword = checkword;
	}
	
	
}
