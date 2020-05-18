package sample;

public class Objeto {

    boolean nInicial;
    boolean nFinal;
    int NodoOrigen;
    int NodoDestino;
    String validacion;

    public Objeto(boolean nInicial, boolean nFinal, int nodoOrigen, int nodoDestino, String validacion) {
        this.nInicial = nInicial;
        this.nFinal = nFinal;
        NodoOrigen = nodoOrigen;
        NodoDestino = nodoDestino;
        this.validacion = validacion;
    }

    public Objeto(int origen, int destino, String validacion) {
        this.NodoOrigen = origen;
        this.NodoDestino = destino;
        this.validacion = validacion;
    }

    public boolean isnInicial() {
        return nInicial;
    }

    public void setnInicial(boolean nInicial) {
        this.nInicial = nInicial;
    }

    public boolean isnFinal() {
        return nFinal;
    }

    public void setnFinal(boolean nFinal) {
        this.nFinal = nFinal;
    }


    public int getNodoOrigen() {
        return NodoOrigen;
    }

    public void setNodoOrigen(int nodoOrigen) {
        NodoOrigen = nodoOrigen;
    }

    public int getNodoDestino() {
        return NodoDestino;
    }

    public void setNodoDestino(int nodoDestino) {
        NodoDestino = nodoDestino;
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }
}
