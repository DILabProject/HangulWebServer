package di.cs.skuniv.model;


public class DayVo {
	private int num;
	private String name;
	
	public DayVo() {
		
	}
	
	public DayVo(int num, String name) {
		super();
		this.num = num;
		this.name = name;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
