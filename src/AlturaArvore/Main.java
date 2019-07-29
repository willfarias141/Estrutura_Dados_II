package AlturaArvore;

public class Main {

    public static void main(String[] args) {
        Arvore<Integer> a = new Arvore<Integer>();
        a.inserir(4);
        a.inserir(2);
        a.inserir(3);
        System.out.print("pre: ");
        a.preordem(a.raiz);
        System.out.print("\nRSD");
        a.rotacaoSD(a.raiz);
        System.out.print("\npre: ");
        a.preordem(a.raiz);
    }
}
