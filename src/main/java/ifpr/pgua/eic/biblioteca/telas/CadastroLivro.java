package ifpr.pgua.eic.biblioteca.telas;

import java.time.LocalDate;

import ifpr.pgua.eic.biblioteca.modelos.Autor;
import ifpr.pgua.eic.biblioteca.repositorios.Biblioteca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroLivro {

    @FXML
    private ComboBox<Autor> cbAutores;

    @FXML
    private DatePicker datePickerAno;

    @FXML
    private TextField tfCapitulos;

    @FXML
    private TextField tfEditora;

    @FXML
    private TextField tfPaginas;

    @FXML
    private TextField tfTitulo;


    private Biblioteca biblioteca;

    public CadastroLivro(Biblioteca biblioteca){
        this.biblioteca = biblioteca;
    }

    public void initialize(){
        cbAutores.getItems().clear();
        cbAutores.getItems().addAll(biblioteca.getAutores());
    }


    @FXML
    void adicionar(ActionEvent event) {
        String titulo = tfTitulo.getText();
        String editora = tfEditora.getText();
        //int anoPublicacao = Integer.valueOf(tfAnoPublicacao.getText());
        
        LocalDate dataSelecionada = datePickerAno.getValue();
        int anoPublicacao = dataSelecionada.getYear();
        
        int paginas = Integer.valueOf(tfPaginas.getText());
        int capitulos = Integer.valueOf(tfCapitulos.getText());
        Autor autor = cbAutores.getSelectionModel().getSelectedItem();

        boolean flag = true;
        String msg = "";

        if(autor == null){
            msg += "O autor deve ser selecionado!!";
            flag = false;
        }

        if(flag){
            boolean ret = biblioteca.cadastraLivro(titulo, autor, anoPublicacao, paginas, editora, capitulos);
            if(ret){
                msg = "Livro cadastrado!!";
                limpar(null);
            }else{
                msg = "Problema no cadastro do livro!!";
            }
        }

        Alert alert = new Alert(AlertType.INFORMATION,msg,ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    void limpar(ActionEvent event) {
        tfTitulo.clear();
        datePickerAno.setValue(LocalDate.now());
        tfCapitulos.clear();
        tfEditora.clear();
        tfPaginas.clear();
        cbAutores.getSelectionModel().clearSelection();
    }

}
