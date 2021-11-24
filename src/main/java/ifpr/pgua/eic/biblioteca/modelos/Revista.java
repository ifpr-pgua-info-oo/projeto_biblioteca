package ifpr.pgua.eic.biblioteca.modelos;

public class Revista extends ItemAcervo{
    private int numero;


    public Revista(String titulo, int numero, int anoPublicacao, int numeroPaginas, String editora) {
        super(titulo, anoPublicacao, numeroPaginas, editora);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String paraTexto(){
        return super.paraTexto()+";"+numero;
    }

}
