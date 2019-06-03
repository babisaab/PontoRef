package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Contato implements EntidadeBase, Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String telefone;

    @ManyToOne
    private Funcionario funcionario;
    
    public Contato() {}

    public Contato(String telefone, Funcionario funcionario) {
        this.telefone = telefone;
        this.funcionario = funcionario;
        }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Funcionario getfuncionario() {
        return funcionario;
    }

    public void setfuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
 
}