package Jogo;

import java.util.Scanner;

public class Arqueiro extends UnidadeMilitar {
    private String tipoArma;
    private int bonusAtaque;

    public Arqueiro(int index, Scanner sc) {
        super(index, sc);

        System.out.print("Tipo de armamento (arco/besta): ");
        this.tipoArma = sc.nextLine().toLowerCase();

        switch (tipoArma) {
            case "arco":
                bonusAtaque = 2;
                break;
            case "besta":
                bonusAtaque = 4;
                break;
            default:
                bonusAtaque = 0;
                System.out.println("Tipo inválido. Sem bónus de ataque.");
        }

        this.ataque += bonusAtaque;
    }

    @Override
    public void atacar() {
        System.out.println(nome + " #" + index + " dispara com " + tipoArma + "!");
    }
}
