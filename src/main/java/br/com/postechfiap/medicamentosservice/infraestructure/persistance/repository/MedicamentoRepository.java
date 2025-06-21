package br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository;

import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.MedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<MedicamentoEntity, Long> {

    @Query("SELECT m FROM MedicamentoEntity m " +
            "WHERE ('' = :nomeMedicamento OR m.nome LIKE %:nome%) " +
            "AND ('' = :sku OR m.sku = :sku) " +
            "AND ('' = :principioAtivo OR m.principioAtivo LIKE %:principio_ativo%) " +
            "AND ('' = :laboratorio OR m.laboratorio LIKE %:laboratorio%)")
    List<MedicamentoEntity> findByRequestParams(@Param("nomeMedicamento") String nomeMedicamento,
                                                @Param("sku") String sku,
                                                @Param("principioAtivo") String principioAtivo,
                                                @Param("laboratorio") String laboratorio);
}
