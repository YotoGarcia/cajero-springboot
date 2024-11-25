package com.aplicacion.cajero.monohilo;

import com.aplicacion.cajero.modelos.Cajera;
import com.aplicacion.cajero.modelos.Cliente;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

// Servicio encargado de procesar varios clientes de manera secuencial mediante enfoque monohilo
@Service
public class MonohiloServicio {

    public void procesarClienteMonohilo(List<Cliente> clientes) {
        // Crear una única instancia de la cajera
        Cajera cajera = new Cajera("Cajera 1");

        // Registrar el tiempo inicial del procesamiento
        System.out.println("Monohilo iniciado...");

        long inicio = System.currentTimeMillis();

        // Iterar sobre los clientes y procesar sus compras
        for (Cliente cliente : clientes) {
            System.out.println(cajera.getNombre() + " Procesando la compra de " + cliente.getNombre());
            cajera.procesarCompra(cliente);
        }

        // Registro e impresión del tiempo final del procesamiento
        long fin = System.currentTimeMillis();
        double tiempoTotal = (fin - inicio) / 1000.0;
        System.out.printf("Procesamiento Monohilo completado en: %.2f segundos\n", tiempoTotal);
    }
}
