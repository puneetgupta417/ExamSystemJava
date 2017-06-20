package design;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.*;

import pojo.Answer;
import pojo.Exam;
import pojo.Question;
import services.ExamService;

public class ExamLayout implements ActionListener{
	
	JFrame mainFrame = new JFrame("Exam");
	JPanel panel1 = new JPanel();
	private static int questionId = 1;
	private static java.util.List<Exam> savedAnswers = new ArrayList<Exam>();
	private int answerId_selected = 0;
	
	JButton btn_next = new JButton("Next");
	JButton btn_prev = new JButton("Prev");
	JButton btn_submit = new JButton("Submit");
	
	Label label_question = new Label("Questions");
	Label label_Score = new Label("Scores");
	Label label_Score_Numbers = new Label("");
	Label[] label_answers = new Label[4];
	JRadioButton[] radioButton = null;
	ButtonGroup btnGroup = new ButtonGroup();
	
	private java.util.List<Question> numberOfQuestions = ExamService.GetAllQuestions();
	
	ExamLayout() {
		mainFrame.setVisible(true);
		mainFrame.setSize(600, 500);
		panel1.setLayout(null);
		panel1.setBounds(5, 5, 550, 480);
		mainFrame.add(panel1);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setRadioButtons();
		addQuestionLabelsAndRadioAnswers();
		addNextAndPrevButtons();
		
	}
	
	private void setRadioButtons(){
		radioButton = new JRadioButton[4];
		for(int i = 0; i < 4; i++){
			radioButton[i] = new JRadioButton();
			radioButton[i].setBounds(50, 50+(i*30), 400, 30);
			radioButton[i].setText("Radio"+i);
			panel1.add(radioButton[i]);
			btnGroup.add(radioButton[i]);
		}
		
	}
	
	
	private void saveAnswer(){
		boolean addAnswer = true;
		for(Exam answer : savedAnswers){
			if(answer.getQuestionId() == questionId)
			{
				updateAnswer(answer);
				addAnswer = false;
			}
		}
		if(addAnswer)
			addAnswer();
	}
	
	private void updateAnswer(Exam answer){
		Exam savedAnswer = answer;
		savedAnswer.setAnswerId(answerId_selected);
		savedAnswers.set(answer.getQuestionId(), savedAnswer); 
	}
	private void addAnswer(){
		savedAnswers.add(new Exam(questionId, answerId_selected));
	}
	
	private void addNextAndPrevButtons(){
		
		panel1.add(btn_next);
		panel1.add(btn_prev);
		panel1.add(btn_submit);
		btn_prev.setBounds(50, 200, 80, 30);
		btn_next.setBounds(400, 200, 100, 30);
		btn_submit.setBounds(200, 200, 100, 30);
		btn_next.addActionListener(this);
		btn_prev.addActionListener(this);
		btn_submit.addActionListener(this);
		//panel1.add(btn_submit);
		checkNextAndPrevButtons();
	}

	private void addQuestionLabelsAndRadioAnswers(){
		Question question = ExamService.GetQuestionAnswerByQuestionId(questionId);
		setQuestionAndAnswerLabel(question);
	}
	
	private void setQuestionAndAnswerLabel(Question question) {
		panel1.add(label_question);
		label_question.setBounds(50, 10, 500, 30);
		label_question.setText(question.getQuestion());
		
		java.util.List<Answer> answers = question.getAnswers();
		for(int i = 0; i < answers.size(); i++){
			radioButton[i].setText(answers.get(i).getText());
			String answerId = String.valueOf(answers.get(i).getAnswerId());
			radioButton[i].setActionCommand(answerId);
		}
		
	}
	
	public static void main(String[] args){
		ExamLayout obj = new ExamLayout();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_next){
			answerId_selected = Integer.parseInt(btnGroup.getSelection().getActionCommand());
			saveAnswer();
			questionId++;
			addQuestionLabelsAndRadioAnswers();
			checkNextAndPrevButtons();
		}
		else if(e.getSource() == btn_prev){
			answerId_selected = Integer.parseInt(btnGroup.getSelection().getActionCommand());
			saveAnswer();
			questionId--;
			addQuestionLabelsAndRadioAnswers();
			checkNextAndPrevButtons();
		}
		else if(e.getSource() == btn_submit){
			saveAnswer();
			calculateScore();
			btn_submit.setEnabled(false);
			btn_prev.setEnabled(false);
		}
	}
	
	private void calculateScore(){
		int score = 0;
		java.util.List<Answer> answers = ExamService.GetAllAnswers();
		for(Answer answer : answers){
			for(Exam student_answer : savedAnswers){
				if(student_answer.getAnswerId() == answer.getAnswerId()){
					if(answer.isCorrectAnswer())
						score++;
				}
				
			}
		}
		
		
		label_Score.setBounds(100, 300, 100, 30);
		panel1.add(label_Score);
		label_Score.setText("Scores:");
		label_Score_Numbers.setBounds(150, 300, 100, 30);
		panel1.add(label_Score_Numbers);
		label_Score.setText(String.valueOf(score) + " / " + numberOfQuestions.size());
	}

	private void checkNextAndPrevButtons() {
		if(questionId == numberOfQuestions.size()){
			btn_next.setEnabled(false);
			btn_submit.setEnabled(true);
		}
		else{
			btn_next.setEnabled(true);
			btn_submit.setEnabled(false);
		}
		
		if(questionId == 1)
			btn_prev.setEnabled(false);
		else
			btn_prev.setEnabled(true);
	}
	
}
