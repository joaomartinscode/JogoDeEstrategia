package Jogo;

import java.util.Scanner;

public class Feiticeiro extends UnidadeMilitar {
    private int maxFeiticos, feiticosUsados = 0;

    public Feiticeiro(Scanner scanner) {
        super(scanner);
        setNomeAuto("Feiticeiro");

        System.out.print("Número máximo de feitiços: ");
        maxFeiticos = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void atacarUnidade(UnidadeMilitar alvo) {
        int distancia = Math.abs(this.posX - alvo.posX) + Math.abs(this.posY - alvo.posY);
        if (distancia <= this.alcance) {
            System.out.println(this.nome + " atacando " + alvo.nome);
            // Suponhamos que cada feitiço causa pontosAtaque de dano e que tem um limite de feitiços
            if (maxFeiticos > 0) {
                alvo.defenderUnidade(this.pontosAtaque);
                maxFeiticos--;
            } else {
                System.out.println(this.nome + " não tem feitiços restantes para atacar.");
            }
        } else {
            System.out.println(this.nome + " não está no alcance para atacar " + alvo.nome);
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

    public boolean podeLancarFeitico() {
        return feiticosUsados < maxFeiticos;
    }

    public void usarFeitico() {
        feiticosUsados++;
    }

    @Override
    public void printUnidade() {
        System.out.println("Feiticeiro " + nome + " | Vida: " + pontosVida + " | Defesa: " + pontosDefesa + " | Feitiços usados: " + feiticosUsados + "/" + maxFeiticos + " | Posição: (" + posX + "," + posY + ")");
    }
}
