package ifpr.pgua.eic.biblioteca;

import ifpr.pgua.eic.biblioteca.repositorios.Biblioteca;
import ifpr.pgua.eic.biblioteca.telas.CadastroAutor;
import ifpr.pgua.eic.biblioteca.telas.CadastroLivro;
import ifpr.pgua.eic.biblioteca.telas.CadastroRevista;
import ifpr.pgua.eic.biblioteca.telas.Home;
import ifpr.pgua.eic.biblioteca.telas.Listas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * JavaFX App
 */
public class App extends Application {

    private Biblioteca biblioteca;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        biblioteca = new Biblioteca();
        
        /*SOMENTE EM TEMPO DE DESENVOLVIMENTO*/
        /*DESABILITAR EM PRODUCAO*/
        //biblioteca.povoa();

        biblioteca.leDadosTxt();
        //biblioteca.leDadosBin();
        
        Parent root = loadTela("fxml/home.fxml", (o)->new Home(biblioteca));

        Scene scene = new Scene(root, 720, 480);
        
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        biblioteca.salvaDadosTxt();
        //biblioteca.salvaDadosBin();
    }
    
    public static Parent loadTela(String fxml, Callback controller){
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(fxml));
            loader.setControllerFactory(controller);

            root = loader.load();
            
        }catch (Exception e){
            System.out.println("Problema no arquivo fxml. Está correto?? "+fxml);
        }
        return root;   
    }


    public static void main(String[] args) {
        launch();
    }

}