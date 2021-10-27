package ifpr.pgua.eic.biblioteca;

import ifpr.pgua.eic.biblioteca.repositorios.Biblioteca;
import ifpr.pgua.eic.biblioteca.telas.CadastroAutor;
import ifpr.pgua.eic.biblioteca.telas.CadastroRevista;
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
    private StackPane central;
    private VBox botoes;
    private BorderPane root;

    @Override
    public void start(Stage stage) {
        
        biblioteca = new Biblioteca();
        root = new BorderPane();

        central = new StackPane();
        botoes = new VBox();

        botoes.setSpacing(5.0);
        botoes.setPadding(new Insets(8.0));
        
        Button btListas = new Button("Listas");
        btListas.setOnAction((evt)->{
            central.getChildren().clear();
            central.getChildren().add(new Listas(biblioteca).getRoot());
        });

        botoes.getChildren().add(btListas);

        Button btCadastroAutor = new Button("Cadastro Autor");
        btCadastroAutor.setOnAction((evt)->{
            central.getChildren().clear();
            CadastroAutor telaAutor = new CadastroAutor(biblioteca);
            central.getChildren().add(telaAutor.getRoot());
        });

        botoes.getChildren().addAll(btCadastroAutor);
        

        Button btCadastroRevista = new Button("Cadastro Revista");
        btCadastroRevista.setOnAction((evt)->{
            central.getChildren().clear();
            central.getChildren().add(loadTela("fxml/cadastro_revista.fxml", (o)->new CadastroRevista(biblioteca)));
        });

        botoes.getChildren().add(btCadastroRevista);

        root.setCenter(central);
        root.setLeft(botoes);

        Scene scene = new Scene(root, 640, 480);
        
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
            
        }catch (Exception e){
            System.out.println("Problema no arquivo fxml. Está correto?? "+fxml);
        }
        return root;   
    }


    public static void main(String[] args) {
        launch();
    }

}