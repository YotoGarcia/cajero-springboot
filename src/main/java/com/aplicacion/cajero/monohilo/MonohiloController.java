package com.aplicacion.cajero.monohilo;

import com.aplicacion.cajero.modelos.Cajera;
import com.aplicacion.cajero.modelos.Cliente;
import com.aplicacion.cajero.modelos.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

// Controlador que simula el procesamiento de compras de varios clientes, en el monohilo
@RestController
public class MonohiloController {

    @Autowired
    private MonohiloServicio servicio;
    @Autowired
    private MonohiloServicio monohiloServicio;

    @GetMapping("/procesar-monohilo")
        public String procesarMonohilo() {
        // Clientes con sus respectivos productos
        Cliente cliente1 = new Cliente("Pedro", Arrays.asList(
                new Producto("Carne", 50000),
                new Producto("Huevos", 20000)
        ));
        Cliente cliente2 = new Cliente("Pepe", Arrays.asList(
                new Producto("Arroz", 4000),
                new Producto("arepas", 3000)
        ));
        Cliente cliente3 = new Cliente("Nicolas", Arrays.asList(
                new Producto("Queso", 7000),
                new Producto("Panela", 3000)
        ));
        Cliente cliente4 = new Cliente("Pacho", Arrays.asList(
                new Producto("Atún", 5000),
                new Producto("Lentejas", 8000)
        ));
        

            // Cajera que procesara los productos
            Cajera cajera = new Cajera("Cajera 1");

            // Mensje indicando en final del proceso
            monohiloServicio.procesarClienteMonohilo(Arrays.asList(cliente1,cliente2, cliente3, cliente4));
            return "Monohilo completado";
        }
}
