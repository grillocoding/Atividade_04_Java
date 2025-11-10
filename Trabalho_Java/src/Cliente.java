import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class Cliente
{
        static int i = 0;
        static Scanner sc = new Scanner(System.in);
        static String[] nomesClientes = new String[10];
        static String[] cpfClientes = new String[10];

        static void CadastrarCliente()
        {

            System.out.println("\n==== Menu de Cadastro/Listar Vendedores ====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("0 - Retornar ao Menu");
            System.out.println("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao)
            {
                case 1:
                    System.out.println("Digite o nome do cliente: ");
                    nomesClientes[i] = sc.nextLine();

                    System.out.println("Digite o CPF do cliente: ");
                    cpfClientes[i] = sc.nextLine();

                    System.out.println("Cliente cadastrado com sucesso!");
                    sc.nextLine();
                    i++;
                    break;

                case 2:
                    if (i > 0)
                    {
                        System.out.println("\nClientes cadastrados:");
                        for (int j = 0; j < i; j++)
                        {
                        System.out.println("Nome: " + nomesClientes[j] + " | CPF: " + cpfClientes[j]);
                        }
                        sc.nextLine();
                    }
                    else
                    {
                        System.out.println("Nenhum cliente cadastrado.");
                        sc.nextLine();
                        break;
                    }
                default:
                    break;
            }


        }
    public static void salvarClientes() {
        // ajustar o caminho do arquivo aqui
        try (PrintWriter out = new PrintWriter(new FileWriter("/home/lvs/Documentos/Codes/Java/Trabalho_Java/src/TXTs/clientes.txt"))) {
            for (int j = 0; j < i; j++) {
                // Salva "nome;cpf" em cada linha
                out.println(nomesClientes[j] + ";" + cpfClientes[j]);
            }
            System.out.println("Dados dos clientes salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public static void carregarClientes() {
        // Ajuste o caminho do arquivo para corresponder ao local exato
        File arquivo = new File("/home/lvs/Documentos/Codes/Java/Trabalho_Java/src/TXTs/clientes.txt");
        if (!arquivo.exists()) {
            System.out.println("Arquivo clientes.txt não encontrado. Iniciando sem dados.");
            return;
        }

        try (Scanner fileScanner = new Scanner(arquivo)) {
            i = 0; // Reseta o contador de clientes antes de carregar
            while (fileScanner.hasNextLine()) {
                String linha = fileScanner.nextLine();
                String[] dados = linha.split(";");

                if (dados.length == 2) {
                    nomesClientes[i] = dados[0];
                    cpfClientes[i] = dados[1];
                    i++; // Incrementa o contador
                }
            }
            System.out.println("Dados dos clientes carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }
}
