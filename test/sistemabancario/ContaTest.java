package sistemabancario;

import org.junit.Test;
import static org.junit.Assert.*;

public class ContaTest {

    // Helper class to instantiate abstract Conta
    private class ContaConcreta extends Conta {
        public ContaConcreta(Correntista nome, String tipoConta) {
            super(nome, tipoConta);
        }
    }

    @Test
    public void testDepositar() {
        Correntista c = new Correntista("Teste", "123");
        Conta conta = new ContaConcreta(c, "Teste");
        
        conta.depositar(100.0);
        assertEquals(100.0, conta.getSaldo(), 0.001);
    }

    @Test
    public void testSacar() {
        Correntista c = new Correntista("Teste", "123");
        Conta conta = new ContaConcreta(c, "Teste");
        
        conta.depositar(100.0);
        conta.sacar(30.0);
        assertEquals(70.0, conta.getSaldo(), 0.001);
    }
    
    @Test
    public void testSacarSaldoInsuficiente() {
        Correntista c = new Correntista("Teste", "123");
        Conta conta = new ContaConcreta(c, "Teste");
        
        conta.depositar(50.0);
        conta.sacar(100.0);
        assertEquals(50.0, conta.getSaldo(), 0.001); // Saldo deve permanecer inalterado
    }
}
