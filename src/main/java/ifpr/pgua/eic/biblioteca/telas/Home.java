package ifpr.pgua.eic.biblioteca.telas;

import ifpr.pgua.eic.biblioteca.App;
import ifpr.pgua.eic.biblioteca.repositorios.Biblioteca;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Home {
    
    private Biblioteca biblioteca;
    
    @FXML
    private StackPane painelCentral;
    @FXML
    private BorderPane root;

    @FXML
    private ToggleButton btTema;

    private String tema = "light";


    public Home(Biblioteca biblioteca){
        this.biblioteca = biblioteca;
    }


    @FXML
    private void loadListas(){
        painelCentral.getChildren().clear();
        painelCentral.getChildren().add(App.loadTela("fxml/listas.fxml", (o)->new Listas(biblioteca)));
    }

    @FXML
    private void loadCadastraAutor(){
        painelCentral.getChildren().clear();
        CadastroAutor telaAutor = new CadastroAutor(biblioteca);
        painelCentral.getChildren().add(telaAutor.getRoot());
    }

    @FXML
    private void loadCadastraRevista(){
        painelCentral.getChildren().clear();
        painelCentral.getChildren().add(App.loadTela("fxml/cadastro_revista.fxml", (o)->new CadastroRevista(biblioteca)));
    }

    @FXML
    private void loadCadastraLivro(){
        painelCentral.getChildren().clear();
        painelCentral.getChildren().add(App.loadTela("fxml/cadastro_livro.fxml", (o)->new CadastroLivro(biblioteca)));
    }

    @FXML
    private void mudaTema(){
        if(tema.equals("light")){
            tema = "dark";
            btTema.setText("Light Mode");
            root.getStylesheets().remove(App.class.getResource("css/estilo.css").toExternalForm());
            root.getStylesheets().add(App.class.getResource("css/estilo_dark.css").toExternalForm());
            
        }else{
            tema = "light";
            btTema.setText("Dark Mode");
            root.getStylesheets().add(App.class.getResource("css/estilo.css").toExternalForm());
            root.getStylesheets().remove(App.class.getResource("css/estilo_dark.css").toExternalForm());
        
        }
    }
}
