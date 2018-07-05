package com.briup.apps.poll.bean.extend;

import java.util.List;




public class QuestionnaireVM {
	  private Long id;
	  private String name;
	  private String description;
	  private List<QuestionVM> questions;
	  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<QuestionVM> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionVM> questions) {
		this.questions = questions;
	}

	  
}
