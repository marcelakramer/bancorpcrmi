package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        contas = new ArrayList<>();
        contas.add(new Conta("1", 100.0));
        contas.add(new Conta("2", 156.0));
        contas.add(new Conta("3", 950.0));
    }

    @Override
    public double saldo(String numero) throws RemoteException {
        Conta conta = buscarConta(numero);
        if (conta != null) {
            return conta.getSaldo();
        }
        return -1;
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public void cadastroConta(String numero, double saldo) throws RemoteException {
        if (buscarConta(numero) == null) {
            contas.add(new Conta(numero, saldo));
            System.out.println("Conta " + numero + " cadastrada com saldo de " + saldo);
        } else {
            System.out.println("Conta " + numero + " já existe.");
        }
    }

    @Override
    public boolean pesquisarConta(String numero) throws RemoteException {
        return buscarConta(numero) != null;
    }

    @Override
    public boolean removerConta(String numero) throws RemoteException {
        Conta conta = buscarConta(numero);
        if (conta != null) {
            contas.remove(conta);
            System.out.println("Conta " + numero + " removida.");
            return true;
        }
        System.out.println("Conta " + numero + " não encontrada.");
        return false;
    }

    private Conta buscarConta(String numero) {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                return conta;
            }
        }
        return null;
    }
}
