package pe.edu.upeu.excel.Excel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ExcelApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExcelViewer.fxml"));
		Parent root = loader.load();
		
		primaryStage.setTitle("Excel Viewer - Importar/Exportar");
		primaryStage.setScene(new Scene(root, 1000, 600));
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(500);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
