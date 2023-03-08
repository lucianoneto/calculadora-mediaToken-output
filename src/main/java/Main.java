import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final int TOKEN_T5 = 2;

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Error: 2 arguments are needed, file name and number of starting tokens.");
            return;
        }

        String fileName = args[0];
        int initialTokenCount;
        try {
            initialTokenCount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number of starting tokens.");
            return;
        }

        int outputTokenCount = 0;
        int valuePi2 = 0;
        int valuePe2 = 0;

        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();

                if (currentLine.isBlank())
                    continue;

                String[] line = currentLine.trim().split("\\s+");

                if (line.length <= TOKEN_T5 || !line[TOKEN_T5].equals("t5"))
                    continue;

                line = scanner.nextLine().trim().split("\\s");
                valuePi2 = Integer.parseInt(line[3]);
                line = scanner.nextLine().trim().split("\\s");
                valuePe2 = Integer.parseInt(line[3]);
                if (valuePi2 != valuePe2) {
                    outputTokenCount++;
                }
            }
        }

        int loss = initialTokenCount - outputTokenCount;
        System.out.println("Loss: " + loss);
        System.out.println("Gain: " + outputTokenCount);
    }
}
