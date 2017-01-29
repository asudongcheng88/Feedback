package program;

public class Program {
	
	private String adId;
	

	private String progId;
	private String progName;
	private String progDate;
	private String progDescription;
	
	public Program() {
		super();
	}
	
	public Program(String progName) {
		super();
		this.progName = progName;
	}

	public Program(String progId, String progName) {
		super();
		this.progId = progId;
		this.progName = progName;
	}

	public Program(String adId, String progId, String progName, String progDate,
			String progDescription) {
		super();
		
		this.progId = progId;
		this.progName = progName;
		this.progDate = progDate;
		this.progDescription = progDescription;
	}

	public String getProgId() {
		return progId;
	}

	public void setProgId(String progId) {
		this.progId = progId;
	}

	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}

	public String getProgDate() {
		return progDate;
	}

	public void setProgDate(String progDate) {
		this.progDate = progDate;
	}

	public String getProgDescription() {
		return progDescription;
	}

	public void setProgDescription(String progDescription) {
		this.progDescription = progDescription;
	}
	
	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}
	
	
}
