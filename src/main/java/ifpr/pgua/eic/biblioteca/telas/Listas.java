package ifpr.pgua.eic.biblioteca.telas;

import ifpr.pgua.eic.biblioteca.modelos.Autor;
import ifpr.pgua.eic.biblioteca.modelos.ItemAcervo;
import ifpr.pgua.eic.biblioteca.modelos.Livro;
import ifpr.pgua.eic.biblioteca.modelos.Revista;
import ifpr.pgua.eic.biblioteca.repositorios.Biblioteca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;

public class Listas {
    
    @FXML
    private ListView<Autor> listaAutores;
    @FXML
    private ListView<ItemAcervo> listaAcervo;
    
    @FXML
    private RadioButton rbTodos;

    @FXML
    private RadioButton rbLivros;

    @FXML
    private RadioButton rbRevistas;

    @FXML
    private TextField tfNomeAutor;

    @FXML
    private TextArea taStatus;

    @FXML
    private ImageView imgView;

    private Biblioteca biblioteca;

    public Listas(Biblioteca biblioteca){
        this.biblioteca = biblioteca;
    }

    public void initialize(){
        
        listaAutores.getItems().addAll(biblioteca.getAutores());
        
        
        listaAcervo.getItems().addAll(biblioteca.getLivros());
        listaAcervo.getItems().addAll(biblioteca.getRevistas());
    }

    @FXML
    private void mostraAcervo(ActionEvent evt){

        if(evt.getSource() == rbTodos){
            listaAcervo.getItems().clear();
            listaAcervo.getItems().addAll(biblioteca.getLivros());
            listaAcervo.getItems().addAll(biblioteca.getRevistas());
        }else if(evt.getSource() == rbLivros){
            listaAcervo.getItems().clear();
            listaAcervo.getItems().addAll(biblioteca.getLivros());
        }else if(evt.getSource() == rbRevistas){
            listaAcervo.getItems().clear();
            listaAcervo.getItems().addAll(biblioteca.getRevistas());
        }
    }

    @FXML
    private void filtraAutores(KeyEvent evt){
        
        String parte = tfNomeAutor.getText();
        //System.out.println(parte);

        if(parte.length() >= 2 ){
            listaAutores.getItems().clear();
            listaAutores.getItems().addAll(biblioteca.filtraAutoresNome(parte));
        }else{
            listaAutores.getItems().clear();
            listaAutores.getItems().addAll(biblioteca.getAutores());
        }

    }

    @FXML 
    private void atualizaStatus(MouseEvent evt){

        String msg = "";

        if(evt.getSource() == listaAutores){
            Autor item = listaAutores.getSelectionModel().getSelectedItem();

            if(item != null){
                msg = item.toString();
            }
        }else if(evt.getSource() == listaAcervo){
            ItemAcervo item = listaAcervo.getSelectionModel().getSelectedItem();

            if(item != null){
                msg = item.toString();
            }
        }

        taStatus.setText(msg);

    }
}
