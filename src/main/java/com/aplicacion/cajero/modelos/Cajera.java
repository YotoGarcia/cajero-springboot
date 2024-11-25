package com.aplicacion.cajero.modelos;


//Clase que representa a una Cajera en un sistema de simulación de compras
// La cajera se representa mediane un nombre
public class Cajera {

    private String nombre;

    public Cajera(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void procesarCompra(Cliente cliente) {
        // inicio del proceso de compra.
        long inicio = System.currentTimeMillis();

        // Itera sobre los productos del cliente.
        cliente.getProductos().forEach(producto -> {
            System.out.println(nombre + " cobrando producto: " + producto.getNombre()
                    + " - Precio: " + producto.getPrecio());

            // tiempo de cobro de 1 segundo por producto.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Manejo de interrupcion.
                Thread.currentThread().interrupt();
                System.err.println("El proceso fue interrumpido para " + nombre);
            }
        });

        // Final del proceso de compra.
        long fin = System.currentTimeMillis();
        double tiempoSegundos = (fin - inicio) / 1000.0;

        // Tiempo total que la cajera tardó en procesar al cliente.
        System.out.println(nombre + " terminó con el cliente " + cliente.getNombre()
                + " en " + tiempoSegundos + " segundos");
    }

    @Override
    public String toString() {
        return "Cajera{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
