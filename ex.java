import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

//testtesttest

class Main {
    public static void main(String[] args) {
        Deque<Integer> elevator = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);
        Integer firstFloor;

        while (true) {
            System.out.println("На какой этаж подать лифт? Введите номер или нажмите 0 для выхода.");
            firstFloor = Integer.valueOf(scanner.nextLine());

            if (firstFloor > 0 && firstFloor < 26) {
                elevator.add(firstFloor);
                break;
            } else if (firstFloor == 0) {
                System.out.println("Не хотите - не надо, лифт не приедет");
                System.exit(0);
            } else {
                System.out.println("Вы ввели некорректное значение, повторите.");
                continue;
            }
        }

        final int waitDoorsInSeconds = 10;
        final int waitMoveInSeconds = 5;
        int totalSeconds = 0;

        while (true) {
            System.out.println("Ожидаю ввода нового этажа (для выхода нажмите 0)");
            Integer userEnter = Integer.valueOf(scanner.nextLine());

            if (userEnter > 0 && userEnter < 26) {

                //Лифт же должен ехать откуда-то, он не может начать движение из ниоткуда
                int previousFloor = firstFloor;
                int currentFloor = firstFloor;
                int localWaitDoorsInSeconds = waitDoorsInSeconds;

                if (!elevator.isEmpty()) {
                    previousFloor = elevator.peekLast();
                }

                elevator.offer(userEnter);

                if (!elevator.isEmpty()) {
                    currentFloor = elevator.peekLast();
                }

                if (currentFloor == previousFloor) {
                    localWaitDoorsInSeconds = 0;
                }

                totalSeconds += Math.abs((currentFloor - previousFloor) * waitMoveInSeconds);
                totalSeconds += localWaitDoorsInSeconds;

                //System.out.println(previousFloor + " " + currentFloor + " " + localWaitDoorsInSeconds
                //+ " " + totalSeconds); //использовал для проверки

            } else if (userEnter == 0) {
                System.out.println("Лифт кончился, вы проехали следующие этажи: ");
                while (!elevator.isEmpty()) {
                    System.out.print("этаж_" + elevator.poll() + " ");
                }
                System.out.println("\nВремя, затраченное лифтом на маршрут: " + totalSeconds + " с.");
                break;

            } else {
                System.out.println("Вы ввели некорректное значение, повторите: ");
            }
        }
    }
}
