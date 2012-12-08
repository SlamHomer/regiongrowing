package com.slamhomer.regiongrowing_gameobjects;

public class Task {
	private String taskName = null;
	private String taskDesc = null;
	private String taskErf = null;
	private int taskInf = 0;
	
	public Task(String name, String desc, String erf, int inf){
		this.taskDesc = desc;
		this.taskErf = erf;
		this.taskName = name;
		this.taskInf = inf;
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

	public int getTaskInf() {
		return taskInf;
	}

	public void setTaskInf(int taskInf) {
		this.taskInf = taskInf;
	}
}
