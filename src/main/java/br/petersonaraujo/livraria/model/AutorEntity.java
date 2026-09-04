package br.petersonaraujo.livraria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_autor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 255)
    private String biografia;

    @ManyToMany(mappedBy = "autor")
    private List<LivroEntity> livros;

}
