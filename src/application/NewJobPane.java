package application;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class NewJobPane extends GridPane {
	
	GridPane subGrid;
	Main main;
	ObservableList<String> scripts = 
		    FXCollections.observableArrayList(
		        "DTBook to ZedAI",
		        "DAISY 2.02 to EPUB 3",
		        "DAISY 3 to EPUB 3",
		        "DTBook Validator"
		    );
		
	public NewJobPane(Main main) {
		this.main = main;
		initControls();
	}
	
	// just have one of each type of control
	private void initControls() {
		this.setPadding(new Insets(10));
		this.setHgap(10);
	    this.setVgap(10);
	    
		Text title = new Text("Choose a script");
		this.add(title,  0,  0);
		
		final ComboBox<String> scriptsCombo = new ComboBox<String>(scripts);
		this.add(scriptsCombo, 1, 0);
		
		scriptsCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			  @Override public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
				  newScriptSelected(newValue);
			  }
			});
		
		subGrid = new GridPane();
		this.add(subGrid, 0, 1);
		
	}
	
	private void newScriptSelected(String name) {
		clearSubGrid();
		populateSubGrid();
	}
	
	private void clearSubGrid() {
		int sz = subGrid.getChildren().size();
		if (sz > 0) {
			subGrid.getChildren().remove(0, sz - 1);
		}
	}
	
	// in the real gui, this function would change based on the script that was selected
	// for this, we'll just add an example of the types of controls that you'd find there
	private void populateSubGrid() {
		Text inputFileTitle = new Text("Input file:");
		subGrid.add(inputFileTitle, 0, 0);
		
		final TextField inputFileText = new TextField();
		subGrid.add(inputFileText, 1, 0);
		
		Button inputFileButton = new Button("...");
		subGrid.add(inputFileButton, 2, 0);
		
		inputFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select File");
                File file = fileChooser.showOpenDialog(null);
                if(file != null) {
                	inputFileText.setText(file.getPath());
                }
			}
		});
		
		Text paramTitle = new Text("Languge code:");
		subGrid.add(paramTitle, 0, 1);
		
		TextField paramField = new TextField();
		subGrid.add(paramField, 1, 1);
		
//		Text booleanTitle = new Text("True or false option:");
//		subGrid.add(booleanTitle, 0, 2);
//		
		CheckBox booleanOption = new CheckBox("Assert valid:");
		subGrid.add(booleanOption, 0, 2);
		
		Button cancel = new Button("Cancel");
		subGrid.add(cancel, 0, 3);
		Button run = new Button("Run");
		subGrid.add(run, 1, 3);
		
		
	}
	

}
