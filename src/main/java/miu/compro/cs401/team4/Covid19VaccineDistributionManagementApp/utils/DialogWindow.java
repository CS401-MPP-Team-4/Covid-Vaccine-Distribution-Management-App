package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils;

import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;

public class DialogWindow<T> {
	Stage dialogStage;
	T controller;

	private DialogWindow(Stage dialogStage, T controller) {
		this.dialogStage = dialogStage;
		this.controller = controller;
	}

	public void showDialog() {
		dialogStage.showAndWait();
	}
	
	public void closeDialog() {
		dialogStage.close();
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public T getController() {
		return controller;
	}

	static public <T> DialogWindow<T> createDialog(String url, Stage parentState) {
		try {

			var loader = App.createFXMLLoader(url);

			Parent page = loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(parentState);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			T controller = loader.getController();

			return new DialogWindow<T>(dialogStage, controller);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
