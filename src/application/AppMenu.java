package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class AppMenu extends MenuBar {

	Main main;
	public AppMenu(Main main) {
		super();
		this.main = main;
		initControls();
	}
	
	private void initControls() {
		Menu menuFile = new Menu("File");
        Menu menuTests = new Menu("Tests");
 
        
        this.getMenus().addAll(menuFile, menuTests);
 
        MenuItem newjob = new MenuItem("New job");
        newjob.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
        menuFile.getItems().addAll(newjob);
        newjob.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent t) {
        		main.newJob();
        	}
        });
        
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
        	this.setUseSystemMenuBar(true);
        }
        
        if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
        	MenuItem exit = new MenuItem("Exit");
        	exit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
        	exit.setOnAction(new EventHandler<ActionEvent>() {
        	    public void handle(ActionEvent t) {
        	        System.exit(0);
        	    }
        	});
        	menuFile.getItems().add(exit);
        }
        
        MenuItem deleteJob = new MenuItem("Delete job");
        menuFile.getItems().add(deleteJob);
        deleteJob.setDisable(true);
        
        MenuItem trigger1 = new MenuItem("Trigger GUI Change #1");
        menuTests.getItems().add(trigger1);
        trigger1.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent t) {
        		main.guiTest1();
        	}
        });

        MenuItem trigger2 = new MenuItem("Trigger GUI Change #2");
        menuTests.getItems().add(trigger2);
        trigger2.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent t) {
        		main.guiTest2();
        	}
        });
        
	}
}
