package AlturaArvore;

public class No<L> {

    private L valor;
    private No esquerda, direita;

    public No(L valor) {
        this.valor = valor;
    }

    public L getValor() {
        return valor;
    }

    public void setValor(L valor) {
        this.valor = valor;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }
}
