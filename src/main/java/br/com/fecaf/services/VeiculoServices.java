package br.com.fecaf.services;

import br.com.fecaf.model.Veiculo;
import br.com.fecaf.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoServices {

    @Autowired
    private VeiculoRepository veiculoRepository;

    // Método para listar Veiculos
    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    // Método para buscar veículo por ID
    public Optional<Veiculo> buscarPorId(int id) {
        return veiculoRepository.findById(id);
    }

    // Método para Salvar novo Veiculo
    public Veiculo salvarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    // Método para Atualizar Veiculo
    public Veiculo atualizarVeiculo(int id, Veiculo veiculoAtualizado) {
        Optional<Veiculo> veiculoExistente = veiculoRepository.findById(id);
        if (veiculoExistente.isPresent()) {
            Veiculo veiculo = veiculoExistente.get();
            veiculo.setModelo(veiculoAtualizado.getModelo());
            veiculo.setAno(veiculoAtualizado.getAno());
            veiculo.setCor(veiculoAtualizado.getCor());
            veiculo.setPreco(veiculoAtualizado.getPreco());
            veiculo.setQuilometragem(veiculoAtualizado.getQuilometragem());
            veiculo.setStatus(veiculoAtualizado.getStatus());
            return veiculoRepository.save(veiculo);
        }
        throw new RuntimeException("Veículo não encontrado com ID: " + id);
    }

    // Método para Deletar Veiculo
    public void deletarVeiculo(int id) {
        if (!veiculoRepository.existsById(id)) {
            throw new RuntimeException("Veículo não encontrado com ID: " + id);
        }
        veiculoRepository.deleteById(id);
    }

    // Buscar por Status
    public List<Veiculo> buscarPorStatus(String status) {
        return veiculoRepository.findByStatus(status);
    }

    // Buscar por Ano
    public List<Veiculo> buscarPorAno(Integer ano) {
        return veiculoRepository.findByAno(ano);
    }

    // Buscar por Faixa de Preço
    public List<Veiculo> buscarPorFaixaDePreco(float precoMin, float precoMax) {
        return veiculoRepository.findByPrecoBetween(precoMin, precoMax);
    }

    // Buscar por Nome da Marca
    public List<Veiculo> buscarPorMarca(String nomeMarca) {
        return veiculoRepository.findByMarcaNome(nomeMarca);
    }
}