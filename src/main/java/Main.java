import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Variaveis:
        float resultado = 0;
        // Inserir o primeiro valor:
        System.out.println("Insira o primeiro valor: ");
        while (!scanner.hasNextFloat()) {
            System.out.println("Por favor insira numeros: ");
            scanner.next();
        }
        float numero_1 = scanner.nextFloat();
        // Inserir o segundo valor:
        System.out.println("Insira o segundo valor: ");
        while (!scanner.hasNextFloat()) {
            System.out.println("Por favor insira numeros: ");
            scanner.next();
        }
        float numero_2 = scanner.nextFloat();
        
        // Opções para o usuario selecionar a operação:
        System.out.println("Selecione a operação: ");
        System.out.println("1 - Soma");
        System.out.println("2 - Subtração");
        System.out.println("3 - Multiplicação");
        System.out.println("4 - Divisão");
        System.out.println("5 - Potência");
        System.out.println("6 - Raiz Quadrada");
        System.out.println("7 - Raiz Cúbica");
        System.out.println("8 - Sair");
        System.out.print("Operação: ");
        int operacao = scanner.nextInt();
        // Inserir o operador:
        switch (operacao) {
            // Opção 1: Soma
            case 1:
                resultado = numero_1 + numero_2;
                System.out.println("O resultado da soma é: " + resultado);
                break;
            // Opção 2: Subtração
            case 2:
                resultado = numero_1 - numero_2;
                System.out.println("o resultado da subtração é: " + resultado);
                break;
            // Opção 3: Multiplicação
            case 3:
                resultado = numero_1 * numero_2;
                System.out.println("O resultado da multiplicação é: " + resultado);
                break;
            // Opção 4: Divisão
            case 4:
                if (numero_1 == 0 || numero_2 == 0) {
                    System.out.println("Não é possível dividir por 0, portanto o resultado é: 0");                
                } else {
                    resultado = numero_1 / numero_2;
                    System.out.println("O resultado da divisão é: " + resultado);
                }
                break;
            // Opção 5: Potência
            case 5:
                resultado = (float) Math.pow(numero_1, numero_2);
                System.out.println("O resultado da potência é: " + resultado);
                break;
            // Opção 6: Raiz Quadrada
            case 6:
                resultado = (float) Math.sqrt(numero_1);
                System.out.println("O resultado da raiz quadrada do primeiro numero é: " + resultado);
                resultado = (float) Math.sqrt(numero_2);
                System.out.println("O resultado da raiz quadrada do segundo numero é: " + resultado);
                break;
            // Opção 7: Raiz Cúbica
            case 7:
                resultado = (float) Math.cbrt(numero_1);
                System.out.println("O resultado da raiz cúbica do primeiro numero é: " + resultado);
                resultado = (float) Math.cbrt(numero_2);
                System.out.println("O resultado da raiz cúbica do segundo numero é: " + resultado);
                break;
            // Opção 8: Sair
            case 8:
                System.out.println("Saindo...");
                break;
        }
        // Fecha o scanner
        scanner.close();
    }
}