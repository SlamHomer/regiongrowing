package com.slamhomer.regiongrowing_gameobjects;

public class Task {
	private String taskName = null;
	private String taskDesc = null;
	private String taskErf = null;
	
	public Task(String name, String desc, String erf){
		this.taskDesc = desc;
		this.taskErf = erf;
		this.taskName = name;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getTaskErf() {
		return taskErf;
	}

	public void setTaskErf(String taskErf) {
		this.taskErf = taskErf;
	}
}
