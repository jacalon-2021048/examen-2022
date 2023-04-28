package org.jhonatan.acalon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 * @author Jhonatan Jose Acalón Ajanel <jacalon-2021048@kinal.edu.gt>
 * @date 28/09/2022
 * @time 11:16:06
 * @codigo IN5BM
 * @jornada Matutina
 * @grupo 1
 */
public class App extends Application {

    private static Scene scene;
    private final String PAQUETE_IMAGES="org/jhonatan/acalon/resources/images/";
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("FXMLMateriaView"),1270, 715);
        Image ico=new Image(PAQUETE_IMAGES+"icono.png");
        stage.getIcons().add(ico);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Materias");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}