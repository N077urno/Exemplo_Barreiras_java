/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exemplo_barreiras_java;

/**
 *
 * @author nando
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ContaBancaria {
    private double saldo;
    private final Lock lock = new ReentrantLock();
    
    public ContaBancaria(double saldoInicial){
        saldo = saldoInicial;
    }
    
    // Método de saque com mutex
    public void sacar(double valor) {
        lock.lock();  // Adquire o lock
        try {
            if (saldo >= valor) {
                System.out.println(Thread.currentThread().getName() + " está sacando: " + String.format("%.2f", valor));
                saldo -= valor;
                System.out.println("Novo saldo após saque: " + String.format("%.2f", saldo));
            } else {
                System.out.println(Thread.currentThread().getName() + " - Saldo insuficiente para saque de: " + String.format("%.2f", valor));
            }
        } finally {
            lock.unlock();  // Libera o lock
        }
    }

    // Método de depósito com mutex
    public void depositar(double valor) {
        lock.lock();  // Adquire o lock
        try {
            System.out.println(Thread.currentThread().getName() + " está depositando: " + String.format("%.2f", valor));
            saldo += valor;
            System.out.println("Novo saldo após depósito: " + String.format("%.2f", saldo));
        } finally {
            lock.unlock();  // Libera o lock
        }
    }

    public double getSaldo() {
        return saldo;
    }
}
