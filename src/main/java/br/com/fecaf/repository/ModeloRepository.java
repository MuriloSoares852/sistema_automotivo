package br.com.fecaf.repository;

import br.com.fecaf.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository <Modelo, Integer> {
}
