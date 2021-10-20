package ifpr.pgua.eic.biblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        var scene = new Scene(new VBox(), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    private Parent loadTela(String fxml, Callback controller){
        Parent root = null;
        
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxml));
            loader.setControllerFactory(controller);

            root = loader.load();

        }catch(Exception e){
            System.out.println("Erro no carregamento do fxml! O arquivo está correto???"+fxml);
        }

        return root;
    }


    public static void main(String[] args) {
        launch();
    }

}