package br.com.postechfiap.medicamentosservice.entities;

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
public class Estoque extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "estoque_id_seq",allocationSize = 1)
    private Long id;

    @Setter
    @NotBlank(message = "O nome do medicamento é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @Setter
    @NotBlank(message = "O sku do produto é obrigatório.")
    @Column(nullable = false, unique = true, updatable = false)
    private String sku;

    @Setter
    @Column(nullable = false)
    private Long quantidade;

}
