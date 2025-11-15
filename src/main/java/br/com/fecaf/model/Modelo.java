package br.com.fecaf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "modelo")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo")
    private Integer id_modelo;

    @Column(nullable = false, length = 100)
    private String nome;

    //
    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca marca;

    // evita recursão infinita
    @JsonIgnore
    @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL)
    private List<Veiculo> veiculos;

    // Construtores
    public Modelo() {}

    public Modelo(String nome, Marca marca) {
        this.nome = nome;
        this.marca = marca;
    }

    // Getters e Setters
    public Integer getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(Integer id_modelo) {
        this.id_modelo = id_modelo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}