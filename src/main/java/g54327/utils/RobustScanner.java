package g54327.utils;

import g54327.humbug.model.Direction;

import java.util.Scanner;
import java.util.function.Consumer;

public final class RobustScanner {
    private static Scanner scanner = new Scanner(System.in);

    public static int askInt(String preMessage, String errorMessage, Consumer<String> preConsumer, Consumer<String> errorConsumer) {
        preConsumer.accept(preMessage);
        System.out.print("\t");
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            errorConsumer.accept(errorMessage);
            System.out.print("\t");
        }

        return scanner.nextInt();
    }

    public static Direction askDirection(String preMessage, String errorMessage, Consumer<String> preConsumer, Consumer<String> errorConsumer) {
        preConsumer.accept(preMessage);
        System.out.print("\t");
        Direction direction;
        do {
            while (!scanner.hasNext()) {
                scanner.nextLine();
                errorConsumer.accept(errorMessage);
                System.out.print("\t");
            }
            direction = Direction.valueOfByShortName(scanner.next());
            if (direction == null) {
                errorConsumer.accept(errorMessage);
            }
        } while (direction == null);

        return direction;
    }
}
