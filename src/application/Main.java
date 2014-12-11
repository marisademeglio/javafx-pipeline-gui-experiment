package application;
	
import java.util.Random;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;


public class Main extends Application {
	ListView<String> list = new ListView<String>();
	ObservableList<String> data = FXCollections.observableArrayList
			("DTBook-to-ZedAI", "DAISY202-to-EPUB3", "DTBook-Validator");

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1024, 768);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("DAISY Pipeline 2");
			
			VBox sidebar = createSidebar();
			root.setLeft(sidebar);
			
			MenuBar menubar = createMenu();
			root.getChildren().addAll(menubar);
			
			GridPane detailview = createDetailView();
			root.setCenter(detailview);
			
			VBox msgs = createMessages();
			root.setBottom(msgs);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private VBox createSidebar() {
		VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

	    Text title = new Text("Jobs");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
	    vbox.getChildren().add(title);

	    VBox.setVgrow(list, Priority.ALWAYS);
	    
	    list.setItems(data);
 
        list.setCellFactory(new Callback<ListView<String>, 
            ListCell<String>>() {
                @Override 
                public ListCell<String> call(ListView<String> list) {
                    return new JobCell();
                }
            }
        );
 
        vbox.getChildren().add(list);
	    
	    return vbox;
	}
	
	private MenuBar createMenu() {
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        Menu menuView = new Menu("View");
 
        
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
 
        MenuItem newjob = new MenuItem("New job");
        menuFile.getItems().addAll(newjob);
        
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
        	menuBar.setUseSystemMenuBar(true);
        }
        
        if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
        	MenuItem exit = new MenuItem("Exit");
        	exit.setOnAction(new EventHandler<ActionEvent>() {
        	    public void handle(ActionEvent t) {
        	        System.exit(0);
        	    }
        	});
        	menuFile.getItems().add(exit);
        }
        
        return menuBar;
	}
	
	private GridPane createDetailView() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10));
		grid.setHgap(10);
        grid.setVgap(10);
		
		Text title = new Text("Job details");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
	    
	    Text script = new Text("Script: DTBook to ZedAI");
	    script.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
	    Text desc = new Text("Converts DTBook to ZedAI");
	    desc.setFont(Font.font("Arial", FontPosture.ITALIC, 15));
	    
	    Text params = new Text("Inputs:\n\tInputFile1.xml\n\tInputFile2.xml \n\nAssert Valid: true\nLanguage: en");
	    params.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
	    
	    grid.add(title, 0, 0);
	    grid.add(script, 0, 1);
	    grid.add(desc, 0, 2);
	    grid.add(params, 0, 3);
	    
	    Hyperlink loglink = new Hyperlink();
	    loglink.setText("Log file");
	    
	    Hyperlink outputlink = new Hyperlink();
	    outputlink.setText("Results");
	    
	    grid.add(loglink, 0, 4);
	    grid.add(outputlink, 0, 5);
	    
	    
	    return grid;
	    
	    
	    
	}
	
	private VBox createMessages() {
		Text title = new Text("Messages");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 15));
	    
		String msgs = "INFO lorem ipsum \nINFO lorem ipsum \nINFO lorem ipsum \nINFO lorem ipsum \nINFO lorem ipsum \nINFO lorem ipsum \n";
		VBox vbox = new VBox();
		TextArea text = new TextArea();
		text.setText(msgs);
		vbox.getChildren().add(title);
		vbox.getChildren().add(text);
		return vbox;
		
	}
	static class JobCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
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
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(3);
            if (randomInt == 0) {
            	// 'error'
            	circle.setFill(Color.RED);
            	jobstatus.setText("ERROR");
            }
            else if (randomInt == 1) {
            	// 'running'
            	circle.setFill(Color.GREEN);
            	jobstatus.setText("RUNNING");
            }
            else {
            	// 'idle'
            	circle.setFill(Color.YELLOW);
            	jobstatus.setText("IDLE");
            }
            circle.setCenterX(100.0f);
            circle.setCenterY(100.0f);
            circle.setRadius(10.0f);
            grid.add(circle, 0, 0);
            
            Text jobname = new Text();
            jobname.setText(item);
            jobname.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            grid.add(jobname, 1, 0);
            
            jobstatus.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
            grid.add(jobstatus, 1, 1);
            
            setGraphic(grid);
            
        }
    }
}
