package com.aplicacion.cajero.multihilo;

import com.aplicacion.cajero.modelos.Cajera;
import com.aplicacion.cajero.modelos.Cliente;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CountDownLatch;

// Servicio encargado de el procesamineto de compras en multiples hilos
// Permite concurrencia de cajeras y clientes
@Service
public class MultihiloServicio {
    public void procesar(List<Cliente> clientes, List<Cajera> cajeras) {
        System.out.println("Procesamiento multihilo iniciado...");
        // Timpor inicial
        long inicio = System.currentTimeMillis();

        // Sincronizar finalizacion de todos los hilos
        CountDownLatch latch = new CountDownLatch(clientes.size());

        // Asignar cajera a cliente
        for (int i = 0; i < clientes.size(); i++) {

            Cliente cliente = clientes.get(i);
            Cajera cajera = cajeras.get(i % cajeras.size());

            // Nuevo hilo para procesar la compra
            new Thread(() -> {
                try {

                    System.out.println(cajera.getNombre() + " Procesando la compra de " + cliente.getNombre());
                    cajera.procesarCompra(cliente);
                } finally {
                    // Reducir el contador al finalizar
                    latch.countDown();
                }
            }).start();
        }

        // Espera el final de todos los hilos
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Imprime el tiempo final del proceso
        long fin = System.currentTimeMillis();
        double tiempoTotal = (fin - inicio) / 1000.0;
        System.out.printf("Procesamiento Multihilo completado en: %.2f segundos\n", tiempoTotal);
    }
}

