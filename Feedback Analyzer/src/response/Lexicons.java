package response;

public class Lexicons {
	
	private int lexId;
	private String lexText;
	private int lexPoint;
	boolean exist;
	
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	public int getLexId() {
		return lexId;
	}
	public void setLexId(int lexId) {
		this.lexId = lexId;
	}
	public String getLextext() {
		return lexText;
	}
	public void setLexText(String lexText) {
		this.lexText = lexText;
	}
	public int getLexPoint() {
		return lexPoint;
	}
	public void setLexPoint(int lexPoint) {
		this.lexPoint = lexPoint;
	}
	public Lexicons() {
		super();
	}
	
	
	

}
