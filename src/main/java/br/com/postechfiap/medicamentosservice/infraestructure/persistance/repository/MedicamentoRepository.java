package br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository;

import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.MedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoRepository extends JpaRepository<MedicamentoEntity, Long> {

    @Query("SELECT m FROM MedicamentoEntity m " +
            "WHERE ('' = :nomeMedicamento OR LOWER(m.nome) LIKE LOWER(CONCAT('%', :nomeMedicamento, '%'))) " +
            "AND ('' = :sku OR m.sku = :sku) " +
            "AND ('' = :principioAtivo OR m.principioAtivo LIKE %:principioAtivo%) " +
            "AND ('' = :laboratorio OR m.laboratorio LIKE %:laboratorio%)")
    List<MedicamentoEntity> findByRequestParams(@Param("nomeMedicamento") String nomeMedicamento,
                                                @Param("sku") String sku,
                                                @Param("principioAtivo") String principioAtivo,
                                                @Param("laboratorio") String laboratorio);
}
