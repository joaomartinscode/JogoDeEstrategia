package Jogo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Exercito {
    protected String nome;
    protected int custoAtual;
    private int nGuerreiros = 0, nArqueiros = 0, nFeiticeiros = 0;
    private final ArrayList<UnidadeMilitar> exercito = new ArrayList<>();

    public Exercito(Scanner keyboard, int custoTotal){
        System.out.print("Digite o nome do Exercito: ");
        nome = keyboard.nextLine();
        custoAtual = custoTotal;
    }

    public void adicionarUnidades(String tipo, int quantidade, Scanner sc) {
        for (int i = 0; i < quantidade; i++) {
            int index = encontrarMenorIndexGlobal();
            UnidadeMilitar nova;

            switch (tipo.toLowerCase()) {
                case "guerreiro":
                    nova = new Guerreiro(index, sc);
                    break;
                case "arqueiro":
                    nova = new Arqueiro(index, sc);
                    break;
                case "feiticeiro":
                    nova = new Feiticeiro(index, sc);
                    break;
                default:
                    System.out.println("Tipo inválido: " + tipo);
                    return;
            }

            if (custoAtual >= nova.getCusto()) {
                exercito.add(nova);
                custoAtual -= nova.getCusto();
                System.out.println("Unidade adicionada: " + tipo + " com índice " + index);
            } else {
                System.out.println("Custo insuficiente para adicionar a unidade.");
            }
        }
    }

    private int encontrarMenorIndexGlobal() {
        ArrayList<Integer> usados = new ArrayList<>();

        for (UnidadeMilitar u : exercito) {
            usados.add(u.getIndex());
        }

        Collections.sort(usados);

        int esperado = 0;
        for (int usado : usados) {
            if (usado > esperado) break;
            if (usado == esperado) esperado++;
        }

        return esperado;
    }


    public void removerUnidade(int index) {
        Iterator<UnidadeMilitar> it = exercito.iterator();
        while (it.hasNext()) {
            UnidadeMilitar u = it.next();
            if (u.getIndex() == index) {
                it.remove();
                System.out.println("Unidade com índice " + index + " removida.");
                return;
            }
        }
        System.out.println("Nenhuma unidade encontrada com o índice " + index);
    }

    public void moverExercito(){
        for(UnidadeMilitar u : exercito){
            u.mover();
        }
    }

    public void atacarExercito(){
        for(UnidadeMilitar u : exercito){
            u.atacar();
        }
    }

    public void defenderExercito(int dano){
        for (UnidadeMilitar u : exercito) {
            u.defender(dano);
        }
    }

    public boolean estadoExercito(){
        System.out.println("Exercito: " + nome);
        for(UnidadeMilitar u : exercito){
           if (u.estadoUnidade()){
               return true;
           }
        }
        return false;
    }

    public ArrayList<UnidadeMilitar> getUnidades() {
        return exercito;
    }

    public void printExercito(){
        for (UnidadeMilitar u : exercito) {
            u.printUnidade();
        }
    }
}
