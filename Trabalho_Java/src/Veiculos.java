import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class Veiculos
{
    public static Scanner sc = new Scanner(System.in);
    public static String[] tipoVeiculo = new String[10];
    public static String[] nomesVeiculo = new String[10];
    public static String[] anoVeiculo = new String[10];
    public static Double[] valor = new Double[10];
    public static String[] dataAluguel = new String[10];
    public static int i = 0;

    public static void salvarVeiculos() {

        try (PrintWriter out = new PrintWriter(new FileWriter("/home/lvs/Documentos/Codes/Java/Trabalho_Java/src/TXTs/veiculo.txt"))) {
            for (int j = 0; j < i; j++) {
                String data = (dataAluguel[j] == null) ? "null" : dataAluguel[j];

                out.println(tipoVeiculo[j] + ";" +
                        nomesVeiculo[j] + ";" +
                        anoVeiculo[j] + ";" +
                        valor[j] + ";" +
                        data);
            }
            System.out.println("Dados dos veículos salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar veículos: " + e.getMessage());
        }
    }

    public static void carregarVeiculos() {
        File arquivo = new File("/home/lvs/Documentos/Codes/Java/Trabalho_Java/src/TXTs/veiculo.txt");
        if (!arquivo.exists()) {
            System.out.println("Arquivo veiculos.txt não encontrado. Iniciando sem dados.");
            return;
        }

        try (Scanner fileScanner = new Scanner(arquivo)) {
            i = 0; // Reseta o contador global antes de carregar
            while (fileScanner.hasNextLine()) {
                String linha = fileScanner.nextLine();
                String[] dados = linha.split(";"); // Divide a linha pelo ";"

                if (dados.length == 5) {
                    tipoVeiculo[i] = dados[0];
                    nomesVeiculo[i] = dados[1];
                    anoVeiculo[i] = dados[2];
                    valor[i] = Double.parseDouble(dados[3]);

                    if (dados[4].equals("null")) {
                        dataAluguel[i] = null;
                    } else {
                        dataAluguel[i] = dados[4];
                    }
                    i++; // Incrementa o contador global de veículos
                }
            }
            System.out.println("Dados dos veículos carregados com sucesso!");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Erro ao carregar veículos: ");
        }
    }

    public static void CadastrarVeiculo()
    {
        System.out.println("\n=== Menu de Veículos ===");
        System.out.println("1 - Cadastrar Veículo");
        System.out.println("2 - Listar Veículos");
        System.out.println("0 - Retornar ao Menu");
        System.out.println("\nEscolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch(opcao)
        {
            case 1:
                System.out.println("Digite o tipo do veículo:");
                tipoVeiculo[i] = sc.nextLine();

                System.out.println("Digite o nome do veículo: ");
                nomesVeiculo[i] = sc.nextLine();

                System.out.println("Digite o ano do veículo: ");
                anoVeiculo[i] = sc.nextLine();

                System.out.println("Digite o valor do veículo: ");
                valor[i] = sc.nextDouble();

                System.out.println("Veículo cadastrado com sucesso!");
                sc.nextLine();
                i++;
                break;

            case 2:
                if (i > 0)
                {
                    System.out.println("\n===Veículos Cadastrados===");
                    int veiculosAtivos = 0;

                    for (int j = 0; j < i; j++)
                    {
                        // Só mostra veículos que não foram VENDIDOS (null)
                        if (nomesVeiculo[j] != null)
                        {
                            veiculosAtivos++;
                            String status;

                            if (dataAluguel[j] == null) {
                                status = "[DISPONÍVEL]";
                            } else {
                                status = "[ALUGADO]";
                            }

                            System.out.println("Nome: " + nomesVeiculo[j] +
                                    " | Tipo: " + tipoVeiculo[j] +
                                    " | Ano: " + anoVeiculo[j] +
                                    " | Valor: R$" + valor[j] +
                                    " | Status: " + status);
                        }
                    }

                    if (veiculosAtivos == 0) {
                        System.out.println("Nenhum veículo cadastrado.");
                    }
                }
                else
                {
                    System.out.println("Nenhum veículo cadastrado.");
                }

                System.out.println("\nEnter para continuar...");
                sc.nextLine();
                break;
            default:
                break;
        }
    }
    public static void VenderVeiculo(String[] vendedores, String[] clientes)
    {
        boolean encontrado = false;
        System.out.println("Quem irá realizar está venda?");
        String  vendedor = sc.nextLine();

        for (String nome : vendedores) {
            if (nome != null && nome.equalsIgnoreCase(vendedor)) {
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Vendedor não encontrado! Voltando ao menu...");
            return;
        }

        boolean encontrado2 = false;
        System.out.println("Quem irá realizar a compra?");
        String cliente = sc.nextLine();

        for (String nome : clientes) {
            if (nome != null && nome.equalsIgnoreCase(cliente)) {
                encontrado2 = true;
                break;
            }
        }
        if (!encontrado2) {
            System.out.println("Cliente não encontrado! Voltando ao menu...");
            return;
        }

        if (i > 0) {
            System.out.println("\nVeículos cadastrados:");
            String[] status = new String[10];
            for (int j = 0; j < i; j++) {


                if (dataAluguel[j] == null) {
                    status[j] = "[DISPONÍVEL]";
                } else {
                    status[j] = "[ALUGADO]";
                }
                System.out.println((j + 1) + ". Nome: " + nomesVeiculo[j] + " | Valor: R$" + valor[j] + " | Status: "+ status[j]);
            }
            System.out.println("Qual carro você deseja vender? (Digite o número) ");
            int vender = sc.nextInt() - 1;
            sc.nextLine();

            if (status[vender].equals("[DISPONÍVEL]"))
            {

            for (int j = vender; j < i - 1; j++) {
                nomesVeiculo[j] = nomesVeiculo[j + 1];
                tipoVeiculo[j] = tipoVeiculo[j + 1];
                anoVeiculo[j] = anoVeiculo[j + 1];
                valor[j] = valor[j + 1];
                dataAluguel[j] = dataAluguel[j + 1];
            }

            i--;
            nomesVeiculo[i] = null;
            tipoVeiculo[i] = null;
            anoVeiculo[i] = null;
            valor[i] = null;
            dataAluguel[i] = null;

            System.out.println("Veículo vendido e removido do sistema!");
            }
            else
            {
                System.out.println("Carro escolhido está alugado.");
            }
        }
        else
        {
            System.out.println("Não existem veículos disponíveis! Voltando ao menu...");
            return;
        }

    }
    public static void AlugarVeiculo(String[] vendedores, String[] clientes)
    {
        boolean encontrado = false;
        System.out.println("Qual vendedor será o responsável pelo aluguel?");
        String  vendedor = sc.nextLine();

        for (String nome : vendedores) {
            if (nome != null && nome.equalsIgnoreCase(vendedor)) {
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Vendedor não encontrado! Voltando ao menu...");
            return;
        }

        boolean encontrado2 = false;
        System.out.println("Qual cliente irá alugar?");
        String cliente = sc.nextLine();

        for (String nome : clientes) {
            if (nome != null && nome.equalsIgnoreCase(cliente)) {
                encontrado2 = true;
                break;
            }
        }
        if (!encontrado2) {
            System.out.println("Cliente não encontrado! Voltando ao menu...");
            return;
        }

        System.out.println("\n===Veículos disponíveis para aluguel===");

        int disponiveis = 0;
        for (int j = 0; j < i; j++)
        {
            if (nomesVeiculo[j] != null && dataAluguel[j] == null)
            {
                System.out.println((j + 1) + ". Nome: " + nomesVeiculo[j] + " | Valor: R$" + valor[j]);
                disponiveis++;
            }
        }

        if (disponiveis == 0) {
            System.out.println("Nenhum veículo disponível para aluguel no momento.");
            sc.nextLine(); // Pausa
            return;
        }

        System.out.println("==============================================");
        System.out.println("Qual carro você deseja alugar? (Digite o número)");
        int indiceAlugar = sc.nextInt() - 1;
        sc.nextLine();

        if (indiceAlugar < 0 || indiceAlugar >= i || nomesVeiculo[indiceAlugar] == null || dataAluguel[indiceAlugar] != null)
        {
            System.out.println("Opção inválida ou veículo indisponível! Voltando ao menu...");
            return;
        }

        dataAluguel[indiceAlugar] = "Alugado";

        System.out.println("Veículo " + nomesVeiculo[indiceAlugar] + " alugado com sucesso!");
        System.out.println("Pressione Enter para continuar...");
        sc.nextLine();
    }


}
