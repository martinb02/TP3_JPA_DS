package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("En marcha Martin");

        try {
            entityManager.getTransaction().begin();

            // Crear un domicilio
            Domicilio domicilio = Domicilio.builder()
                    .nombreCalle("Av. Siempre Viva")
                    .numero(742)
                    .build();

            // Crear un cliente.
            Cliente cliente = Cliente.builder()
                    .nombre("Martin")
                    .apellido("Gomez")
                    .dni(12345678)
                    .domicilio(domicilio)
                    .build();

            // Crear categorías
            Categoria categoria1 = Categoria.builder()
                    .denominacion("Electrónica")
                    .build();

            Categoria categoria2 = Categoria.builder()
                    .denominacion("Muebles")
                    .build();

            Categoria categoria3 = Categoria.builder()
                    .denominacion("Hogar")
                    .build();  // Nueva categoría

            // Crear artículos
            Articulo articulo1 = Articulo.builder()
                    .denominacion("Televisor")
                    .precio(15000)
                    .cantidad(1)
                    .categorias(Arrays.asList(categoria1, categoria2))
                    .build();

            Articulo articulo2 = Articulo.builder()
                    .denominacion("Sofá")
                    .precio(20000)
                    .cantidad(1)
                    .categorias(Arrays.asList(categoria2, categoria3))
                    .build();  // Nuevo artículo

            Articulo articulo3 = Articulo.builder()
                    .denominacion("Refrigerador")
                    .precio(30000)
                    .cantidad(1)
                    .categorias(Arrays.asList(categoria1, categoria3))
                    .build();  // Otro nuevo artículo

            // Crear detalles de factura
            DetalleFactura detalle1 = DetalleFactura.builder()
                    .cantidad(1)
                    .subtotal(15000)
                    .articulo(articulo1)
                    .build();

            DetalleFactura detalle2 = DetalleFactura.builder()
                    .cantidad(1)
                    .subtotal(20000)
                    .articulo(articulo2)
                    .build();  // Nuevo detalle

            DetalleFactura detalle3 = DetalleFactura.builder()
                    .cantidad(1)
                    .subtotal(30000)
                    .articulo(articulo3)
                    .build();  // Otro nuevo detalle

            // Crear la factura
            Factura factura = Factura.builder()
                    .fecha("2024-09-04")
                    .numero(1001)
                    .total(65000)  // Total de todos los artículos
                    .cliente(cliente)
                    .detalles(Arrays.asList(detalle1, detalle2, detalle3))  // Agregamos todos los detalles
                    .build();

            // Persistir los objetos
            entityManager.persist(factura);

            entityManager.getTransaction().commit();

            System.out.println("Factura persistida correctamente.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Error al persistir la factura. Transacción revertida.");
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            entityManagerFactory.close();
        }

    }
}
