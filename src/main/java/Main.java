import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a quantidade de linhas de cada bloco: \n");
        int linhas = scanner.nextInt();
        int cont = 0;
        Double somatorio1 = Double.valueOf(0);
        Double somatorio2 = Double.valueOf(0);
        Double resultado1;
        List<Double> coluna4 = new ArrayList<>();

        Scanner scannerNew = new Scanner(new FileReader("arquivo_yosef2.txt"));
        while (scannerNew.hasNextLine()) {
            String line = scannerNew.nextLine();
            line = line.trim().replaceAll("\\s+", "¥");

            if (cont != 0) {
                String[] linha = line.split("¥");

                somatorio1 += Double.parseDouble(linha[3]);
                somatorio2 += Double.parseDouble(linha[4]);

                if (cont == linhas) {
                    cont = 0;
                    resultado1 = (Math.pow((somatorio1 - somatorio2), 2) / ((Math.pow(somatorio1, 2) + Math.pow(somatorio2,2))));
                    coluna4.add(resultado1);
                    somatorio1 = Double.valueOf(0);
                }
            }
            cont++;
        }
        for(int i = 0; i < coluna4.size(); i++) {
            System.out.println("BLOCO " + (i+1) );
            System.out.println("Somatorio : " + coluna4.get(i));
        }
    }
}
