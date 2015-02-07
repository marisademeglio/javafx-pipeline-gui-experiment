package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class Sidebar extends VBox {
	ListView<JobInfo> list = new ListView<JobInfo>();
	ObservableList<JobInfo> data;
	Main main;
	
	public Sidebar(ObservableList<JobInfo> data, Main main) {
		super();
		this.data = data;
		this.main = main;
		initControls();
	}
	
	private void initControls() {
		this.setPadding(new Insets(10));
	    this.setSpacing(8);
	
	    Text title = new Text("Jobs");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
	    this.getChildren().add(title);
	
	    VBox.setVgrow(list, Priority.ALWAYS);
	    
	    list.setItems(data);
	    
	
	    list.setCellFactory(new Callback<ListView<JobInfo>, 
	        ListCell<JobInfo>>() {
	            @Override 
	            public ListCell<JobInfo> call(ListView<JobInfo> list) {
	                return new JobCell();
	            }
	        }
	    );
	
	    list.getSelectionModel().selectedItemProperty().addListener(
	            new ChangeListener<JobInfo>() {
	                public void changed(ObservableValue<? extends JobInfo> ov, 
	                    JobInfo old_val, JobInfo new_val) {
	                    main.notifySidebarSelectChange(new_val);
	            }
	        });
	    
//	    data.addListener(new ListChangeListener<JobInfo>() {
//			@Override
//			public void onChanged(
//					javafx.collections.ListChangeListener.Change<? extends JobInfo> c) {
//				// TODO Auto-generated method stub
//				
//			}
//	     });
	    
	    this.getChildren().add(list);
	    
	}
	
	static class JobCell extends ListCell<JobInfo> {
        @Override
        public void updateItem(JobInfo item, boolean empty) {
        	if (item == null) {
        		return;
        	}
            super.updateItem(item, empty);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(0, 10, 0, 10));

            Circle circle = new Circle();
            Text jobstatus = new Text();
            if (item.getStatus() == JobInfo.JobStatus.DONE) {
            	circle.setFill(Color.BLUE);
                jobstatus.setText("DONE");
            }
            else if (item.getStatus() == JobInfo.JobStatus.ERROR) {
            	circle.setFill(Color.RED);
                jobstatus.setText("ERROR");
            }
            else if (item.getStatus() == JobInfo.JobStatus.IDLE) {
            	circle.setFill(Color.YELLOW);
                jobstatus.setText("IDLE");
            }
            else if (item.getStatus() == JobInfo.JobStatus.RUNNING) {
            	circle.setFill(Color.GREEN);
                jobstatus.setText("RUNNING");
            }
            circle.setCenterX(100.0f);
            circle.setCenterY(100.0f);
            circle.setRadius(10.0f);
            grid.add(circle, 0, 0);
            
            Text jobname = new Text();
            jobname.setText(item.getScriptName());
            jobname.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            grid.add(jobname, 1, 0);
            
            jobstatus.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
            grid.add(jobstatus, 1, 1);
            
            setGraphic(grid);
            
        }
	}
}
