package analyze;

public class Analyze {
	
	
	private String resId;
	private String lexText;
	private int totalPoint;
	private String secName;
	private int questId;
	boolean exist;
	
	
	public int getQuestId() {
		return questId;
	}
	public void setQuestId(int questId) {
		this.questId = questId;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getSecName() {
		return secName;
	}
	public void setSecName(String secName) {
		this.secName = secName;
	}

	public String getLexText() {
		return lexText;
	}
	public void setLexText(String lexText) {
		this.lexText = lexText;
	}
	public int getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	public Analyze() {
		super();
	}
	public Analyze(String lexText, int totalPoint) {
		super();
		this.lexText = lexText;
		this.totalPoint = totalPoint;
	}
	public Analyze(int totalPoint, String secName) {
		super();
		this.totalPoint = totalPoint;
		this.secName = secName;
	}
	
	
	

}
