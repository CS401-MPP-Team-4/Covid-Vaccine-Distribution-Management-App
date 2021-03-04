package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	public static Stage primaryStage;
	static final String APP_TITLE = "CVDMS";

	@Override
	public void start(Stage stage) throws IOException {
		primaryStage = stage;
		scene = new Scene(loadFXML(Navigations.MASTER.getValue()), (stage.getX() + stage.getWidth()), stage.getY());
		scene.getStylesheets().addAll(this.getClass().getResource("css/style.css").toExternalForm());
		stage.setTitle(APP_TITLE);
		stage.setScene(scene);
		stage.show();
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	public static FXMLLoader createFXMLLoader(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader;
	}
	
	public static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = createFXMLLoader(fxml);
		return fxmlLoader.load();
	}

	public static void setUppAppSubTitle(String subTitle) {
		primaryStage.setTitle(APP_TITLE + " - " + subTitle);
	}
	
	public static void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR, message);
		alert.setTitle(APP_TITLE);
		alert.showAndWait();
	}

	public static void showSuccess(String message) {
		Alert alert = new Alert(AlertType.INFORMATION, message);
		alert.setTitle(APP_TITLE);
		alert.showAndWait();
	}

	public static void showSuccess() {
		showSuccess("Successful!");
	}

	public static Optional<ButtonType> showConfirm(String message) {
		Alert alert = new Alert(AlertType.CONFIRMATION, message);
		alert.setTitle("CVDMS");
		return alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}

}