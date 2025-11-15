package br.com.fecaf.controller;

import br.com.fecaf.model.Veiculo;
import br.com.fecaf.services.VeiculoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/veiculo")
@RestController
@CrossOrigin(origins = "*") // Permite requisições do frontend
public class VeiculoController {

    @Autowired
    private VeiculoServices veiculoService;

    // Endpoint para listar TODOS os Veiculos
    @GetMapping("/listarVeiculos")
    public ResponseEntity<List<Veiculo>> listarVeiculos() {
        List<Veiculo> veiculos = veiculoService.listarVeiculos();
        return ResponseEntity.ok(veiculos);
    }

    // Endpoint para buscar veículo por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable int id) {
        Optional<Veiculo> veiculo = veiculoService.buscarPorId(id);
        return veiculo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para CADASTRAR novo veículo
    @PostMapping("/cadastrarVeiculo")
    public ResponseEntity<Veiculo> salvarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo newVeiculo = veiculoService.salvarVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVeiculo);
    }

    // Endpoint para DELETAR veículo
    @DeleteMapping("/deletarVeiculo/{id}")
    public ResponseEntity<String> deletarVeiculo(@PathVariable int id) {
        try {
            veiculoService.deletarVeiculo(id);
            return ResponseEntity.ok("Veículo deletado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint para ATUALIZAR veículo
    @PutMapping("/atualizarVeiculo/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(
            @PathVariable int id,
            @RequestBody Veiculo veiculo
    ) {
        try {
            Veiculo veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculo);
            return ResponseEntity.ok(veiculoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para FILTRAR por Status
    @GetMapping("/filtrar/status")
    public ResponseEntity<List<Veiculo>> filtrarPorStatus(@RequestParam String status) {
        List<Veiculo> veiculos = veiculoService.buscarPorStatus(status);
        return ResponseEntity.ok(veiculos);
    }

    //  Endpoint para FILTRAR por Ano
    @GetMapping("/filtrar/ano")
    public ResponseEntity<List<Veiculo>> filtrarPorAno(@RequestParam Integer ano) {
        List<Veiculo> veiculos = veiculoService.buscarPorAno(ano);
        return ResponseEntity.ok(veiculos);
    }

    //Endpoint para FILTRAR por Faixa de Preço
    @GetMapping("/filtrar/preco")
    public ResponseEntity<List<Veiculo>> filtrarPorPreco(
            @RequestParam float precoMin,
            @RequestParam float precoMax
    ) {
        List<Veiculo> veiculos = veiculoService.buscarPorFaixaDePreco(precoMin, precoMax);
        return ResponseEntity.ok(veiculos);
    }

    // Endpoint para FILTRAR por Marca
    @GetMapping("/filtrar/marca")
    public ResponseEntity<List<Veiculo>> filtrarPorMarca(@RequestParam String nomeMarca) {
        List<Veiculo> veiculos = veiculoService.buscarPorMarca(nomeMarca);
        return ResponseEntity.ok(veiculos);
    }

    // Endpoint com múltiplos filtros opcionais
    @GetMapping("/filtrar")
    public ResponseEntity<List<Veiculo>> filtrarVeiculos(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) String marca
    ) {
        if (status != null) {
            return ResponseEntity.ok(veiculoService.buscarPorStatus(status));
        }
        if (ano != null) {
            return ResponseEntity.ok(veiculoService.buscarPorAno(ano));
        }
        if (marca != null) {
            return ResponseEntity.ok(veiculoService.buscarPorMarca(marca));
        }
        return ResponseEntity.ok(veiculoService.listarVeiculos());
    }
}