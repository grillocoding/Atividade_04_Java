import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Vendedor
{
    static int i = 0;
    static Scanner sc = new Scanner(System.in);
    static String[] nomesVendedor = new String[10];
    static String[] CPF = new String[10];

    static void CadastrarVendedor()
    {

        System.out.println("\n==== Menu de Cadastro/Listar Vendedores ====");
        System.out.println("1 - Cadastrar Vendedor");
        System.out.println("2 - Listar Vendedores");
        System.out.println("0 - Retornar ao Menu");
        System.out.println("\nEscolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch(opcao)
        {
            case 1:
                System.out.println("Digite o nome do vendedor: ");
                nomesVendedor[i] = sc.nextLine();

                System.out.println("Digite o CPF do vendedor: ");
                CPF[i] = sc.nextLine();
                i++;
                break;

            case 2:
                if (i > 0)
                {
                    System.out.println("\nVendedores cadastrados:");
                    for (int j = 0; j < i; j++)
                    {
                        System.out.println("Nome: " + nomesVendedor[j] + " | CPF: " + CPF[j]);
                    }
                    sc.nextLine();
                    break;
                }
                else
                {
                    System.out.println("Nenhum vendedor cadastrado.");
                    sc.nextLine();
                    break;
                }
            default:
                break;
        }

    }
    public static void salvarVendedores() {
        // ajustar o caminho do arquivo aqui
        try (PrintWriter out = new PrintWriter(new FileWriter("/home/lvs/Documentos/Codes/Java/Trabalho_Java/src/TXTs/vendedores.txt"))) {
            for (int j = 0; j < i; j++)
            {
                out.println(nomesVendedor[j] + ";" + CPF[j]);
            }
            System.out.println("Dados dos vendedores salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar vendedores: " + e.getMessage());
        }
    }

    public static void carregarVendedores() {
        // Ajuste o caminho do arquivo para corresponder ao local exato
        File arquivo = new File("/home/lvs/Documentos/Codes/Java/Trabalho_Java/src/TXTs/vendedores.txt");
        if (!arquivo.exists()) {
            System.out.println("Arquivo vendedores.txt não encontrado. Iniciando sem dados.");
            return;
        }

        try (Scanner fileScanner = new Scanner(arquivo)) {
            i = 0; // Reseta o contador de vendedores antes de carregar
            while (fileScanner.hasNextLine()) {
                String linha = fileScanner.nextLine();
                String[] dados = linha.split(";");

                if (dados.length == 2) {
                    nomesVendedor[i] = dados[0];
                    CPF[i] = dados[1];
                    i++; //Incrementa o contador
                }
            }
            System.out.println("Dados dos vendedores carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao carregar vendedores: ");
        }
    }
}

