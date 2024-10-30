/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.exemplo_barreiras_java;

/**
 *
 * @author nando
 */
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.Random;

public class Exemplo_Barreiras_java {

    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(1000);
        Random random = new Random();

        // CyclicBarrier que sincroniza duas threads
        CyclicBarrier barreira = new CyclicBarrier(2, () -> {
            System.out.println("As threads alcançaram a barreira e vão iniciar uma nova operação.\n");
        });

        // Criação das duas threads
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                double valor = 50 + (200 - 50) * random.nextDouble(); // Gera valores entre 50 e 200
                if (random.nextBoolean()) {
                    conta.depositar(valor);
                } else {
                    conta.sacar(valor);
                }
                aguardarBarreira(barreira);
            }
        }, "Thread 1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                double valor = 50 + (200 - 50) * random.nextDouble(); // Gera valores entre 50 e 200
                if (random.nextBoolean()) {
                    conta.depositar(valor);
                } else {
                    conta.sacar(valor);
                }
                aguardarBarreira(barreira);
            }
        }, "Thread 2");
        
        // Inicia as threads
        t1.start();
        t2.start();
    }
    
    private static void aguardarBarreira(CyclicBarrier barreira) {
        try {
            barreira.await(); // Espera até que ambas as threads cheguem à barreira
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
