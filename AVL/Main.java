public class Main {
    public static void main(String[] args) {
        AVL treeAvl = new AVL();

        treeAvl.inserir(50);
        treeAvl.inserir(1);
        treeAvl.inserir(64);
        treeAvl.inserir(12);
        treeAvl.inserir(18);
        treeAvl.inserir(66);
        treeAvl.inserir(38);
        treeAvl.inserir(95);
        treeAvl.inserir(58);
        treeAvl.inserir(59);
        treeAvl.inserir(70);
        treeAvl.inserir(68);
        treeAvl.inserir(39);
        treeAvl.inserir(62);
        treeAvl.inserir(7);
        treeAvl.inserir(60);
        treeAvl.inserir(43);
        treeAvl.inserir(16);
        treeAvl.inserir(67);
        treeAvl.inserir(34);
        treeAvl.inserir(35);

        treeAvl.remover(50);
        treeAvl.remover(95);
        treeAvl.remover(70);
        treeAvl.remover(60);
        treeAvl.remover(35);

        treeAvl.printTree();
    }
}
