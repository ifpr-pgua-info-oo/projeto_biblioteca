package ifpr.pgua.eic.biblioteca.telas;

import ifpr.pgua.eic.biblioteca.modelos.Autor;
import ifpr.pgua.eic.biblioteca.modelos.ItemAcervo;
import ifpr.pgua.eic.biblioteca.repositorios.Biblioteca;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;

public class Listas {
    
    private FlowPane root;

    private ListView<Autor> listaAutores;
    private ListView<ItemAcervo> listaAcervo;
    
    private Biblioteca biblioteca;

    public Listas(Biblioteca biblioteca){
        this.biblioteca = biblioteca;
        inicializa();
    }

    private void inicializa(){
        
        root = new FlowPane();

        root.setVgap(5.0);
        root.setHgap(5.0);
        root.setPadding(new Insets(8.0));


        listaAutores = new ListView<>();
        
        /*for(Autor a:biblioteca.getAutores()){
            listaAutores.getItems().add(a);
        }*/

        listaAutores.getItems().addAll(biblioteca.getAutores());
        root.getChildren().add(listaAutores);
        
        listaAcervo = new ListView<>();
        listaAcervo.getItems().addAll(biblioteca.getRevistas());
        listaAcervo.getItems().addAll(biblioteca.getLivros());
        root.getChildren().add(listaAcervo);

    }

    public Parent getRoot(){
        return this.root;
    }

}
