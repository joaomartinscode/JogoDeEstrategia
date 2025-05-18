package Jogo;

import java.util.Scanner;

public class Guerreiro extends UnidadeMilitar {
    private int blindagem;

    public Guerreiro(int index, Scanner sc) {
        super(index, sc);

        System.out.print("Pontos de blindagem: ");
        this.blindagem = Integer.parseInt(sc.nextLine());
    }

    @Override
    public void defender(int dano) {
        int danoFinal = dano - (defesa + blindagem);
        if (danoFinal > 0) vida -= danoFinal;
        if (vida < 0) vida = 0;
    }

    @Override
    public void atacar() {
        System.out.println(nome + " #" + index + " ataca com espada!");
    }
}
