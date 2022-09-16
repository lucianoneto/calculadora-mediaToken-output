import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String nomeArquivo;
        System.out.println("Digite o nome do arquivo: ");
        nomeArquivo = scanner.nextLine();
        System.out.println("Digite a quantidade de linhas de cada bloco: ");
        int tamanhoBloco = scanner.nextInt();
        int contador = 0;

        double iExperimental1 = 0;
        double iExperimental2 = 0;
        double resultadoFinal;

        List<Double> resultados = new ArrayList<>();

        Scanner scannerNew = new Scanner(new FileReader(nomeArquivo + ".txt"));
        while (scannerNew.hasNextLine()) {
            String linhaAtual = scannerNew.nextLine();
            linhaAtual = linhaAtual.trim().replaceAll("\\s+", "¥");

            if (contador != 0) {
                String[] linha = linhaAtual.split("¥");

                iExperimental1 += Double.parseDouble(linha[3]);
                iExperimental2 += Double.parseDouble(linha[4]);

                if (contador == tamanhoBloco) {
                    contador = 0;
                    resultadoFinal = (Math.pow((iExperimental1 - iExperimental2), 2) / (Math.pow(iExperimental1, 2) + Math.pow(iExperimental2, 2)));
                    resultados.add(resultadoFinal);
                    iExperimental1 = 0;
                }
            }
            contador++;
        }

        for (int i = 1; i < resultados.size(); i++) {
            System.out.println("BLOCO " + i);
            System.out.println("Somatorio : " + resultados.get(i));
        }
    }
}
