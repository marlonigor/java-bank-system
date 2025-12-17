package sistemabancario;

import java.util.ArrayList;
import java.util.List;

public class Correntista {

    String nome;
    String cpf;
    List<Conta> contas;

    public Correntista(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.contas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
    }

    public boolean possuiConta(Class<? extends Conta> tipoConta) {
        for (Conta conta : contas) {
            if (tipoConta.isInstance(conta)) {
                return true;
            }
        }
        return false;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return nome + ", " + cpf;
    }


}
