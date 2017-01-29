package admin;

public class Admin {
	
	private String adId; 
	private String adName;
	private String adPass;
	public boolean valid;
	
	
	public Admin() {
		super();
	}
	public Admin(String adId, String adName, String adPass) {
		super();
		this.adId = adId;
		this.adName = adName;
		this.adPass = adPass;
	}
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAdPass() {
		return adPass;
	}
	public void setAdPass(String adPass) {
		this.adPass = adPass;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
	
}
