package br.com.fecaf.controller;

import br.com.fecaf.model.Modelo;
import br.com.fecaf.services.ModeloServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modelo")
@CrossOrigin(origins = "*")
public class ModeloController {

    @Autowired
    private ModeloServices modeloService;

    @GetMapping("/listarModelos")
    public ResponseEntity<List<Modelo>> listarModelos() {
        List<Modelo> modelos = modeloService.listarModelos();
        return ResponseEntity.ok(modelos);
    }

    @PostMapping("/cadastrarModelo")
    public ResponseEntity<Modelo> salvarModelo(@RequestBody Modelo modelo) {
        Modelo newModelo = modeloService.salvarModelo(modelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newModelo);
    }

    @DeleteMapping("/deletarModelo/{id}")
    public ResponseEntity<String> deletarModelo(@PathVariable int id) {
        try {
            modeloService.deletarModelo(id);
            return ResponseEntity.ok("Modelo deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo não encontrado");
        }
    }

    @PutMapping("/atualizarModelo/{id}")
    public ResponseEntity<Modelo> atualizarModelo(
            @PathVariable int id,
            @RequestBody Modelo modelo
    ) {
        modelo.setId_modelo(id);
        Modelo modeloAtualizado = modeloService.salvarModelo(modelo);
        return ResponseEntity.ok(modeloAtualizado);
    }
}