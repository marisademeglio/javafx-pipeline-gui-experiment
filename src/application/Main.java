package application;
	
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	ObservableList<JobInfo> data = AppDataHelper.createJobs();
	Sidebar sidebar;
	DetailsPane detailsPane;
	MessagesPane messagesPane;
	AppMenu menubar;
	BorderPane root;
	NewJobPane newJobPane;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = new BorderPane();
			Scene scene = new Scene(root,1024, 768);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("DAISY Pipeline 2");
			
			sidebar = new Sidebar(data, this);
			root.setLeft(sidebar);
			
			menubar = new AppMenu(this);
			root.getChildren().addAll(menubar);
			
			detailsPane = new DetailsPane(this);
			root.setCenter(detailsPane);
			
			newJobPane = new NewJobPane(this);
			
			messagesPane = new MessagesPane(this);
			root.setBottom(messagesPane);
			
			messagesPane.setPrefHeight(150);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void notifySidebarSelectChange(JobInfo newInfo) {
		if (root.getCenter() != detailsPane) {
			root.setCenter(detailsPane);
		}
		detailsPane.setJobInfo(newInfo);
		messagesPane.setJobInfo(newInfo);
	}
	
	public void guiTest1() {
		data.get(2).setStatus(JobInfo.JobStatus.DONE);
	}
	
	public void guiTest2() {
		data.get(3).setStatus(JobInfo.JobStatus.RUNNING);
	}
	
	public void newJob() {
		if (root.getCenter() != newJobPane) {
			root.setCenter(newJobPane);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
