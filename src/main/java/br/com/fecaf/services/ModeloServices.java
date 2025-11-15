package br.com.fecaf.services;

import br.com.fecaf.model.Modelo;
import br.com.fecaf.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloServices {

    @Autowired
    private ModeloRepository modeloRepository;

    // Método para listar modelos
    public List<Modelo> listarModelos() {
        return modeloRepository.findAll();
    }

    // Método para buscar Modelo por ID
    public Optional<Modelo> buscarPorId(int id) {
        return modeloRepository.findById(id);
    }

    // Método para Salvar novo Modelo
    public Modelo salvarModelo(Modelo modelo) {
        return modeloRepository.save(modelo);
    }

    // Método para Atualizar Modelo
    public Modelo atualizarModelo(int id, Modelo modeloAtualizado) {
        Optional<Modelo> modeloExistente = modeloRepository.findById(id);
        if (modeloExistente.isPresent()) {
            Modelo modelo = modeloExistente.get();
            modelo.setNome(modeloAtualizado.getNome());
            modelo.setMarca(modeloAtualizado.getMarca());
            return modeloRepository.save(modelo);
        }
        throw new RuntimeException("Modelo não encontrado com ID: " + id);
    }

    // Método para Deletar Modelo
    public void deletarModelo(int id) {
        if (!modeloRepository.existsById(id)) {
            throw new RuntimeException("Modelo não encontrado com ID: " + id);
        }
        modeloRepository.deleteById(id);
    }
}