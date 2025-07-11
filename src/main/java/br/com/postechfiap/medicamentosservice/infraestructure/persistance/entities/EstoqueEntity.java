package br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@Table(name = "estoque")
@Entity
public class EstoqueEntity extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estoque_seq_generator")
    @SequenceGenerator(name = "estoque_seq_generator", sequenceName = "estoque_id_seq", allocationSize = 1)
    private Long id;

    @Setter
    @NotBlank(message = "O nome do medicamento é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @Setter
    @NotBlank(message = "O sku do produto é obrigatório.")
    @Column(nullable = false, unique = true, updatable = true)
    private String sku;

    @Setter
    @Column(nullable = false)
    private Integer quantidade;

    @Setter
    @Builder.Default
    @Column(name = "reposicao_pendente", nullable = false)
    private Boolean reposicaoPendente = false;

}
