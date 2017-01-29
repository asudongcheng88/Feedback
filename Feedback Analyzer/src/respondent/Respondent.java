package respondent;

public class Respondent {

	private String resId;
	private String resName;
	private String resPass;
	public boolean valid;
	
	public Respondent() {
		super();
	}

	public Respondent(String resId, String resPass) {
		super();
		this.resId = resId;
		this.resPass = resPass;
	}
	
	public Respondent(String resId, String resName, String resPass) {
		super();
		this.resId = resId;
		this.resName = resName;
		this.resPass = resPass;
	}
	
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getResPass() {
		return resPass;
	}

	public void setResPass(String resPass) {
		this.resPass = resPass;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}
	
	
	
}
