// |PT-BR|
// Calculadora basica em terminal, com operações de soma, subtração, multiplicação, divisão, potenciação, raiz quadrada e cubica.
// Autor: Higor Stanley aka Devyat009
// Data de criação: 16/05/2024
// Data de atualização: 
//
// EN:
// Simple Calculator in terminal, with basic operations of addition, subtraction, multiplication, division, exponentiation, square root and cubic root.
// Author: Higor Stanley aka Devyat009
// Creation date: 16/05/2024
// Last update date:

import java.util.Scanner;

public class SimpleCalculator {

    public static void main(String[] args) {
        // |PT-BR| Titulo do programa:
        // |EN| Title of the program:
        System.out.println("Calculadora simples em terminal feita por Higor Stanley aka Devyat009");
        // |PT-BR| Cria um objeto Scanner para ler a entrada do usuário
        // |EN| Creates a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        // |PT-BR| Variáveis:
        // |EN| Variables:
        float resultado = 0;
        int operacao;
        // |PT-BR| Inserir o primeiro valor:
        // |EN| Insert the first value:
        System.out.println("Insira o PRIMEIRO valor: ");
        float numero1 = inputUsuario(scanner);
        // |PT-BR| Inserir o segundo valor:
        // |EN| Insert the second value:
        System.out.println("Insira o SEGUNDA valor: ");
        float numero2 = inputUsuario(scanner);
        //
        boolean inputIsValid = false;
        while (!inputIsValid) {
            // |PT-BR| Opções para o usuario selecionar a operação:
            // |EN| Options for the user to select the operation:
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
            operacao = scanner.nextInt();
            // |PT-BR| Operações matemáticas com a resposta do programa:
            // |EN| Mathematical operations with the answer of the program:
            switch (operacao) {
                // |PT-BR| Opção 1: Soma
                // |EN| Option 1: Addition
                case 1:
                    resultado = numero1 + numero2;
                    System.out.println("O resultado da soma é: " + resultado);
                    break;
                // |PT-BR| Opção 2: Subtração
                // |EN| Option 2: Subtraction
                case 2:
                    resultado = numero1 - numero2;
                    System.out.println("o resultado da subtração é: " + resultado);
                    break;
                // |PT-BR| Opção 3: Multiplicação
                // |EN| Option 3: Multiplication
                case 3:
                    resultado = numero1 * numero2;
                    System.out.println("O resultado da multiplicação é: " + resultado);
                    break;
                // |PT-BR| Opção 4: Divisão
                // |EN| Option 4: Division
                case 4:
                    if (numero1 == 0 || numero2 == 0) {
                        System.out.println("Não é possível dividir por 0, portanto o resultado é: 0");
                    } else {
                        resultado = numero1 / numero2;
                        System.out.println("O resultado da divisão é: " + resultado);
                    }
                    break;
                // |PT-BR| Opção 5: Potência
                // |EN| Option 5: Exponentiation
                case 5:
                    resultado = (float) Math.pow(numero1, numero2);
                    System.out.println("O resultado da potência é: " + resultado);
                    break;
                // |PT-BR| Opção 6: Raiz Quadrada
                // |EN| Option 6: Square Root
                case 6:
                    resultado = (float) Math.sqrt(numero1);
                    System.out.println("O resultado da raiz quadrada do primeiro numero é: " + resultado);
                    resultado = (float) Math.sqrt(numero2);
                    System.out.println("O resultado da raiz quadrada do segundo numero é: " + resultado);
                    break;
                // |PT-BR| Opção 7: Raiz Cúbica
                // |EN| Option 7: Cubic Root
                case 7:
                    resultado = (float) Math.cbrt(numero1);
                    System.out.println("O resultado da raiz cúbica do primeiro numero é: " + resultado);
                    resultado = (float) Math.cbrt(numero2);
                    System.out.println("O resultado da raiz cúbica do segundo numero é: " + resultado);
                    break;
                // |PT-BR| Opção 8: Sair
                // |EN| Option 8: Exit
                case 8:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Invalid day! Please enter a number between 1 and 8.");
                } // switch end

        } // While END

        // |PT-BR| Loop para verificar se a opção selecionada é válida:
        // |EN| Loop to check if the selected option is valid:

        // |PT-BR| Fecha o scanner
        // |EN| Close the scanner
        scanner.close();
    } // End of the public main void
    
    // |PT-BR| Função para ler o input do usuário
    // |EN| Function to read user input
    public static float inputUsuario(Scanner scanner){
        while (!scanner.hasNextFloat()) {
            System.out.println("Por favor insira numeros: ");
            scanner.next();
        }
        return scanner.nextFloat();
    } 
}// End of the main class