package pojo;

import java.util.List;

public class Question {
	private int QuestionId;
	private String Question;
	private List<Answer> Answers;
	
	public Question(int questionId, String question, List<Answer> answers){
		this.QuestionId = questionId;
		this.Question = question;
		this.Answers = answers;
	}
	
	public int getQuestionId() {
		return QuestionId;
	}
	public void setExamId(int questionId) {
		QuestionId = questionId;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public List<Answer> getAnswers() {
		return Answers;
	}
	public void setAnswers(List<Answer> answers) {
		Answers = answers;
	}
	
	
}
