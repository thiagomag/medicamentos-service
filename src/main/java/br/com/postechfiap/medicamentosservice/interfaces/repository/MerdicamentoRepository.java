package br.com.postechfiap.medicamentosservice.interfaces.repository;

import br.com.postechfiap.medicamentosservice.entities.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerdicamentoRepository extends JpaRepository<Medicamento, Long> {
}
