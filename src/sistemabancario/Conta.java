package sistemabancario;

public abstract class Conta {

    private int numero;
    private String tipoConta;
    private static int contadorConta = 1;
    private double saldo;
    Correntista nome;
    Correntista cpf;

    public Conta(Correntista nome, String tipoConta) {
        this.numero = contadorConta++;
        this.nome = nome;
        this.saldo = 0.0;
        this.tipoConta = tipoConta;

    }

    public int getNumero() {
        return numero;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public static int getContadorConta() {
        return contadorConta;
    }

    public Correntista getNome() {
        return nome;
    }

    public Correntista getCpf() {
        return cpf;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Insira um valor valido");
        } else {
            this.saldo += valor;
            System.out.println("Valor de R$ " + valor + " depositado na conta " + this.getNumero()
                    + " para o correntista " + this.getNome());
            System.out.println("Saldo atual: " + this.getSaldo());
        }
    }

    public void sacar(double valor) {
        if (this.saldo < valor) {
            System.out.println("Saldo insuficiente");
        } else if (valor <= 0) {
            System.out.println("Insira um valor valido");
        } else {
            this.saldo -= valor;
            System.out.println("Valor de R$" + valor + " sacado da conta " + this.getNumero() + " para o correntista "
                    + nome.getNome());
            System.out.println("Saldo atual: " + this.getSaldo());
        }
    }

    @Override
    public String toString() {
        return "Conta{" + "numero=" + numero + ", tipo=" + tipoConta + ", saldo=" + saldo + ", nome=" + nome + ", cpf="
                + cpf + '}';
    }

}
