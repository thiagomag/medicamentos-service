package br.com.postechfiap.medicamentosservice.interfaces.repository;

import br.com.postechfiap.medicamentosservice.entities.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    @Query("SELECT m FROM Medicamento m " +
            "WHERE ('' = :nomeMedicamento OR m.nome LIKE %:nome%) " +
            "AND ('' = :sku OR m.sku = :sku) " +
            "AND ('' = :principioAtivo OR m.principioAtivo LIKE %:principio_ativo%) " +
            "AND ('' = :laboratorio OR m.laboratorio LIKE %:laboratorio%)")
    List<Medicamento> findByRequestParams(@Param("nomeMedicamento") String nomeMedicamento,
                                                  @Param("sku") String sku,
                                                  @Param("principioAtivo") String principioAtivo,
                                                  @Param("laboratorio") String laboratorio);
}
