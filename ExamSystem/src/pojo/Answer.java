package pojo;

public class Answer {
	private int AnswerId;
	private String Text;
	private int QuestionId;
	private Question Question;
	private boolean CorrectAnswer;
	
	public Answer(int AnswerId, String Text, int QuestionId, boolean CorrectAnswer, Question Question){
		this.AnswerId = AnswerId;
		this.Text = Text;
		this.QuestionId = QuestionId;
		this.CorrectAnswer = CorrectAnswer;
		this.Question = Question;
	}
	
	public int getQuestionId() {
		return QuestionId;
	}
	public void setQuestionId(int questionId) {
		QuestionId = questionId;
	}
	public Question getQuestion() {
		return Question;
	}
	public void setQuestion(Question question) {
		Question = question;
	}
	public boolean isCorrectAnswer() {
		return CorrectAnswer;
	}
	public void setCorrectAnswer(boolean correctAnswer) {
		CorrectAnswer = correctAnswer;
	}
	public int getAnswerId() {
		return AnswerId;
	}
	public void setAnswerId(int answerId) {
		AnswerId = answerId;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	
	
}
