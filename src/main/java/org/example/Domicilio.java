package org.example;

import lombok.*;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

@Entity
@Table(name = "Domicilio")
public class Domicilio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCalle;
    private int numero;

    //para que sea bidireccional
    //@OneToOne(mappedBy = "domicilio")
    //private Cliente cliente;
}
