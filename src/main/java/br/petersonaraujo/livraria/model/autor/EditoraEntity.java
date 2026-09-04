package br.petersonaraujo.livraria.model.autor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_editora")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditoraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 255, unique = true)
    private String cnpj;

    @Column(nullable = false, length = 255, unique = true)
    private String email;
}
