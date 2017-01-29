package section;

public class Section {
	
	private String secName;
	private String progId;
	boolean exist;
	
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	public Section(String secName) {
		super();
		this.secName = secName;
	}
	public String getSecName() {
		return secName;
	}
	public void setSecName(String secName) {
		this.secName = secName;
	}
	public String getProgId() {
		return progId;
	}
	public void setProgId(String progId) {
		this.progId = progId;
	}
	public Section() {
		super();
	}
	
	

}
