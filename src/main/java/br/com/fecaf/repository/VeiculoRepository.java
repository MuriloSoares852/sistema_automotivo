package br.com.fecaf.repository;

import br.com.fecaf.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

    List<Veiculo> findByStatus(String status);

    List<Veiculo> findByAno(int ano);

    List<Veiculo> findByPrecoBetween(float precoMin, float precoMax);

    @Query("SELECT v FROM Veiculo v WHERE v.modelo.marca.nome = :nomeMarca")
    List<Veiculo> findByMarcaNome(@Param("nomeMarca") String nomeMarca);
}