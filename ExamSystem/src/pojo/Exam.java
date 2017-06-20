package pojo;

import java.util.List;

public class Exam {
	private int questionId;
	private int answerId;
	
	public Exam(int questionid, int answerId){
		this.questionId = questionid;
		this.answerId = answerId;
	}
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	
}
