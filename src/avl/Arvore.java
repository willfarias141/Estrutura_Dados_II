package avl;

public class Arvore<L> {

    private No<L> raiz;

    public void inserir(L valor) {
        inserir(raiz, valor);
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

    private int alt(No no) {
        return (1 + calcularAltura(no.getDireita())) - (1 + calcularAltura(no.getEsquerda()));
    }

    private No balancear(No no) {
        if (no != null) {
            balancear(no.getEsquerda());
            balancear(no.getDireita());
            int df, d = alt(no);
            if (d == 2) {
                df = alt(no.getDireita());
                if (df >= 0) {
                    //System.out.println("RSE " + no.getValor());
                    No auxE = RSE(no);
                    no.setValor(auxE.getValor());
                    no.setEsquerda(auxE.getEsquerda());
                    no.setDireita(auxE.getDireita());
                } else {
                    //System.out.println("RDE " + no.getValor());
                    No auxDE = RDE(no);
                    no.setValor(auxDE.getValor());
                    no.setEsquerda(auxDE.getEsquerda());
                    no.setDireita(auxDE.getDireita());
                }
            } else if (d == -2) {
                df = alt(no.getEsquerda());
                if (df <= 0) {
                    //System.out.println("RSD " + no.getValor());
                    No auxD = RSD(no);
                    no.setValor(auxD.getValor());
                    no.setEsquerda(auxD.getEsquerda());
                    no.setDireita(auxD.getDireita());
                } else {
                    //System.out.println("RDD " + no.getValor());
                    No auxDD = RDD(no);
                    no.setValor(auxDD.getValor());
                    no.setEsquerda(auxDD.getEsquerda());
                    no.setDireita(auxDD.getDireita());
                }
            }
        }
        return no;
    }

    private No RSE(No noDesbalanceado) {
        No novo = new No(noDesbalanceado.getValor());
        if (noDesbalanceado.getDireita() != null) {
            No aux = noDesbalanceado.getEsquerda();
            novo.setEsquerda(aux);
        }
        noDesbalanceado = noDesbalanceado.getDireita();
        if (noDesbalanceado.getEsquerda() == null) {
            noDesbalanceado.setEsquerda(novo);
        } else {
            No novoD = new No(noDesbalanceado.getEsquerda().getValor());
            novo.setDireita(novoD);
            noDesbalanceado.setEsquerda(novo);
        }
        return noDesbalanceado;
    }

    private No RDE(No noDesbalanceado) {
        No aux = RSD(noDesbalanceado.getDireita());
        noDesbalanceado.setDireita(aux);
        return RSE(noDesbalanceado);
    }

    private No RDD(No noDesbalanceado) {
        No aux = RSE(noDesbalanceado.getEsquerda());
        noDesbalanceado.setEsquerda(aux);
        return RSD(noDesbalanceado);
    }

    private No RSD(No noDesbalanceado) {
        No novo = new No(noDesbalanceado.getValor());
        if (noDesbalanceado.getDireita() != null) {
            No aux = noDesbalanceado.getDireita();
            novo.setDireita(aux);
        }
        noDesbalanceado = noDesbalanceado.getEsquerda();
        if (noDesbalanceado.getDireita() == null) {
            noDesbalanceado.setDireita(novo);
        } else {
            No novoD = new No(noDesbalanceado.getDireita().getValor());
            novo.setEsquerda(novoD);
            noDesbalanceado.setDireita(novo);
        }
        return noDesbalanceado;
    }

    private void inserir(No<L> no, L valor) {
        if (vazia()) {
            raiz = new No(valor);
            //System.out.println("Raiz " + raiz.getValor());
        } else {
            int resultComp = ((Comparable<L>) valor).compareTo(no.getValor());
            if (resultComp < 0) {
                if (no.getEsquerda() != null) {
                    inserir(no.getEsquerda(), valor);
                } else {
                    no.setEsquerda(new No(valor));
                    //System.out.println(valor + " inserido a esquerda de " + no.getValor());
                    raiz = balancear(raiz);
                }
            } else if (resultComp > 0) {
                if (no.getDireita() != null) {
                    inserir(no.getDireita(), valor);
                } else {
                    no.setDireita(new No(valor));
                    //System.out.println(valor + " inserido a direita de " + no.getValor());
                    raiz = balancear(raiz);
                }
            } else {
                System.out.println("valor igual");
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

    public void remover(L valor) {
        raiz = excluir(raiz, valor);
        raiz = balancear(raiz);
    }

    private No excluir(No<L> no, L valor) {
        if (vazia()) {
            System.out.println("Árvore vazia");
        } else {
            int resultComp = ((Comparable<L>) valor).compareTo(no.getValor());
            if (resultComp < 0) {
                no.setEsquerda(excluir(no.getEsquerda(), valor));
            } else if (resultComp > 0) {
                no.setDireita(excluir(no.getDireita(), valor));
            } else {
                if (no.getEsquerda() != null && no.getDireita() != null) {
                    /*2 filhos*/
                    System.out.println("\nRemoveu No " + no.getValor());
                    No<L> aux = encontraMinimo(no.getDireita());
                    no.setValor(aux.getValor());
                    no.setDireita(removeMinimo(no.getDireita()));
                } else {
                    System.out.println("\nRemoveu No " + no.getValor());
                    no = (no.getEsquerda() != null) ? no.getEsquerda() : no.getDireita();
                }
                
            }
            
        }
        return no;
    }
    
    private No removeMinimo(No<L> node) {  
        if (node == null) {  
            System.out.println("  ERRO ");  
        } else if (node.getEsquerda() != null) {  
            node.setEsquerda(removeMinimo(node.getEsquerda()));  
            return node;  
        } else {  
            return node.getDireita();
        }  
        return null;  
    }  
  
    private No encontraMinimo(No<L> node) {
        No<L> aux = node;
        if (node != null) {  
            while (node.getEsquerda() != null) {
                aux = node;
                node = node.getEsquerda();
            }
        }
        return node;
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

    public void imprimir() {
        System.out.print("Pré-ordem: ");
        preordem(raiz);
    }
}
