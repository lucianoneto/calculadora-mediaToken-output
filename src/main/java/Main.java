import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a quantidade de linhas de cada bloco: \n");

        int tamanhoBloco = scanner.nextInt();
        int cont = 0;

        double iExperimental1 = 0;
        double iExperimental2 = 0;
        double resultadoFinal;

        List<Double> resultados = new ArrayList<>();

        Scanner scannerNew = new Scanner(new FileReader("arquivo_yosef2.txt"));
        while (scannerNew.hasNextLine()) {
            String linhaAtual = scannerNew.nextLine();
            linhaAtual = linhaAtual.trim().replaceAll("\\s+", "¥");

            if (cont != 0) {
                String[] linha = linhaAtual.split("¥");

                iExperimental1 += Double.parseDouble(linha[3]);
                iExperimental2 += Double.parseDouble(linha[4]);

                if (cont == tamanhoBloco) {
                    cont = 0;
                    resultadoFinal = (Math.pow((iExperimental1 - iExperimental2), 2) / ((Math.pow(iExperimental1, 2) + Math.pow(iExperimental2, 2))));
                    resultados.add(resultadoFinal);
                    iExperimental1 = 0;
                }
            }
            cont++;
        }

        for (int i = 1; i < resultados.size(); i++) {
            System.out.println("BLOCO " + i);
            System.out.println("Somatorio : " + resultados.get(i));
        }
    }
}
