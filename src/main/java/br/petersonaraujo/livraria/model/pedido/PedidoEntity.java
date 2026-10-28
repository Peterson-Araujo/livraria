package br.petersonaraujo.livraria.model.pedido;

import br.petersonaraujo.livraria.model.cliente.ClienteEntity;
import br.petersonaraujo.livraria.model.enums.EnumPagamento;
import br.petersonaraujo.livraria.model.enums.EnumStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @Column(name = "data_pedido", nullable = false)
    private LocalDate dataPedido;

    @Column(nullable = false)
    private EnumStatus status;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "forma_pagamento", nullable = false)
    private EnumPagamento formaPagamento;
}
