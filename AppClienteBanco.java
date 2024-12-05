package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        if (args.length != 1) {
            System.out.println("Erro: IP não informado.");
            return;
        }

        String servidorIP = args[0];
        System.out.println("Conectando ao servidor RMI Registry em: " + servidorIP);

        Registry registry = LocateRegistry.getRegistry(servidorIP);

        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while (opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    // Chamada ao método remoto, como se fosse executar localmente
                    System.out.println("Saldo da conta " + conta + ": " + banco.saldo(conta));
                    break;
                }
                case 2: {
                    // Chamada ao método remoto, como se fosse executar localmente
                    System.out.println("Quantidade de contas no banco: " + banco.quantidadeContas());
                    break;
                }
                case 3: {
                    System.out.println("Digite o número da nova conta:");
                    String numeroConta = entrada.next();
                    System.out.println("Digite o saldo inicial:");
                    double saldoInicial = entrada.nextDouble();
                    // Chamada ao método remoto para cadastrar uma nova conta
                    banco.cadastroConta(numeroConta, saldoInicial);
                    System.out.println("Conta " + numeroConta + " cadastrada com saldo de " + saldoInicial);
                    break;
                }
                case 4: {
                    System.out.println("Digite o número da conta a ser pesquisada:");
                    String numeroConta = entrada.next();
                    // Chamada ao método remoto para verificar se a conta existe
                    boolean existe = banco.pesquisarConta(numeroConta);
                    if (existe) {
                        System.out.println("A conta " + numeroConta + " existe.");
                    } else {
                        System.out.println("A conta " + numeroConta + " não existe.");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Digite o número da conta a ser removida:");
                    String numeroConta = entrada.next();
                    // Chamada ao método remoto para remover a conta
                    boolean removida = banco.removerConta(numeroConta);
                    if (removida) {
                        System.out.println("Conta " + numeroConta + " removida com sucesso.");
                    } else {
                        System.out.println("Não foi possível remover a conta " + numeroConta + ". Conta não encontrada.");
                    }
                    break;
                }
                default: {
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
                }
            }
            menu();
            opcao = entrada.nextInt();
        }
        entrada.close();
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI === / Marcela Barreto de Oliveira Kramer(20221370019)");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Cadastrar nova conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("9 - Sair");
    }
}
