import java.util.Scanner;

public class Main
{

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        Veiculos.carregarVeiculos();
        Vendedor.carregarVendedores();
        Cliente.carregarClientes();
        boolean resultado = Login.login();

        if (resultado)
        {
            int opcao;

            do {
                System.out.println("\n===== MENU PRINCIPAL =====");
                System.out.println("1 - Cadastrar/Listar Vendedor");
                System.out.println("2 - Cadastrar/Listar Cliente");
                System.out.println("3 - Cadastrar/Listar Veículos");
                System.out.println("4 - Vender Veículos");
                System.out.println("5 - Reservar Veículos");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();

                switch (opcao) {
                    case 1:
                        Vendedor.CadastrarVendedor();
                        break;
                    case 2:
                        Cliente.CadastrarCliente();
                        break;
                    case 3:
                        Veiculos.CadastrarVeiculo();
                        break;
                    case 4:
                        Veiculos.VenderVeiculo(Vendedor.nomesVendedor, Cliente.nomesClientes);
                        break;
                    case 5:
                        Veiculos.AlugarVeiculo(Vendedor.nomesVendedor, Cliente.nomesClientes);
                        break;
                    case 0:
                        Veiculos.salvarVeiculos();
                        Vendedor.salvarVendedores();
                        Cliente.salvarClientes();
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Digite uma opção válida.");

                }
            } while (opcao != 0);

        }
    }
}