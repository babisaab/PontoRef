package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cargo  implements EntidadeBase, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cargaHorariaObrigatoria;

    @ManyToOne
    private Departamento departamento;
    
    public Cargo() {}

    public Cargo(String nome, String cargaHorariaObrigatoria, Departamento departamento){
	this.nome = nome;
	this.cargaHorariaObrigatoria =cargaHorariaObrigatoria;
        this.departamento = departamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargaHorariaObrigatoria() {
        return cargaHorariaObrigatoria;
    }

    public void setCargaHoraria(String cargaHorariaObrigatoria) {
        this.cargaHorariaObrigatoria = cargaHorariaObrigatoria;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
}