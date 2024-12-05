package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BancoServiceIF extends Remote {

    double saldo(String conta) throws RemoteException;
    int quantidadeContas() throws RemoteException;

    void cadastroConta(String conta, double saldo) throws RemoteException;
    boolean pesquisarConta(String conta) throws RemoteException;
    boolean removerConta(String conta) throws RemoteException;
}
