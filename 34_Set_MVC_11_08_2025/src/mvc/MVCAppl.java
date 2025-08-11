package mvc;

import mvc.controller.TaskController;

public class MVCAppl {
	public static void main(String[] args) {
//		TaskModel model = new TaskModel();
//		TaskView view = new TaskView();
//		TaskController controller = new TaskController(model, view);

		TaskController controller = new TaskController();
		controller.start();
	}
}
