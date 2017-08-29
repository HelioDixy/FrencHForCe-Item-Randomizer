package frenchforce.item_randomizer.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private Stage primaryStage;
	
	public MainApp() {
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.showGui();
	}

	public static void launch() {
		Application.launch();
	}
	
	private void showGui() {

	}
	
}
