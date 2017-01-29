package multipleData;

public class ProgramAndQuestions {
	private String progId;
	private String progName;
	private String progDate;
	private String progDescription;
	private String adId;
	private int questId;
	private String questText;
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
	public int getQuestId() {
		return questId;
	}
	public void setQuestId(int questId) {
		this.questId = questId;
	}
	public String getQuestText() {
		return questText;
	}
	public void setQuestText(String questText) {
		this.questText = questText;
	}
	public ProgramAndQuestions() {
		super();
	}
	public ProgramAndQuestions(String progId, String progName, String progDate,
			String adId, int questId, String questText) {
		super();
		this.progId = progId;
		this.progName = progName;
		this.progDate = progDate;
		this.adId = adId;
		this.questId = questId;
		this.questText = questText;
	}
	
	
}
