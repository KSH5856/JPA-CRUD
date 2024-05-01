package com.rest.JPA_demo.survey;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class Controller {

	private SurveyService surveyService;

//	In the above field if we put the Autowired then it will automatically inject the static field present in the SurveyService. 
//	Other wise we have to create a constructor for it. 

	public Controller(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	@GetMapping("surveys")
	public List<Survey> retrieveAllSurveys() {
		return surveyService.retrieveAllSurveys();
	}

	@GetMapping("surveys/{id}")
	public Survey retrieveSurveysById(@PathVariable String id) {
		Survey survey = surveyService.retrieveSurveysById(id);

		if (survey == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return survey;
	}

	@GetMapping("surveys/{surveyId}/questions")
	public List<Question> retrieveAllQuestions(@PathVariable String surveyId) {
		return surveyService.retrieveAllQuestions(surveyId);
	}

	@GetMapping("surveys/{surveyId}/questions/{questionId}")
	public Question retrieveQuestionById(@PathVariable String surveyId, @PathVariable String questionId) {
		Question questionById = surveyService.retrieveQuestionById(surveyId, questionId);

		if (questionById == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return questionById;
	}

	@PostMapping("surveys/post")
	public String postSurvey(@RequestBody Survey survey) {
		surveyService.postSurvey(survey);
		return "Success";
	}

	@PostMapping("surveys/{surveyId}/questions")
	public ResponseEntity<Object> addNewSurveyQuestion(@RequestBody Question question, @PathVariable String surveyId) {
		surveyService.addNewSurveyQuestion(question, surveyId);
		return ResponseEntity.status(202).body("Success");
	}

	@DeleteMapping("surveys/{surveyId}/questions/{questionId}")
	public ResponseEntity<Object> deleteQuestionById(@PathVariable String surveyId, @PathVariable String questionId) {
		surveyService.deleteQuestionById(surveyId, questionId);
		return ResponseEntity.status(204).body("Deleted successfully");
	}

	@PutMapping("surveys/{surveyId}/questions/{questionId}")
	public ResponseEntity<Object> updateSurveyQuestion(@RequestBody Question question, @PathVariable String surveyId,
			@PathVariable String questionId) {
		surveyService.updateSurveyQuestion(question, surveyId, questionId);
		return ResponseEntity.status(202).body("Updated Success");
	}

}
