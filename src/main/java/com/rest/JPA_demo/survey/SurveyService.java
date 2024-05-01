package com.rest.JPA_demo.survey;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class SurveyService {

	private static List<Survey> surveys = new ArrayList<>();

	static {
		Question question1 = new Question("Question1", "Most popular",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
		Question question2 = new Question("Question2", "Most popular",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
		Question question3 = new Question("Question3", "Most popular",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");

		List<Question> questions = new ArrayList<>(Arrays.asList(question1, question2, question3));

		Survey survey = new Survey("Survey1", "My fav survey", "Description of survey", questions);
		surveys.add(survey);

	}

	public List<Survey> retrieveAllSurveys() {
		return surveys;
	}

	public Survey retrieveSurveysById(String id) {

		Predicate<? super Survey> predicate = survey -> survey.getId().equalsIgnoreCase(id);

		Optional<Survey> op = surveys.stream().filter(predicate).findFirst();
		if (op.isEmpty()) {

			return null;
		}
		return op.get();
	}

	public List<Question> retrieveAllQuestions(String id) {

		Predicate<? super Survey> predicate = survey -> survey.getId().equalsIgnoreCase(id);
		Optional<Survey> op = surveys.stream().filter(predicate).findFirst();
		if (op == null) {
			return null;
		}
		Survey s1 = op.get();
		List<Question> q = s1.getQuestions();

		return q;
	}

	public Question retrieveQuestionById(String surveyId, String questionId) {

		Predicate<Survey> predicate = s -> s.getId().equalsIgnoreCase(surveyId);

		Optional<Survey> op = surveys.stream().filter(predicate).findFirst();
		if (op.isEmpty()) {
			return null;
		}
		List<Question> q = op.get().getQuestions();
		Predicate<Question> p = qu -> qu.getId().equalsIgnoreCase(questionId);

		Optional<Question> ques = q.stream().filter(p).findFirst();

		if (ques == null) {
			return null;
		}
		return ques.get();
	}

	public void postSurvey(Survey survey) {
		surveys.add(survey);
	}

	public String addNewSurveyQuestion(Question question, String surveyId) {

		question.setId(generateRandomId());
		List<Question> q = retrieveAllQuestions(surveyId);
		q.add(question);
		return question.getId();
	}

	/**
	 * @return
	 */
	private String generateRandomId() {
		SecureRandom random = new SecureRandom();
		String r = new BigInteger(32, random).toString();
		return r;
	}

	public String deleteQuestionById(String surveyId, String questionId) {

		Optional<Survey> op = surveys.stream().filter(s -> s.getId().equalsIgnoreCase(surveyId)).findFirst();
		if (op == null) {
			return null;
		}

		Predicate<Question> predicate = q -> q.getId().equalsIgnoreCase(questionId);
		boolean removed = op.get().getQuestions().removeIf(predicate);

		if (!removed)
			return null;

		return questionId;

	}

	public void updateSurveyQuestion(Question question, String surveyId, String questionId) {
		List<Question> ques = retrieveAllQuestions(surveyId);
		ques.removeIf(q -> q.getId().equalsIgnoreCase(questionId));
		ques.add(question);
	}

}
