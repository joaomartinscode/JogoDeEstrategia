package Jogo;

import java.util.Scanner;

public class Feiticeiro extends UnidadeMilitar {
    private int maxFeiticos;

    public Feiticeiro(int index, Scanner sc) {
        super(index, sc);

        System.out.print("Número máximo de feitiços por simulação: ");
        this.maxFeiticos = Integer.parseInt(sc.nextLine());
    }

    @Override
    public void atacar() {
        System.out.println(nome + " #" + index + " lança um feitiço poderoso! [" + maxFeiticos + " disponíveis]");
    }
}
