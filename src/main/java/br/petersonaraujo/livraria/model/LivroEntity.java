package br.petersonaraujo.livraria.model;

import br.petersonaraujo.livraria.model.enums.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_livro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255, unique = true)
    private String isbn;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false)
    private int estoque;

    @Column(name = "ano_publicacao", nullable = false, length = 4)
    private int anoPublicacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "editora_id", nullable = false)
    private EditoraEntity editora;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
            name = "tb_livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<AutorEntity> autor;
}
