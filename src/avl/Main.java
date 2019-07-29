package avl;

public class Main {

    public static void main(String[] args) {
        Arvore a = new Arvore();
        a.inserir(30);
        a.inserir(50);
        a.inserir(80);
        a.inserir(20);
        a.inserir(10);
        a.inserir(25);
        a.inserir(70);
        a.imprimir();
        a.remover(30);
        a.imprimir();
        a.remover(20);
        a.imprimir();
        a.remover(10);
        a.imprimir();
        a.remover(25);
        a.imprimir();
        a.inserir(75);
        System.out.println("");
        a.imprimir();
        a.remover(50);
        a.imprimir();
    }
    
}
