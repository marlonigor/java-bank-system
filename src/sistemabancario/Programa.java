package sistemabancario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {
        List<Correntista> correntistas = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        int opcaoMenuGeral;

        do {
            Menu.menuGeral();
            opcaoMenuGeral = Integer.parseInt(sc.nextLine().trim());

            switch (opcaoMenuGeral) {
                case 1: //Secao Conta
                    Menu.menuConta();
                    int opcaoMenuConta = Integer.parseInt(sc.nextLine());

                    switch (opcaoMenuConta) {
                        case 1: 
                            inserirConta(correntistas, sc);
                            break;

                        case 2: //Acessar Conta
                            System.out.println("Insira o cpf do correntista: ");
                            String cpfCorrentista = sc.nextLine();
                            
                            Correntista correntistaEncontrado = null;
                            for (Correntista correntista : correntistas) {
                                if (correntista.getCpf().equals(cpfCorrentista)) {
                                    correntistaEncontrado = correntista;
                                    break;
                                }
                            }
                            
                            if (correntistaEncontrado == null){
                                System.out.println("Correntista nao encontrado. Crie um novo correntista ou escolha um existente.");
                            } else {
                                Menu.menuTipoConta();
                            }
                            
                            Conta contaEncontrada = null;
                            
                            int opcaoTipoConta = Integer.parseInt(sc.nextLine());

                                switch (opcaoTipoConta) {
                                    case 1: //Conta Corrente
                                        for(Conta conta : correntistaEncontrado.getContas()){
                                            if (conta instanceof ContaCorrente) {
                                                    contaEncontrada = conta;
                                                    System.out.println("Conta Corrente de numero " + contaEncontrada.getNumero() + " encontrada para o CPF " + correntistaEncontrado.getCpf() + ".");
                                                    break;
                                            }
                                        }
                                        break;
                                        
                                    case 2: //Conta Poupança
                                        for(Conta conta : correntistaEncontrado.getContas()){
                                            if (conta instanceof ContaPoupanca) {
                                                    contaEncontrada = conta;
                                                    System.out.println("Conta Poupanca de numero " + contaEncontrada.getNumero() + " encontrada para o CPF " + correntistaEncontrado.getCpf() + ".");
                                                    break;
                                            }
                                        }
                                        break;
                                                    
                                    case 3: //Conta Poupança
                                        for(Conta conta : correntistaEncontrado.getContas()){
                                            if (conta instanceof ContaAplicacao) {
                                                    contaEncontrada = conta;
                                                    System.out.println("Conta Aplicacao de numero " + contaEncontrada.getNumero() + " encontrada para o CPF " + correntistaEncontrado.getCpf() + ".");
                                                    break;
                                            }
                                        }
                                        break;
                                        
                                    default:
                                        System.out.println("Tipo de opção inválida!");
                                        break;
                                }
                            
                            if (contaEncontrada != null){
                                
                                int opcaoOperacao;
                                
                                do{
                                Menu.menuOperacao();
                                
                                opcaoOperacao = Integer.parseInt(sc.nextLine());
                                
                                switch (opcaoOperacao){
                                    case 1: //Depositar
                                        System.out.println("Insira o valor do deposito: ");
                                        double valorDeposito = Double.parseDouble(sc.nextLine());
                                        contaEncontrada.depositar(valorDeposito);
                                        
                                        break;
                                    case 2: //Sacar
                                        System.out.println("Insira o valor do saque: ");
                                        double valorSaque = Double.parseDouble(sc.nextLine());
                                        contaEncontrada.sacar(valorSaque);
                                        
                                        break;
                                    case 3: //Transferir
                                        transferenciaEntreContas(correntistas, sc, contaEncontrada);
                                        break;
                                        
                                    case 4: //Exibir saldo
                                        System.out.println("Saldo atual da conta: " + contaEncontrada.getSaldo());
                                        break;
                                        
                                    case 5: //Voltar
                                        break;
                                        
                                    default:
                                        System.out.println("Selecione uma opcao valida");
                                        Menu.menuOperacao();
                                        break;        
                                }
                            } while(opcaoOperacao != 5);
                            }
                            break;
                            
                        default:
                            System.out.println("Opcao invalida!");
                            break;
                            
                        }
                    break;

                case 2:
                    Menu.menuCorrentista();
                    int opcaoMenuCorrentista = Integer.parseInt(sc.nextLine());

                    switch (opcaoMenuCorrentista) {
                        case 1:
                            inserirCorrentista(correntistas, sc);
                            break;
                            
                        case 2:
                            System.out.println(correntistas.toString());
                            break;

                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }
                    break;

                case 3:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcaoMenuGeral != 0);

        sc.close();
    }
    
public static void inserirConta(List<Correntista> correntistas, Scanner sc) {
    
    
        System.out.println("Insira o CPF do correntista: ");
        String cpfCorrentista = sc.nextLine();

        Correntista correntistaEncontrado = null;
        for (Correntista correntista : correntistas) {
            if (correntista.getCpf().equals(cpfCorrentista)) {
                correntistaEncontrado = correntista;
                break;
            }
        }

        if (correntistaEncontrado == null) {
            System.out.println("Correntista nao encontrado. Crie um novo correntista ou escolha um existente.");
        } else {
            Menu.menuTipoConta();

            int opcaoTipoConta = Integer.parseInt(sc.nextLine());

            switch (opcaoTipoConta) {
                case 1: //Conta Corrente
                    if (correntistaEncontrado.possuiConta(ContaCorrente.class)) {
                        System.out.println("O correntista já possui uma conta corrente.");
                    } else {
                        ContaCorrente novaCC = new ContaCorrente(correntistaEncontrado);
                        correntistaEncontrado.adicionarConta(novaCC);
                        System.out.println("Conta Corrente de numero " + novaCC.getNumero() + " criada para: " + correntistaEncontrado.getNome());
                    }
                    break;

                case 2: //Conta Poupanca
                    if (correntistaEncontrado.possuiConta(ContaPoupanca.class)) {
                        System.out.println("O correntista já possui uma conta poupanca.");
                    } else {
                        ContaPoupanca novaCP = new ContaPoupanca(correntistaEncontrado);
                        correntistaEncontrado.adicionarConta(novaCP);
                        System.out.println("Conta Poupança de numero " + novaCP.getNumero() + " criada para: " + correntistaEncontrado.getNome());
                    }
                    break;

                case 3: //Conta Aplicação
                    if (correntistaEncontrado.possuiConta(ContaAplicacao.class)) {
                        System.out.println("O correntista ja possui uma conta aplicacao.");
                    } else {
                        ContaAplicacao novaCA = new ContaAplicacao(correntistaEncontrado);
                        correntistaEncontrado.adicionarConta(novaCA);
                        System.out.println("Conta Aplicacao de numero " + novaCA.getNumero() + " criada para: " + correntistaEncontrado.getNome());
                    }
                    break;

                default:
                    System.out.println("Opcao Invalida!");
                    break;
            }
        }
    }

public static void inserirCorrentista(List <Correntista> correntistas, Scanner sc){
    System.out.println("Insira o nome do correntista: ");
    String nomeCorrentista = sc.nextLine().trim();

    System.out.println("Insira o CPF do correntista: ");
    String cpfCorrentista = sc.nextLine().trim();
    Correntista novoCorrentista = new Correntista(nomeCorrentista, cpfCorrentista);
    correntistas.add(novoCorrentista);
    System.out.println("Correntista " + novoCorrentista.getNome() + " criado com sucesso!");
}

public static void transferenciaEntreContas(List<Correntista> correntistas, Scanner sc, Conta contaEncontrada) {
        System.out.println("Insira o cpf do correntista da conta destino: ");
        String cpfDestino = sc.nextLine();

        Correntista correntistaDestino = null;
        for (Correntista correntista : correntistas) {
            if (correntista.getCpf().equals(cpfDestino)) {
                correntistaDestino = correntista;
                break;
            }
        }

        if (correntistaDestino == null) {
            System.out.println("Correntista nao encontrado. Escolha um existente");
        } else {
            Menu.menuTipoConta();
        }


        
        Conta contaDestino = null;

        int opcaoTipoConta = Integer.parseInt(sc.nextLine());

        switch (opcaoTipoConta) {
            case 1: //Conta Corrente
                for (Conta conta : correntistaDestino.getContas()) {
                    if (conta instanceof ContaCorrente) {
                        contaDestino = conta;
                        System.out.println("Conta Corrente de numero " + contaDestino.getNumero() + " encontrada para o CPF " + correntistaDestino.getCpf() + ".");

                        System.out.println("Insira o valor que sera transferido");
                        double valorTransferencia = Double.parseDouble(sc.nextLine());

                        if (valorTransferencia > contaEncontrada.getSaldo()) {
                        } else if (valorTransferencia <= 0) {
                            System.out.println("Insira um valor valido");
                        } else {
                            contaEncontrada.sacar(valorTransferencia);
                            contaDestino.depositar(valorTransferencia);
                            System.out.println("Transferencia da " + contaEncontrada.getTipoConta() + "de numero " + contaEncontrada.getNumero() + " realizada para a " + contaDestino.getTipoConta() + " de numero " + contaDestino.getNumero() + " no valor de R$ " + valorTransferencia + ".");

                        }
                    }
                }

                System.out.println("Saldo atual da conta de origem: R$ " + contaEncontrada.getSaldo() + ".");
                System.out.println("Saldo atual da conta destino: R$ " + contaDestino.getSaldo() + ".");

                break;

            case 2: //Conta Poupanca
                for (Conta conta : correntistaDestino.getContas()) {
                    if (conta instanceof ContaPoupanca) {
                        contaDestino = conta;
                        System.out.println("Conta Corrente de numero " + contaDestino.getNumero() + " encontrada para o CPF " + correntistaDestino.getCpf() + ".");

                        System.out.println("Insira o valor que sera transferido");
                        double valorTransferencia = Double.parseDouble(sc.nextLine());

                        if (valorTransferencia > contaEncontrada.getSaldo()) {
                        } else if (valorTransferencia <= 0) {
                            System.out.println("Insira um valor valido");
                        } else {
                            contaEncontrada.sacar(valorTransferencia);
                            contaDestino.depositar(valorTransferencia);
                            System.out.println("Transferencia da " + contaEncontrada.getTipoConta() + "de numero " + contaEncontrada.getNumero() + " realizada para a " + contaDestino.getTipoConta() + " de numero " + contaDestino.getNumero() + " no valor de R$ " + valorTransferencia + ".");

                        }
                    }
                }

                System.out.println("Saldo atual da conta de origem: R$ " + contaEncontrada.getSaldo() + ".");
                System.out.println("Saldo atual da conta destino: R$ " + contaDestino.getSaldo() + ".");

                break;

            case 3: //Conta Aplicacao
                for (Conta conta : correntistaDestino.getContas()) {
                    if (conta instanceof ContaAplicacao) {
                        contaDestino = conta;
                        System.out.println("Conta Corrente de numero " + contaDestino.getNumero() + " encontrada para o CPF " + correntistaDestino.getCpf() + ".");

                        System.out.println("Insira o valor que sera transferido");
                        double valorTransferencia = Double.parseDouble(sc.nextLine());

                        if (valorTransferencia > contaEncontrada.getSaldo()) {
                        } else if (valorTransferencia <= 0) {
                            System.out.println("Insira um valor valido");
                        } else {
                            contaEncontrada.sacar(valorTransferencia);
                            contaDestino.depositar(valorTransferencia);
                            System.out.println("Transferencia da " + contaEncontrada.getTipoConta() + "de numero " + contaEncontrada.getNumero() + " realizada para a " + contaDestino.getTipoConta() + " de numero " + contaDestino.getNumero() + " no valor de R$ " + valorTransferencia + ".");

                        }
                    }
                }

                System.out.println("Saldo atual da conta de origem: R$ " + contaEncontrada.getSaldo() + ".");
                System.out.println("Saldo atual da conta destino: R$ " + contaDestino.getSaldo() + ".");

                break;
        }
    }
}
