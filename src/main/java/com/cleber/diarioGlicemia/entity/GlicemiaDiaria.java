package com.cleber.diarioGlicemia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "diario_glicemia",
uniqueConstraints = @UniqueConstraint(columnNames = "momento"))
public class GlicemiaDiaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "momento", nullable = false, unique = true)
    private LocalDate data;

    private Double jejum;

    @Column(name = "pos_cafe")
    private Double posCafe;

    @Column(name = "antes_almoco")
    private Double antesAlmoco;

    @Column(name = "pos_almoco")
    private Double posAlmoco;

    @Column(name = "antes_jantar")
    private Double antesJantar;

    @Column(name = "pos_jantar")
    private Double posJantar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    public GlicemiaDiaria(LocalDate hoje) {
        this.data = hoje;
    }
}
