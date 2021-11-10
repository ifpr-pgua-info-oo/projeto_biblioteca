package ifpr.pgua.eic.biblioteca.telas;

import ifpr.pgua.eic.biblioteca.repositorios.Biblioteca;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroRevista {
    
    @FXML
    private TextField tfTitulo;

    @FXML
    private TextField tfEditora;

    @FXML
    private TextField tfAnoPublicacao;

    @FXML
    private TextField tfPaginas;

    @FXML
    private TextField tfNumero;


    private Biblioteca biblioteca;

    public CadastroRevista(Biblioteca biblioteca){
        this.biblioteca = biblioteca;
    }

    @FXML
    private void cadastrar(){
        String titulo = tfTitulo.getText();
        String editora = tfEditora.getText();
        int anoPublicacao = Integer.valueOf(tfAnoPublicacao.getText());
        int numero = Integer.valueOf(tfNumero.getText());
        int paginas = Integer.valueOf(tfPaginas.getText());
        String msg="";
        //cadastrar na biblioteca
        
        boolean ret = biblioteca.cadastraRevista(titulo, numero, anoPublicacao, paginas, editora);
        
        if(ret){
            msg = "Revista cadastrada!!";
            limpar();
        }else{
            msg = "Erro no cadastro de revista!!";
        }

        Alert alert = new Alert(AlertType.INFORMATION,msg,ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private void limpar(){
        tfTitulo.setText("");
        tfEditora.setText("");
        tfAnoPublicacao.setText("");
        tfNumero.setText("");
        tfPaginas.setText("");  
    }

}
