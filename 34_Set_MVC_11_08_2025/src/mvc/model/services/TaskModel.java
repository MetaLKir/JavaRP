package mvc.model.services;

import java.util.ArrayList;
import java.util.List;

import mvc.model.entitites.Task;

public class TaskModel {
	private List<Task> tasks = new ArrayList<>();

	public void addTask(Task task) {
		if (task == null)
			throw new IllegalArgumentException("Task content must not be null");
		tasks.add(task);
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public int size() {
		return tasks.size();
	}
}
