import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String nomeArquivo = args[0];
        int contador = -26;
        double iExperimental1 = 0;
        double iExperimental2 = 0;
        double resultadoFinal;

        List<Double> resultados = new ArrayList<>();
        List<Double> angulos = new ArrayList<>();

        Scanner scannerNew = new Scanner(new FileReader(nomeArquivo));
        while (scannerNew.hasNextLine()) {
            String linhaAtual = scannerNew.nextLine();
            linhaAtual = linhaAtual.trim().replaceAll("\\s+", "¥");

            String[] linha = linhaAtual.split("¥");

            if (contador == 0)
                angulos.add(Double.parseDouble(linha[3]));

            if (contador > 0) {
                if (linha.length == 5) {
                    iExperimental1 += Double.parseDouble(linha[3]);
                    iExperimental2 += Double.parseDouble(linha[4]);
                }
                if (linha.length == 7) {
                    contador = 0;
                    resultadoFinal = (Math.pow((iExperimental1 - iExperimental2), 2)) / ((Math.pow(iExperimental1, 2)) + (Math.pow(iExperimental2, 2)));
                    resultados.add(resultadoFinal);
                    iExperimental1 = 0;
                    iExperimental2 = 0;
                    angulos.add(Double.parseDouble(linha[3]));
                }
                if (Objects.equals(linha[0], "fitted")) {
                    resultadoFinal = (Math.pow((iExperimental1 - iExperimental2), 2)) / ((Math.pow(iExperimental1, 2)) + (Math.pow(iExperimental2, 2)));
                    resultados.add(resultadoFinal);
                    break;
                }
            }
            contador++;
        }
        for (int i = 1; i <= resultados.size(); i++) {
            System.out.println("BLOCO " + i);
            System.out.println("Ângulo: " + angulos.get(i - 1));
            System.out.println("R : " + resultados.get(i - 1));
            System.out.println("-----------------------------------------");
        }
    }
}
