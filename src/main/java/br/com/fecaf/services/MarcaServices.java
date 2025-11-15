package br.com.fecaf.services;

import br.com.fecaf.model.Marca;
import br.com.fecaf.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServices {

    @Autowired
    private MarcaRepository marcaRepository;

    // Método para listar Marcas
    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    // Método para buscar Marca por ID
    public Optional<Marca> buscarPorId(int id) {
        return marcaRepository.findById(id);
    }

    // Método para Salvar nova Marca
    public Marca salvarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    // Método para Atualizar Marca
    public Marca atualizarMarca(int id, Marca marcaAtualizada) {
        Optional<Marca> marcaExistente = marcaRepository.findById(id);
        if (marcaExistente.isPresent()) {
            Marca marca = marcaExistente.get();
            marca.setNome(marcaAtualizada.getNome());
            return marcaRepository.save(marca);
        }
        throw new RuntimeException("Marca não encontrada com ID: " + id);
    }

    // Método para Deletar Marca
    public void deletarMarca(int id) {
        if (!marcaRepository.existsById(id)) {
            throw new RuntimeException("Marca não encontrada com ID: " + id);
        }
        marcaRepository.deleteById(id);
    }
}