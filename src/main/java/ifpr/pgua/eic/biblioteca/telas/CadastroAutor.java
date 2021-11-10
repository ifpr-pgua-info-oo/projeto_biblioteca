package ifpr.pgua.eic.biblioteca.telas;

import ifpr.pgua.eic.biblioteca.repositorios.Biblioteca;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CadastroAutor {
    
    private VBox root;
    
    private Label lbNome;
    private TextField tfNome;
    private Label lbEmail;
    private TextField tfEmail;
    private Label lbCpf;
    private TextField tfCpf;

    private Button btAcao;
    private Button btLimpar;
    
    private Biblioteca biblioteca;

    public CadastroAutor(Biblioteca biblioteca){
        this.biblioteca = biblioteca;
        inicializa();
    }

    private void inicializa(){
        root = new VBox();

        root.setSpacing(5.0);
        root.setPadding(new Insets(8.0));

        lbNome = new Label("Nome:");
        lbNome.setLabelFor(tfNome);
        tfNome = new TextField();
        root.getChildren().addAll(lbNome,tfNome);

        lbEmail = new Label("Email:");
        lbEmail.setLabelFor(tfEmail);
        tfEmail = new TextField();
        root.getChildren().addAll(lbEmail,tfEmail);

        lbCpf = new Label("Cpf:");
        lbCpf.setLabelFor(tfCpf);
        tfCpf = new TextField();
        root.getChildren().addAll(lbCpf,tfCpf);

        btAcao = new Button("Adicionar");
        
        //sempre no futuro
        btAcao.setOnAction((evento)->{
            cadastra();    
        });

        
        btLimpar = new Button("Limpar");
        //btLimpar.setStyle("-fx-background-color: red; -fx-text-fill:blue; -fx-background-radius: 10px");
        btLimpar.getStyleClass().add("botao-limpar");
        btLimpar.setOnAction((evento)->{
            limpar();
        });

        HBox botoes = new HBox();

        botoes.setSpacing(5.0);
        botoes.setPadding(new Insets(8.0));
        botoes.setAlignment(Pos.CENTER_RIGHT);

        botoes.getChildren().add(btAcao);
        botoes.getChildren().add(btLimpar);

        root.getChildren().add(botoes);
    }   

    private void limpar(){
        tfNome.setText("");
        tfEmail.setText("");
        tfCpf.setText("");
    }

    private void cadastra(){

        String nome = tfNome.getText();
        String email = tfEmail.getText();
        String cpf = tfCpf.getText();

        boolean flag = true;
        String msg = "";


        if(nome.isEmpty() || nome.isBlank()){
            msg = "Nome não pode ser vazio!";
            flag = false;
        }

        if(cpf.isEmpty() || cpf.isBlank()){
            msg += "\nCPF não pode ser vazio!";
            flag = false;
        }

        if(email.isEmpty() || email.isBlank()){
            msg += "\nEmail não pode ser vazio!";
            flag = false;
        }

        if(flag){
            boolean ret = biblioteca.cadastraAutor(nome, email, cpf);
            if(ret){
                System.out.println(biblioteca.getAutores());
                
                limpar();

                msg = "Autor cadastrado!";
            }else{
                msg = "Autor não cadastrado! CPF repetido!";
            }
        }

        Alert alert = new Alert(AlertType.INFORMATION,msg,ButtonType.OK);
        alert.showAndWait();

    }

    public VBox getRoot(){
        return root;
    }

}
