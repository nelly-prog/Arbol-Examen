package binario;

import java.util.Scanner;

public class Binario {
    class Nodo {
        int valor;
        Nodo izquierdo, derecho;

        Nodo(int item) {
            valor = item;
            izquierdo = derecho = null;
        }
    }

    Nodo raiz;

    int altura(Nodo nodo) {
        if (nodo == null) {
            return -1; 
        } else {
            int alturaIzquierda = altura(nodo.izquierdo);
            int alturaDerecha = altura(nodo.derecho);
            return Math.max(alturaIzquierda, alturaDerecha) + 1;
        }
    }

    int peso(Nodo nodo) {
        if (nodo == null) {
            return 0; 
        } else {
            return 1 + peso(nodo.izquierdo) + peso(nodo.derecho);
        }
    }

    int nivel(Nodo nodo, int valor, int nivelActual) {
        if (nodo == null) {
            return -1; 
        }
        if (nodo.valor == valor) {
            return nivelActual; 
        }

        int nivelIzquierdo = nivel(nodo.izquierdo, valor, nivelActual + 1);
        if (nivelIzquierdo != -1) {
            return nivelIzquierdo; 
        }

        return nivel(nodo.derecho, valor, nivelActual + 1); 
    }

    int altura() {
        return altura(raiz);
    }

    int peso() {
        return peso(raiz);
    }

    int nivel(int valor) {
        return nivel(raiz, valor, 0);
    }

    void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    Nodo insertarRec(Nodo nodo, int valor) {
        if (nodo == null) {
            nodo = new Nodo(valor);
            return nodo;
        }
        if (valor < nodo.valor) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = insertarRec(nodo.derecho, valor);
        }
        return nodo;
    }

    public static void main(String[] args) {
        Binario arbol = new Binario();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese la cantidad de nodos que desea agregar al árbol:");
        int cantidadNodos = scanner.nextInt();
        
        for (int i = 0; i < cantidadNodos; i++) {
            System.out.println("Ingrese el valor del nodo " + (i + 1) + ":");
            int valor = scanner.nextInt();
            arbol.insertar(valor);
        }

        System.out.println("Altura del árbol: " + arbol.altura());
        System.out.println("Peso del árbol: " + arbol.peso());
        
        System.out.println("Ingrese un valor para conocer su nivel:");
        int valorConsulta = scanner.nextInt();
        System.out.println("Nivel del nodo " + valorConsulta + ": " + arbol.nivel(valorConsulta));
        
        scanner.close();
    }
}
