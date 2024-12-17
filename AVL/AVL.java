import java.util.Stack;

public class AVL {
    private No raiz;

    public int getFatoBalanceamento(No no){
        if(no == null){
            return 0;
        }
        return altura(no.esq) - altura(no.dir);
    }

    public int altura(No no){
        if(no == null){
            return 0;
        }
        return no.alt;
    }

    public void atualizarAltura(No no){
        no.alt = 1 + Math.max(altura(no.esq), altura(no.dir));
    }

    public boolean buscar(int valor) {
        return buscar(valor, this.raiz);
    }

    private boolean buscar(int valor, No noRaiz){
        if (noRaiz == null) {
            return false;
        }

        if (noRaiz.valor == valor) {
            return true;
        }
        
        if (valor < noRaiz.valor) {
            return buscar(valor, noRaiz.esq);
        }else{
            return buscar(valor, noRaiz.dir);
        }
    }

    public void inserir(int valor){
        this.raiz = inserir(raiz, valor);
    }

    private No inserir(No noRaiz, int valor){
        if (noRaiz == null) {
            return new No(valor);
        }

        if (valor < noRaiz.valor) {
            noRaiz.esq = inserir(noRaiz.esq, valor);
        }else if (valor > noRaiz.valor) {
            noRaiz.dir = inserir(noRaiz.dir, valor);
        }else{
            return noRaiz;
        }
        atualizarAltura(noRaiz);
        return rebalancear(noRaiz);
    }

    public void remover(int valor) {
        raiz = remover(this.raiz, valor);
    }

    private No remover(No noRaiz, int valor){
        if (noRaiz == null) {
            return null;
        }

        if (valor < noRaiz.valor) {
            noRaiz.esq = remover(noRaiz.esq, valor);
        }else if (valor > noRaiz.valor) {
            noRaiz.dir = remover(noRaiz.dir, valor);
        }else{
            if (noRaiz.esq == null && noRaiz.dir == null) {
                return null;
            }else if (noRaiz.esq == null || noRaiz.dir == null) {
                if (noRaiz.esq != null) {
                    noRaiz = noRaiz.esq;
                }else{
                    noRaiz = noRaiz.dir;
                }
            }else{
                No temp = getMin(noRaiz.dir);
                noRaiz.valor = temp.valor;
                noRaiz.dir = remover(noRaiz.dir, temp.valor);
            }
        }
        atualizarAltura(noRaiz);
        return rebalancear(noRaiz);
    }

    public No rotacaoDireita(No A){
        No B = A.esq;
        No Bdir = B.dir;

        B.dir = A;
        A.esq = Bdir;
        atualizarAltura(A);
        atualizarAltura(B);

        return B;
    }

    public No rotacaoEsquerda(No A){
        No B = A.dir;
        No Besq = B.esq;

        B.esq = A;
        A.dir = Besq;

        atualizarAltura(A);
        atualizarAltura(B);

        return B;
    }

    private No rebalancear(No no){
        int fatoBalanceamento = getFatoBalanceamento(no);

        if (fatoBalanceamento > 1 && getFatoBalanceamento(no.esq) >= 0){
            return rotacaoDireita(no);
        }

        if (fatoBalanceamento > 1 && getFatoBalanceamento(no.esq) < 0) {
            no.esq = rotacaoEsquerda(no.esq);
            return rotacaoDireita(no);
        }

        if (fatoBalanceamento < -1 && getFatoBalanceamento(no.dir) <= 0) {
            return rotacaoEsquerda(no);
        }

        if (fatoBalanceamento < -1 && getFatoBalanceamento(no.dir) > 0) {
            no.dir = rotacaoDireita(no.dir);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void printTree() {
        Stack globalStack = new Stack();
        globalStack.push(raiz);
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) {
                No temp = (No) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.valor);
                    localStack.push(temp.esq);
                    localStack.push(temp.dir);
                    if (temp.esq != null ||
                            temp.dir != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }
        System.out.println(separator);
    }

    private No getMin(No raiz) {
        if (raiz == null)
            return raiz;
        if (raiz.esq != null) {
            return getMin(raiz.esq);
        }
        return raiz;
    }
}
