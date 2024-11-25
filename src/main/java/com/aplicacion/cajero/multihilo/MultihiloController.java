package com.aplicacion.cajero.multihilo;

import com.aplicacion.cajero.modelos.Cajera;
import com.aplicacion.cajero.modelos.Cliente;
import com.aplicacion.cajero.modelos.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

// Controlador que permite gestinar las compras, mediante el enfoque multihilo
// Se distribuye la carga entre varias cajeras
@RestController
public class MultihiloController {

    @Autowired
    private MultihiloServicio servicio;
    @Autowired
    private MultihiloServicio multihiloServicio;

    @GetMapping("/procesar-multihilo")
    public String procesarMultihilo() {
        // Crear instancias de clientes con sus respectivas listas de productos
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
        
        // Crear instancias de cajeras disponibles
        Cajera cajera1 = new Cajera("Cajera 1");
        Cajera cajera2 = new Cajera("Cajera 2");
        Cajera cajera3 = new Cajera("Cajera 3");
        Cajera cajera4 = new Cajera("Cajera 4");

        //Procesar clientes y cajeras de forma paralela
        multihiloServicio.procesar(Arrays.asList(cliente1,cliente2, cliente3, cliente4),
                Arrays.asList(cajera1, cajera2, cajera3, cajera4));
        // Mensaje de finalización
        return "Multihilo completado";
    }
}
