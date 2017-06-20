package data;

import java.util.ArrayList;
import java.util.List;

import pojo.Answer;
import pojo.Question;

public class DbHelper {

	public static List<Question> GetQuestions(){
		
		List<Question> questions = new ArrayList<Question>();
		
		questions.add(new Question(1, "What is the stored in the object obj in following lines of code?", null));
		questions.add(new Question(2, "Which of these keywords is used to make a class?", null));
		questions.add(new Question(3, "Which of the following is a valid declaration of an object of class Box?", null));
		questions.add(new Question(4, "Which of these operators is used to allocate memory for an object?", null));
		questions.add(new Question(5, "Which of these statement is incorrect?", null));
		
		return questions;
	}
	
	public static List<Answer> GetAnswers(){
		List<Answer> answers = new ArrayList<Answer>();
		
		answers.add(new Answer(1, "Memory address of allocated memory of object.", 1, false, null));
		answers.add(new Answer(2, "NULL", 1, false, null));
		answers.add(new Answer(3, "Any arbitrary pointer", 1, true, null));
		answers.add(new Answer(4, "Garbage", 1, false, null));
		answers.add(new Answer(5, "class", 2, false, null));
		answers.add(new Answer(6, "struct", 2, false, null));
		answers.add(new Answer(7, "int", 2, true, null));
		answers.add(new Answer(8, "None of the mentioned", 2, false, null));
		answers.add(new Answer(9, "Box obj = new Box();", 3, false, null));
		answers.add(new Answer(10, "Box obj = new Box;", 3, false, null));
		answers.add(new Answer(11, "obj = new Box();", 3, true, null));
		answers.add(new Answer(12, "new Box obj;", 3, false, null));
		answers.add(new Answer(13, "malloc", 4, true, null));
		answers.add(new Answer(14, "alloc", 4, false, null));
		answers.add(new Answer(15, "new", 4, false, null));
		answers.add(new Answer(16, "give", 4, false, null));
		answers.add(new Answer(17, "Every class must contain a main() method.", 5, false, null));
		answers.add(new Answer(18, "Applets do not require a main() method at all.", 5, true, null));
		answers.add(new Answer(19, "There can be only one main() method in a program.", 5, false, null));
		answers.add(new Answer(20, "main() method must be made public.", 5, false, null));
		return answers;
	}
	
	public static List<Question> GetAllQuestionAnswers(){
		List<Question> questions = GetQuestions();
		
		List<Answer> answers = GetAnswers();
		
		for(Question question : questions) {
			List<Answer> answersOfQuestion = new ArrayList<Answer>();
			for(Answer answer : answers){
				if(answer.getQuestionId() == question.getQuestionId()){
					answersOfQuestion.add(answer);
				}
			}
			question.setAnswers(answersOfQuestion);
		}
		
		return questions;
	}
	
	public static Question GetQuestionAnswersByQuestionId(int questionId){
		List<Question> questions = GetAllQuestionAnswers();
		for(Question question : questions){
			if(question.getQuestionId() == questionId){
				return question;
			}
		}
		return null;
	}
	
}
