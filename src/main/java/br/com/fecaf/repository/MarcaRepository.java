package br.com.fecaf.repository;

import br.com.fecaf.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository <Marca, Integer> {
}
