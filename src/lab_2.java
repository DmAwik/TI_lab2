import CIPHER.ElGamal;

import java.util.Scanner;


public class lab_2 {

    static String Choice() {
        Scanner in = new Scanner(System.in);
        String s;
        s = in.nextLine();
        return s;
    }

    static void GenerateMenu() {
        Scanner in = new Scanner(System.in);
        String originalText;
        ElGamal elGamal = new ElGamal();
        elGamal.generateP();
        elGamal.generateG();
        elGamal.generateX();
        elGamal.calculateY();
        System.out.println("Открытый ключ = (" + elGamal.getP() + ", " + elGamal.getG() + ", " + elGamal.getY() + ")");
        System.out.println("Закрытый ключ = (" + elGamal.getX() + ")");
        System.out.print("Введите сообщение, которое хотите зашифровать: ");
        originalText = in.nextLine();
        elGamal.coding(originalText);
    }

    static void SetMenu() {
        Scanner in = new Scanner(System.in);
        ElGamal elGamal = new ElGamal();
        System.out.print("Введите p (простое число): ");
        elGamal.setP(in.nextInt());
        System.out.print("Введите g (первообразный корень): ");
        elGamal.setG(in.nextInt());
        System.out.print("Введите x (секретный ключ): ");
        elGamal.setX(in.nextInt());
        elGamal.calculateY();
        System.out.println("Открытый ключ = (" + elGamal.getP() + ", " + elGamal.getG() + ", " + elGamal.getY() + ")");
        System.out.println("Закрытый ключ = (" + elGamal.getX() + ")");
        System.out.print("Введите m (сообщение в виде цифры): ");
        elGamal.setM(in.nextInt());
        System.out.print("Введите число k (для генерации шифротекста): ");
        elGamal.setK(in.nextInt());
        elGamal.calculateA();
        elGamal.calculateB();
        System.out.print("(" + elGamal.getA() + ", " + elGamal.getB() + ") ");
        System.out.println();
    }

    static void DecodingMenu() {
        Scanner in = new Scanner(System.in);
        ElGamal elGamal = new ElGamal();
        System.out.print("Введите p (простое число): ");
        elGamal.setP(in.nextInt());
        System.out.print("Введите x (секретный ключ): ");
        elGamal.setX(in.nextInt());
        int countOfCouple;
        System.out.print("Введите количество пар шифротекста: ");
        countOfCouple = in.nextInt();
        StringBuilder originalText = new StringBuilder();
        for (int i = 0; i < countOfCouple; i++) {
            System.out.print("Введите А: ");
            elGamal.setA(in.nextInt());
            System.out.print("Введите В: ");
            elGamal.setB(in.nextInt());
            elGamal.decoding();
            originalText.append((char) (elGamal.getM()));
        }
        System.out.println("Исходный текст: " + originalText.toString());
    }

    static void MainMenu() {
        String Answer = "0";
        String Ask = "3";
        while (!Answer.equals(Ask)) {
            System.out.println("1. Зашифровать сообщение");
            System.out.println("2. Расшифровать сообщение");
            System.out.println("3. Выход");
            switch (Answer = Choice()) {
                case "1": {
                    System.out.println("1. Сгенерировать данные");
                    System.out.println("2. Ввести данные");
                    switch (Answer = Choice()) {
                        case "1": {
                            GenerateMenu();
                            break;
                        }
                        case "2": {
                            SetMenu();
                            break;
                        }
                    }
                    break;
                }
                case "2": {
                    DecodingMenu();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        MainMenu();
    }
}
