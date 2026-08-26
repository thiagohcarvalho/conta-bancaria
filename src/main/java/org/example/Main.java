package org.example;

import java.util.ArrayList;
import java.util.Objects;
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
            IO.println("3 - Listar contas");
            IO.println("4 - Depositar");
            IO.println("5 - Sacar");
            IO.println("6 - Transferir");
            IO.println("7 - Consultar saldo");
            IO.println("8 - Sair");
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

    private static Cliente findCliente(String cpfCliente) {
        for (Cliente cliente : clientesList) {
            if (Objects.equals(cliente.getCpf(), cpfCliente)) {
                return cliente;
            }
        }
        return null;
    }

    private static boolean doesCpfHaveAccount(Cliente cliente) {
        for (Conta conta : contasList) {
            if (Objects.equals(conta.getCliente(), cliente)) {
                return true;
            }
        }
        return false;
    }
}
