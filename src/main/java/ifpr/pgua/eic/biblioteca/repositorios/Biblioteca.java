package ifpr.pgua.eic.biblioteca.repositorios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ifpr.pgua.eic.biblioteca.modelos.Autor;
import ifpr.pgua.eic.biblioteca.modelos.ItemAcervo;
import ifpr.pgua.eic.biblioteca.modelos.Livro;
import ifpr.pgua.eic.biblioteca.modelos.Revista;

public class Biblioteca {
    
    private ArrayList<Autor> autores;
    private ArrayList<ItemAcervo> acervo;

    private static final String NOME_ARQUIVO_TXT = "dados.txt";
    private static final String NOME_ARQUIVO_BIN = "dados.bin";

    public Biblioteca(){
        autores = new ArrayList<>();
        acervo = new ArrayList<>();
    }

    public void povoa(){
        autores.clear();

        autores.add(new Autor("Ze Autor","ze@teste.com","001"));
        autores.add(new Autor("Blah Autor","blah@teste.com","002"));
        autores.add(new Autor("Bleh Autor","bleh@teste.com","003"));
        autores.add(new Autor("Blih Autor","blih@teste.com","004"));
        autores.add(new Autor("Xico Autor","xico@teste.com","005"));
        
        acervo.clear();
        acervo.add(new Livro("Livro 1",autores.get(0),1,1,"Editora 1",1));
        acervo.add(new Livro("Livro 2",autores.get(1),2,2,"Editora 2",2));
        acervo.add(new Livro("Livro 3",autores.get(2),3,3,"Editora 3",3));
        acervo.add(new Livro("Livro 4",autores.get(3),4,4,"Editora 4",4));

        acervo.add(new Revista("Revista 1",1,1,1,"Editora 1"));
        acervo.add(new Revista("Revista 2",2,2,2,"Editora 2"));
        acervo.add(new Revista("Revista 3",3,3,3,"Editora 3"));
        acervo.add(new Revista("Revista 4",4,4,4,"Editora 4"));


    }


    public Autor buscaAutorNome(String nome){
        return autores.stream().filter(a -> a.getNome().equals(nome)).findAny().orElseGet(()->null);
    }

    public Autor buscaAutorCpf(String cpf){
        return autores.stream().filter(a -> a.getCpf().equals(cpf)).findFirst().orElseGet(()->null);
    }

    public boolean cadastraAutor(String nome, String email, String cpf){
        Autor a = new Autor(nome, email, cpf);

        if(buscaAutorCpf(cpf)==null){
            autores.add(a);
            return true;
        }
        return false;
    }

    public ItemAcervo buscaItemAcervo(String titulo){
        return acervo.stream().filter(a -> a.getTitulo().equals(titulo)).findAny().orElseGet(()->null);
    }

    public ItemAcervo buscaItemAcervo(String titulo, int numero){

        for(ItemAcervo a:acervo){
            if(a instanceof Revista){
                Revista r = (Revista)a;
                if(r.getNumero()==numero && r.getTitulo().equals(titulo)){
                    return r;
                }
            }
        }
        return null;
    }

    public boolean cadastraLivro(String titulo, Autor a, int anoPublicacao, int numeroPaginas, String editora, int numeroCapitulos){
        if(buscaItemAcervo(titulo) == null){
            Livro l = new Livro(titulo, a, anoPublicacao, numeroPaginas, editora, numeroCapitulos);
            acervo.add(l);
            return true;
        }

        return false;
    }

    public boolean cadastraRevista(String titulo, int numero, int anoPublicacao, int numeroPaginas, String editora){
        if(buscaItemAcervo(titulo,numero) == null){
            Revista r = new Revista(titulo, numero, anoPublicacao, numeroPaginas, editora);
            acervo.add(r);
            return true;
        }

        return false;
    }

    public ArrayList<Autor> getAutores(){
        return autores;
    }

    public ArrayList<Autor> filtraAutoresNome(String parte){
        ArrayList<Autor> retorno = new ArrayList<>();

        for(Autor a:autores){
            if(a.getNome().toLowerCase().startsWith(parte.toLowerCase())){
                retorno.add(a);
            }
        }

        return retorno;

    }

