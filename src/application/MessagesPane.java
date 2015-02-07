package application;

import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MessagesPane extends VBox {

	ListView<String> messages;
	Main main;
	
	public MessagesPane(Main main) {
		super();
		this.main = main;
		initControls();
	}
	
	public void setJobInfo(JobInfo data) {
		messages.setItems(data.getMessages());
	}
	private void initControls() {
		Text title = new Text("Messages");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 15));
	    
	    messages = new ListView<String>();
	    
		this.getChildren().add(title);
		this.getChildren().add(messages);
	
	}
	
}
