package response;

public class Response {
	
	private String resId;
	private int questId;
	private String response;
	private int responseId;
	private String cleanData;
	
	public int getResponseId() {
		return responseId;
	}
	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}
	public String getCleanData() {
		return cleanData;
	}
	public void setCleanData(String cleanData) {
		this.cleanData = cleanData;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public int getQuestId() {
		return questId;
	}
	public void setQuestId(int questId) {
		this.questId = questId;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Response() {
		super();
	}
	
	

}