    public ArrayList<Livro> getLivros(){
        ArrayList<Livro> livros = new ArrayList<>();

        for(ItemAcervo a:acervo){
            if(a instanceof Livro){
                livros.add((Livro)a);
            }
        }
        return livros;
    }

    public ArrayList<Revista> getRevistas(){
        ArrayList<Revista> revistas = new ArrayList<>();

        for(ItemAcervo a:acervo){
            if(a instanceof Revista){
                revistas.add((Revista)a);
            }
        }
        return revistas;
    }

    public void salvaDadosTxt() throws IOException {

        File arq = new File(NOME_ARQUIVO_TXT);
        FileWriter fw = new FileWriter(arq);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("[Autores]");
        bw.newLine();
        for(Autor a:autores){
            bw.write(a.paraTexto());
            bw.newLine();
        }

        bw.write("[Revistas]");
        bw.newLine();
        for(ItemAcervo item:acervo){
            if(item instanceof Revista){
                bw.write(item.paraTexto());
                bw.newLine();
            }
        }
        bw.write("[Livros]");
        bw.newLine();
        for(ItemAcervo item:acervo){
            if(item instanceof Livro){
                bw.write(item.paraTexto());
                bw.newLine();
            }
        }
        

        bw.close();
        fw.close();
    
    }

    public void leDadosTxt() throws IOException{
        File arq = new File(NOME_ARQUIVO_TXT);
        FileReader fr = new FileReader(arq);
        BufferedReader br = new BufferedReader(fr);

        String linha = br.readLine();
        int tipo = 0;
        
        while(linha != null){

            switch(linha){
                case "[Autores]":
                    tipo = 0;
                break;
                case "[Revistas]":
                    tipo = 1;
                break;
                case "[Livros]":
                    tipo = 2;
                break;
            }

            if(! linha.startsWith("[") && linha.length()>0){
                if(tipo == 0){
                    String[] partes = linha.split(";");
                    String nome = partes[0];
                    String email = partes[1];
                    String cpf = partes[2];

                    Autor a = new Autor(nome, email, cpf);
                    this.autores.add(a);
                }else if(tipo == 1){
                    String[] partes = linha.split(";");
                    String titulo = partes[0];
                    int anoPublicacao = Integer.parseInt(partes[1]);
                    String editora = partes[2];
                    int numeroPaginas = Integer.parseInt(partes[3]);
                    int numero = Integer.parseInt(partes[4]);
                    
                    Revista revista = new Revista(titulo, numero, anoPublicacao, numeroPaginas, editora);

                    this.acervo.add(revista);

                }
                else if(tipo == 2){
                    String[] partes = linha.split(";");
                    String titulo = partes[0];
                    int anoPublicacao = Integer.parseInt(partes[1]);
                    String editora = partes[2];
                    int numeroPaginas = Integer.parseInt(partes[3]);
                    int numeroCapitulos = Integer.parseInt(partes[4]);
                    String cpfAutor = partes[5];

                    Autor autor = buscaAutorCpf(cpfAutor);

                    Livro l = new Livro(titulo, autor, anoPublicacao, numeroPaginas, editora, numeroCapitulos);

                    this.acervo.add(l);

                }
            }


            linha = br.readLine();
        }

    }

    public void salvaDadosBin() throws IOException{
        File arq = new File(NOME_ARQUIVO_BIN);
        FileOutputStream fos = new FileOutputStream(arq);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(autores);
        oos.writeObject(acervo);

        oos.close();
        fos.close();
    }   

    public void leDadosBin() throws IOException,ClassNotFoundException{

        File arq = new File(NOME_ARQUIVO_BIN);
        FileInputStream fis = new FileInputStream(arq);
        ObjectInputStream ois = new ObjectInputStream(fis);

        autores = (ArrayList) ois.readObject();
        acervo = (ArrayList) ois.readObject();

        ois.close();
        fis.close();


    }




}
