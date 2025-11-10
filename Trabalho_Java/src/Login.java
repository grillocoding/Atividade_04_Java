import java.util.Scanner;

public class Login
{

    public static boolean login()
    {   boolean verificador = false;
        do
        {
            String usuarioLogin = "admin";
            String senhaLogin = "123";
            Scanner scan = new Scanner(System.in);

            System.out.println("Digite o usúario: (admin) ");
            String usuarioIns = scan.nextLine();

            System.out.println("Digite a senha: (123)");
            String senhaIns = scan.nextLine();

            if (usuarioIns.equals(usuarioLogin) && senhaIns.equals(senhaLogin)) {
                System.out.println("Login Realizado com Sucesso!");
                verificador = true;
            } else {
                System.out.println("Credenciais inválidas");
            }
        }while(!verificador);
        return true;
    }

}
