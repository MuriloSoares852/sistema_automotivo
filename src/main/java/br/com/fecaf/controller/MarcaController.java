package br.com.fecaf.controller;

import br.com.fecaf.model.Marca;
import br.com.fecaf.services.MarcaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marca")
@CrossOrigin(origins = "*")
public class MarcaController {

    @Autowired
    private MarcaServices marcaService;

    // Endpoint para listar Marcas
    @GetMapping("/listarMarcas")
    public ResponseEntity<List<Marca>> listarMarcas() {
        List<Marca> marcas = marcaService.listarMarcas();
        return ResponseEntity.ok(marcas);
    }

    // Endpoint para buscar marca por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Marca> buscarPorId(@PathVariable int id) {
        Optional<Marca> marca = marcaService.buscarPorId(id);
        return marca.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para cadastrar marca
    @PostMapping("/cadastrarMarca")
    public ResponseEntity<Marca> salvarMarca(@RequestBody Marca marca) {
        Marca newMarca = marcaService.salvarMarca(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMarca);
    }

    // Endpoint para deletar marca
    @DeleteMapping("/deletarMarca/{id}")
    public ResponseEntity<String> deletarMarca(@PathVariable int id) {
        try {
            marcaService.deletarMarca(id);
            return ResponseEntity.ok("Marca deletada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Atualizar marca com tratamento de erro
    @PutMapping("/atualizarMarca/{id}")
    public ResponseEntity<Marca> atualizarMarca(
            @PathVariable int id,
            @RequestBody Marca marca
    ) {
        try {
            Marca marcaAtualizada = marcaService.atualizarMarca(id, marca);
            return ResponseEntity.ok(marcaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}