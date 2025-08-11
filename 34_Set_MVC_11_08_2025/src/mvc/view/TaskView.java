package mvc.view;

import java.time.format.DateTimeFormatter;
import java.util.List;

import mvc.model.entitites.Task;

public class TaskView {
	private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public void showTasks(List<Task> tasks) {
		if (tasks == null || tasks.isEmpty()) {
			System.out.println("The task list is empty\n");
			return;
		}
		System.out.println("Task list:");
		for (int i = 0; i < tasks.size(); i++) {
			Task t = tasks.get(i);
			System.out.printf("%d. %s at [%s]\n", i + 1, t.getContent(), t.getCreatedAt().format(FMT));
		}
		System.out.println();
	}

	public void showMessage(String message) {
		System.out.println(message);
	}

	public void showHelp() {
		System.out.println("""
				Commands:
				- just text 	-> add task
				- list 		-> show task list
				- help 		-> show help
				- exit		-> exit
				""");
	}

}
