package ifpr.pgua.eic.biblioteca.modelos;

import java.io.Serializable;

public abstract class ItemAcervo implements Serializable {
    private String titulo;
    private int anoPublicacao;
    private int numeroPaginas;
    private String editora;
    
    public ItemAcervo(String titulo, int anoPublicacao, int numeroPaginas, String editora) {
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.numeroPaginas = numeroPaginas;
        this.editora = editora;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String toString(){
        return titulo;
    }

    public String paraTexto(){
        return titulo+";"+anoPublicacao+";"+editora+";"+numeroPaginas;
    }

}
