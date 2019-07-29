package AlturaArvore;

public class Arvore<L> {

    public No<L> raiz;

    public void inserir(L valor) {
        inserir(raiz, valor);
    }
    
    public void rotacaoSE(No<L> noDesbalanceado){
        No<L> auxRaiz = noDesbalanceado.getDireita();
        if(auxRaiz.getEsquerda() != null){
            noDesbalanceado.setDireita(auxRaiz.getEsquerda());
        } else {
            noDesbalanceado.setDireita(null);
        }
        auxRaiz.setEsquerda(noDesbalanceado);
        raiz = auxRaiz;
    }
    
    public void rotacaoSD(No<L> noDesbalanceado){
        No<L> auxRaiz = noDesbalanceado.getEsquerda();
        if(auxRaiz.getDireita() != null){
            auxRaiz = auxRaiz.getDireita();
            noDesbalanceado.getEsquerda().setDireita(null);
            auxRaiz.setEsquerda(noDesbalanceado.getEsquerda());
            noDesbalanceado.setEsquerda(null);
            auxRaiz.setDireita(noDesbalanceado);
        } else {
            noDesbalanceado.setEsquerda(null);
        }
        auxRaiz.setDireita(noDesbalanceado);
        raiz = auxRaiz;
    }

    private void inserir(No<L> no, L valor) {
        if (vazia()) {
            raiz = new No(valor);
        } else {
            int resultComp = ((Comparable<L>) valor).compareTo(no.getValor());
            if (resultComp < 0) {
                if (no.getEsquerda() != null) {
                    inserir(no.getEsquerda(), valor);
                } else {
                    no.setEsquerda(new No(valor));
                }
            } else if (resultComp > 0) {
                if (no.getDireita() != null) {
                    inserir(no.getDireita(), valor);
                } else {
                    no.setDireita(new No(valor));
                }
            } else {
                System.out.println("valor igual");
            }
        }
    }
    
    private int calcularAltura(No<L> no) {
        if (no == null) {
            return -1;
        } else {
            int he = calcularAltura(no.getEsquerda());
            int hd = calcularAltura(no.getDireita());
            if (he > hd) {
                return he += 1;
            } else {
                return hd += 1;
            }
        }
    }

    public boolean vazia() {
        return raiz == null;
    }

    public void preordem(No<L> node) {
        if (node != null) {
            System.out.print(node.getValor() + " ");
            preordem(node.getEsquerda());
            preordem(node.getDireita());
        }
    }

    public void posordem(No<L> node) {
        if (node != null) {
            posordem(node.getEsquerda());
            posordem(node.getDireita());
            System.out.print(node.getValor() + " ");
        }
    }

    private void ordem(No<L> no) {
        if (no != null) {
            ordem(no.getEsquerda());
            System.out.print(no.getValor() + " ");
            ordem(no.getDireita());
        }
    }

    public void imprimirEmOrdem() {
        ordem(raiz);
    }
}
