package services;

import java.util.List;

import data.DbHelper;
import pojo.Answer;
import pojo.Question;

public class ExamService {

	public static List<Question> GetAllQuestions(){
		return DbHelper.GetQuestions();
	}
	
	public static List<Question> GetAllQuestionAndItsAnswers(){
		return DbHelper.GetAllQuestionAnswers();
	}
	
	public static Question GetQuestionAnswerByQuestionId(int questionId){
		return DbHelper.GetQuestionAnswersByQuestionId(questionId);
	}
	
	public static List<Answer> GetAllAnswers(){
		return DbHelper.GetAnswers();
	}
	
}
