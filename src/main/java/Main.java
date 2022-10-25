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
        double resultadoFinal1 = 0d;
        double resultadoFinal2 = 0d;
        double resultadoFinal3 = 0d;

        List<Double> resultados = new ArrayList<>();
        List<Double> angulos = new ArrayList<>();
        List<Double> coluna4 = new ArrayList<>();
        List<Double> coluna5 = new ArrayList<>();
        List<Double> novaColuna4 = new ArrayList<>();
        List<Double> novaColuna5 = new ArrayList<>();

        Scanner scannerNew = new Scanner(new FileReader(nomeArquivo));
        while (scannerNew.hasNextLine()) {
            String linhaAtual = scannerNew.nextLine();
            linhaAtual = linhaAtual.trim().replaceAll("\\s+", "¥");

            String[] linha = linhaAtual.split("¥");

            if (contador == 0)
                angulos.add(Double.parseDouble(linha[3]));

            if (contador > 0) {
                if (linha.length == 5) {
                    //verificar maior valor
                    coluna4.add(Double.parseDouble(linha[3]));
                    coluna5.add(Double.parseDouble(linha[4]));
                }

                if (linha.length == 7 || (Objects.equals(linha[0], "fitted"))) {
                    Double maxValueColuna4 = 0d;
                    Double maxValueColuna5 = 0d;

                    for (Double double1 : coluna4) {
                        if(double1 < 0){
                            double1 = double1 * -1;
                        }
                        if (double1 > maxValueColuna4)
                            maxValueColuna4 = double1;
                    }
                    for (Double double1 : coluna5) {
                        if(double1 < 0){
                            double1 = double1 * -1;
                        }
                        if (double1 > maxValueColuna5)
                            maxValueColuna5 = double1;
                    }
                    for (Double value : coluna4) {
                        Double auxiliar = value/maxValueColuna4;
                        novaColuna4.add(auxiliar);
                    }
                    for (Double aDouble : coluna5) {
                        Double auxiliar = aDouble/maxValueColuna5;
                        novaColuna5.add(auxiliar);
                    }

                    for(int i = 0; i<novaColuna4.size(); i++) {
                        resultadoFinal1 += (novaColuna4.get(i)-novaColuna5.get(i))*(novaColuna4.get(i)-novaColuna5.get(i));
                        resultadoFinal2 += ((novaColuna4.get(i)*novaColuna4.get(i))+(novaColuna5.get(i)*novaColuna5.get(i)));
                    }

                    resultadoFinal3= resultadoFinal1/resultadoFinal2;

                    resultados.add(resultadoFinal3);

                    coluna4.clear();
                    coluna5.clear();
                    novaColuna4.clear();
                    novaColuna5.clear();
                    contador = 0;

                    if (Objects.equals(linha[0], "fitted"))
                        break;

                    resultadoFinal1 = 0;
                    resultadoFinal2 = 0;
                    angulos.add(Double.parseDouble(linha[3]));
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