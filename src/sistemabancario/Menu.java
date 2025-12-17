package sistemabancario;

public class Menu {

    public static void menuGeral() {
        System.out.println("Bem vindo ao Sistema Bancario!\n" +
                "1 - Secao Conta\n" +
                "2 - Secao Correntista\n" +
                "3 - Sair");
    }

    public static void menuConta() {
        System.out.println("Secao Conta\n" +
                "1 - Inserir Conta\n" +
                "2 - Acessar Conta");

    }

    public static void menuCorrentista() {
        System.out.println("Secao Correntista\n" +
                "1 - Inserir Correntista\n" +
                "2 - Exibir Correntistas");
    }

    public static void menuTipoConta() {
        System.out.println("Insira o tipo da conta: \n" +
                "Tipos de Conta\n" +
                "1 - Conta Corrente\n" +
                "2 - Conta Poupanca\n" +
                "3 - Conta Aplicacao");
    }

    public static void menuOperacao() {
        System.out.println("Selecione o tipo de operacao\n" +
                "1 - Deposito\n" +
                "2 - Saque\n" +
                "3 - Transferir\n" +
                "4 - Exibir saldo\n" +
                "5 - Voltar");
    }

}
