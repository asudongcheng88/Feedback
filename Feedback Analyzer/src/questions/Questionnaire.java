package questions;

public class Questionnaire {
	
	private int questId;
	private String questText;
	private String secName;
	private String progId;

	
	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public Questionnaire(String questText) {
		super();
		this.questText = questText;
	}
	
	
	
	public Questionnaire(String secName, int questId, String questText) {
		super();
		this.questId = questId;
		this.questText = questText;
		this.secName = secName;
	}

	public Questionnaire(int quest_id, String questText, String progId) {
		super();
		this.questId = quest_id;
		this.questText = questText;
		this.progId = progId;
	}
	
	public Questionnaire() {
		super();
	}

	public Questionnaire(String questText, int questId) {
		super();
		this.questText = questText;
		this.questId = questId;
	}

	public String getQuestText() {
		return questText;
	}

	public void setQuestText(String questText) {
		this.questText = questText;
	}
	
	public String getProgId() {
		return progId;
	}

	public void setProgId(String progId) {
		this.progId = progId;
	}

	public int getQuestId() {
		return questId;
	}

	public void setQuest_id(int questId) {
		this.questId = questId;
	}
	
	
	
	
	

}
