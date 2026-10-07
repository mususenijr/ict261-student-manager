package zm.ac.mu.ict261.studentapp.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
            Objects.requireNonNull(getClass().getResource("/view/StudentForm.fxml"))
        );
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(
            Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm()
        );
        stage.setTitle("ICT261 Student Services");
        stage.setScene(scene);
        stage.setMinWidth(850);
        stage.setMinHeight(550);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
