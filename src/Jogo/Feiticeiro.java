package Jogo;

import Utils.InputValidation;

import java.util.Scanner;

public class Feiticeiro extends UnidadeMilitar {
    protected int maxFeiticos, feiticosUsados = 0;

    public Feiticeiro(Scanner scanner) {
        super(scanner);
        setNomeAuto("Feiticeiro");

        maxFeiticos = InputValidation.validateIntGT0(scanner, "Número máximo de feitiços: ");
    }

    @Override
    public void atacarUnidade(UnidadeMilitar alvo) {
        if (maxFeiticos > 0) {
            super.atacarUnidade(alvo);  // reutiliza a lógica de distância e ataque
            maxFeiticos--;
        } else {
            System.out.println(nome + " não tem feitiços restantes para atacar.");
        }
    }

    @Override
    public void defenderUnidade(int dano) {
        // Apenas pontos de defesa e vida
        if (pontosDefesa > 0) {
            int defesaUsada = Math.min(pontosDefesa, dano);
            pontosDefesa -= defesaUsada;
            dano -= defesaUsada;
        }
        if (dano > 0) {
            pontosVida -= dano;
        }
    }

    @Override
    public void printUnidade() {
        System.out.println(nome + " | Vida: " + pontosVida + " | Defesa: " + pontosDefesa +
                " | Feitiços usados: " + feiticosUsados + "/" + maxFeiticos +
                " | Posição: (" + posX + "," + posY + ")");
    }
}
