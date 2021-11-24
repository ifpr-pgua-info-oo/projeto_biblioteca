package ifpr.pgua.eic.biblioteca.modelos;

import java.io.Serializable;

public class Livro extends ItemAcervo {
    private int numeroCapitulos;
    private Autor autor;
    
    public Livro(String titulo, Autor autor, int anoPublicacao, int numeroPaginas, String editora, int numeroCapitulos
            ) {
        super(titulo, anoPublicacao, numeroPaginas, editora);
        this.numeroCapitulos = numeroCapitulos;
        this.autor = autor;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getNumeroCapitulos() {
        return numeroCapitulos;
    }

    public void setNumeroCapitulos(int numeroCapitulos) {
        this.numeroCapitulos = numeroCapitulos;
    } 

    public String paraTexto(){
        return super.paraTexto()+";"+numeroCapitulos+";"+autor.getCpf();
    }

}
