package br.com.postechfiap.medicamentosservice.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@Table(name = "medicamento")
@Entity
public class Medicamento extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "estoque_id_seq",allocationSize = 1)
    private Long id;
    private String sku;
    private String nome;
    private String principioAtivo;
    private String laboratorio;
    private String dosagem;
    private String descricao;
    private Double preco;
    private Integer quantidadeEmEstoque;


    public void gerarSku() {
        if (this.principioAtivo == null || this.principioAtivo.isEmpty() ||
                this.dosagem == null || this.dosagem.isEmpty() ||
                this.nome == null || this.nome.isEmpty() ||
                this.laboratorio == null || this.laboratorio.isEmpty()) {
            throw new IllegalArgumentException("Não foi possível gerar o SKU: informações essenciais (princípio ativo, dosagem, nome, laboratório) estão incompletas.");
        }

        String pAbreviado = this.principioAtivo.toUpperCase().replaceAll("[^A-Z0-9]", "").substring(0, Math.min(this.principioAtivo.length(), 5)); // Pega até 5 primeiras letras e remove caracteres especiais
        String dAbreviado = this.dosagem.toUpperCase().replaceAll("[^A-Z0-9]", ""); // Remove caracteres especiais da dosagem
        String nAbreviado = this.nome.toUpperCase().replaceAll("[^A-Z0-9]", "").substring(0, Math.min(this.nome.length(), 5)); // Pega até 5 primeiras letras do nome
        String lAbreviado = this.laboratorio.toUpperCase().replaceAll("[^A-Z0-9]", "").substring(0, Math.min(this.laboratorio.length(), 4)); // Pega até 4 primeiras letras do laboratório

        this.sku = String.format("%s-%s-%s-%s", pAbreviado, dAbreviado, nAbreviado, lAbreviado);

        if (this.sku.length() > 30) { // Exemplo de limite de 30 caracteres
            this.sku = this.sku.substring(0, 30);
        }
    }

}
