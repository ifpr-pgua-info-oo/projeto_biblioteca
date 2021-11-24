package ifpr.pgua.eic.biblioteca;

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
import java.lang.reflect.Array;
import java.util.ArrayList;

import ifpr.pgua.eic.biblioteca.modelos.Autor;

public class AppArquivos {

    public static void main(String[] args){


        //escreveArquivoTexto();
        //leArquivoTexto();
        escreveArquivoBinario();
        leArquivoBinario();
    }

    public static void leArquivoBinario(){
        try{
            File arq = new File("arquivo.bin");
            FileInputStream fis = new FileInputStream(arq);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<Autor> lista = (ArrayList)ois.readObject();
            
            for(Autor a:lista){
                System.out.println(a.getNome());
            }
            

            ois.close();
            fis.close();

        }catch(IOException e){
            System.out.println("Erro na leitura do arquivo binário!!"+e.getMessage());
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }


    public static void escreveArquivoBinario(){
        Autor a = new Autor("Hugo","hugo@teste.com","12345");
        Autor a1 = new Autor("Hugo1","hugo1@teste.com","123457");
        Autor a2 = new Autor("Hugo2","hugo2@teste.com","123458");
        
        ArrayList<Autor> lista = new ArrayList<>();
        lista.add(a);
        lista.add(a1);
        lista.add(a2);

        try{
            File arq = new File("arquivo.bin");
            FileOutputStream fos = new FileOutputStream(arq);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(lista);

            oos.close();
            fos.close();

        }catch(IOException e){
            System.out.println("Deu ruim na escrita do arquivo binário!!"+e.getMessage());
            e.printStackTrace();
        }


    }


    public static void leArquivoTexto(){

        try{
            File arq = new File("arquivo.txt");
            FileReader fr = new FileReader(arq);
            BufferedReader br = new BufferedReader(fr);

            int linhaDesejada = 3;
            int count = 1;

            String linha = br.readLine();
            while(linha != null){
                if(count == linhaDesejada){
                    System.out.println(linha);
                }
                linha = br.readLine();
                count ++;
            }
            

            br.close();
            fr.close();


        }catch(IOException e){
            System.out.println("Deu ruim na leitura do arquivo texto!!"+e.getMessage());
        }
    }


    public static void escreveArquivoTexto(){
        try{
            File arq = new File("arquivo.txt");
            FileWriter fw = new FileWriter(arq);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Este é meu primeiro arquivo...X");
            bw.newLine();
            bw.write("Esta String contem o número "+10);

            bw.close();
            fw.close();


        }catch(IOException e){
            System.out.println("Deu ruim na escrita do arquivo txt!!"+e.getMessage());
        }
        


        /*if(arq.exists()){
            System.out.println("O arquivo existe...");
        }else{
            System.out.println("O arquivo não existe...");
        }*/



    }
    
}
