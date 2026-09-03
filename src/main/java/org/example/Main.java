package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    static ArrayList<Cliente> clientesList = new ArrayList<>();
    static ArrayList<Conta> contasList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int proximoNumeroConta = 1;

    static void main() {
        boolean cont = true;

        while (cont) {
            IO.println("Bem vindo ao aplicativo do banco!");
            IO.println("1 - Cadastrar cliente");
            IO.println("2 - Criar conta");
            IO.println("3 - Listar clientes");
            IO.println("4 - Listar contas");
            IO.println("5 - Depositar");
            IO.println("6 - Sacar");
            IO.println("7 - Transferir");
            IO.println("8 - Consultar saldo");
            IO.println("9 - Sair");
            IO.println("Escolha uma opção para continuar:");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": {
                    IO.println("Digite o nome do cliente a ser cadastrado:");
                    String nomeCliente = scanner.nextLine();
                    IO.println("Digite o CPF do cliente a ser cadastrado:");
                    String cpfCliente = scanner.nextLine();
                    cadastrarCliente(nomeCliente, cpfCliente);
                    IO.println("Cliente cadastrado com sucesso!");
                    IO.println("Caso deseje, crie uma conta para esse cliente.");
                }
                break;
                case "2": {
                    IO.println("Digite o CPF do cliente que deseja abrir uma conta:");
                    String cpfCliente = scanner.nextLine();
                    Cliente cliente = findCliente(cpfCliente);
                    if (cliente == null) {
                        IO.println("Cliente não cadastrado, informe um CPF de um cliente válido");
                    } else if (doesCpfHaveAccount(cliente)) {
                        IO.println("Esse CPF já está cadastrado em uma conta, tente novamente.");
                    } else {
                        cadastrarConta(cliente);
                        IO.println("Conta criada com sucesso!");
                        IO.println("Caso deseje, no menu principal, realize alguma ação na conta");
                    }
                }
                break;
                case "3": {
                    listarClientes();
                }
                break;
                case "4": {
                    listarContas();
                }
                break;
                case "5": {
                    IO.println("Digite a conta que você deseja realizar a ação:");
                    int numeroConta = scanner.nextInt();
                    Optional<Conta> conta = findConta(numeroConta);
                    if (conta.isPresent()) {
                        IO.println("Digite a quantidade que você deseja depositar:");
                        BigDecimal valorADepositar = scanner.nextBigDecimal();
                        if (valorADepositar.compareTo(BigDecimal.ZERO) > 0) {
                            conta.get().depositar(valorADepositar);
                            IO.println("Valor depositado. Para consultar o saldo total, escolha a opção no menu!");
                        } else {
                            IO.println("Valor abaixo de zero, tente novamente.");
                        }
                    } else {
                        IO.println("Conta não encontrada! Tente novamente.");
                    }
                }
                break;
            }
        }


    }

    private static void cadastrarCliente(String nomeCliente, String cpfCliente) {
        Cliente cliente = new Cliente(nomeCliente, cpfCliente);
        clientesList.add(cliente);
    }

    private static void cadastrarConta(Cliente cliente) {
        Conta conta = new Conta(proximoNumeroConta++, cliente);
        contasList.add(conta);
    }

    private static void listarClientes() {
        for (Cliente cliente : clientesList) {
            boolean hasAccount = doesCpfHaveAccount(cliente);

            IO.println("========================");
            IO.println("Nome do cliente: " + cliente.getNome());
            IO.println("CPF do cliente: " + cliente.getCpf());
            IO.println("O cliente possui conta? " + (hasAccount ? "Sim" : "Não"));
        }
        IO.println("========================");
    }

    private static void listarContas() {
        for (Conta conta : contasList) {
            IO.println("========================");
            IO.println("Conta número: " + conta.getNumero());
            IO.println("Saldo: " + conta.getSaldo());
            IO.println("Nome do cliente: " + conta.getCliente().getNome());
            IO.println("CPF do cliente: " + conta.getCliente().getCpf());
        }
        IO.println("========================");
    }

    private static Cliente findCliente(String cpfCliente) {
        for (Cliente cliente : clientesList) {
            if (Objects.equals(cliente.getCpf(), cpfCliente)) {
                return cliente;
            }
        }
        return null;
    }

    private static boolean doesCpfHaveAccount(Cliente cliente) {
        return contasList.stream().anyMatch(conta -> conta.getCliente().equals(cliente));
    }

    private static Optional<Conta> findConta(int numeroConta) {
        return contasList.stream().filter(conta -> conta.getNumero() == numeroConta).findAny();
    }
}
