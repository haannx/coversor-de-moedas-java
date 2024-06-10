package empresa;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<String> cofrinho = new ArrayList<>();
        
        cofrinho.add("Real: 10.0");
        cofrinho.add("Dólar: 5.0");
        cofrinho.add("Euro: 8.0");
        
        // Menu principal
        
        while(true) {
            System.out.println("----------------------------------");
            System.out.println("-- Escolha uma opção de ação: --");
            System.out.println("1 - Adicionar Moeda");
            System.out.println("2 - Remover Moeda");
            System.out.println("3 - Listar Moedas");
            System.out.println("4 - Total convertido para Real");
            System.out.println("0 - Encerrar");
            System.out.println("----------------------------------");

            System.out.print("Digite o número da opção desejada: ");
            int opcao = teclado.nextInt();
            
            switch (opcao) {
                case 1:
                    adicionarMoeda(teclado, cofrinho);
                    break;
                case 2:
                    removerMoeda(teclado, cofrinho);
                    break;
                case 3:
                    listarMoedas(cofrinho);
                    break;
                case 4:
                    totalConvertidoParaReal(cofrinho);
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    teclado.close(); // Fechamento do Scanner aqui
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    
    // Inicio dos cases para adicionar moedas ao array
    
    private static void adicionarMoeda(Scanner teclado, ArrayList<String> cofrinho) {
        System.out.println("1 - Real");
        System.out.println("2 - Dólar");
        System.out.println("3 - Euro");
        System.out.println("4 - Voltar");

        Scanner addMoeda = new Scanner(System.in);
        int escolhaMoeda = addMoeda.nextInt();

        switch (escolhaMoeda) {
            case 1:
                System.out.println("Qual valor em Real você quer adicionar? ");
                double valorReal = addMoeda.nextDouble();
                cofrinho.add("Real "+valorReal);
                System.out.println("Valor Adicionado com sucesso!");
                break;
            case 2:
                System.out.println("Qual valor em Dólar você quer adicionar? ");
                double valorDolar = addMoeda.nextDouble();
                cofrinho.add("Dólar "+valorDolar);
                System.out.println("Valor Adicionado com sucesso!");
                break;
            case 3:
                System.out.println("Qual valor em Euro você quer adicionar? ");
                double valorEuro = addMoeda.nextDouble();
                cofrinho.add("Euro "+valorEuro);
                System.out.println("Valor Adicionado com sucesso!");
                break;
            case 4:
                return; // Voltar ao menu principal
            default:
                System.out.println("Opção inválida.");
        }
    }
    
    // Inicio dos cases para remoção de moedas
    
    private static void removerMoeda(Scanner teclado, ArrayList<String> cofrinho) {
        System.out.println("Qual tipo de Moeda deseja retirar? ");
        System.out.println("1 - Real");
        System.out.println("2 - Dólar");
        System.out.println("3 - Euro");
        System.out.print("Digite o número da opção desejada: ");
        int tipoMoeda = teclado.nextInt();

        String tipoMoedaStr = "";
        switch (tipoMoeda) {
            case 1:
                tipoMoedaStr = "Real";
                break;
            case 2:
                tipoMoedaStr = "Dólar";
                break;
            case 3:
                tipoMoedaStr = "Euro";
                break;
            default:
                System.out.println("Opção inválida. Retornando ao menu principal.");
                return;
        }

        // Escolher moeda a ser removida
        
        System.out.println("Digite o valor da moeda a ser removida: ");
        double valorRemover = teclado.nextDouble();

        boolean moedaRemovida = false;
        for (int i = 0; i < cofrinho.size(); i++) {
            String moeda = cofrinho.get(i);
            if (moeda.startsWith(tipoMoedaStr)) {
                String[] partes = moeda.split(" ");
                double valorAtual = Double.parseDouble(partes[1]);
                if (valorAtual == valorRemover) {
                    cofrinho.remove(i);
                    System.out.println("Moeda removida com sucesso: " + moeda);
                    moedaRemovida = true;
                    break;
                }
            }
        }

        if (!moedaRemovida) {
            System.out.println("Não foi possível remover a moeda. Verifique o tipo e o valor.");
        }
    }
    
    // Listagem das moedas
    private static void listarMoedas(ArrayList<String> cofrinho) {
        System.out.println("Moedas no cofrinho:");
        for (String moeda : cofrinho) {
            System.out.println("- " + moeda);
        }
    }

    // Conversão das e soma total das moedas em real
    private static void totalConvertidoParaReal(ArrayList<String> cofrinho) {
        double totalReal = 0.0;
        double totalDolar = 0.0;
        double totalEuro = 0.0;

        // Itera sobre as moedas no cofrinho
        for (String moeda : cofrinho) {
            String[] partes = moeda.split(" ");
            String tipo = partes[0];
            double valor = Double.parseDouble(partes[1]);

            // Verifica o tipo de moeda e soma ao total correspondente
            switch (tipo) {
                case "Real":
                    totalReal += valor;
                    break;
                case "Dólar":
                    totalDolar += valor;
                    break;
                case "Euro":
                    totalEuro += valor;
                    break;
                default:
                    System.out.println("Tipo de moeda inválido: " + tipo);
            }
        }

        // Soma os valores adicionais aos valores iniciais de Dólar e Euro
        totalDolar += 5.0; // Valor inicial de 5 dólares
        totalEuro += 8.0; // Valor inicial de 8 euros
        totalReal+= 10.0; // Valor inicial de 10 reais

        // Converte Dólar e Euro para Real e soma ao total Real
        double totalDolarEmReal = totalDolar * 5.02; // 1 dólar equivale a 5,02 reais
        double totalEuroEmReal = totalEuro * 5.62; // 1 euro equivale a 5,62 reais
        totalReal += totalDolarEmReal + totalEuroEmReal;

        // Formata o total para exibir apenas 2 casas decimais após a vírgula
        String totalFormatado = String.format("%.2f", totalReal);
        System.out.println("Total convertido para Real: " + totalFormatado + " reais");
    }
}



