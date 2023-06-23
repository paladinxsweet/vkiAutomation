package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	        Parent loginRoot = loader.load();

	        // Access the controller of the login screen
	        loginController loginController = loader.getController();

	        // Set the action for login success
	        loginController.setLoginSuccessAction(() -> {
	            try {
	                // Load the sample screen FXML file
	                FXMLLoader sampleLoader = new FXMLLoader(getClass().getResource("Sample.fxml"));
	                Parent sampleRoot = sampleLoader.load();
	                SampleController sampleController = sampleLoader.getController();

	                // Perform any necessary initialization or data passing to the sample screen controller

	                // Create the scene for the sample screen
	                Scene sampleScene = new Scene(sampleRoot);

	                // Set the scene for the primary stage
	                primaryStage.setScene(sampleScene);
	                primaryStage.show();

	                // Close the login screen
	                Stage loginStage = (Stage) loginRoot.getScene().getWindow();
	                if(loginStage!=null) {
	                	
	                	loginStage.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });

	        Scene loginScene = new Scene(loginRoot);
	        primaryStage.setScene(loginScene);
	        primaryStage.initStyle(StageStyle.UNDECORATED);
	        primaryStage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}