package model.bean;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Arquivo {

	@Id
	@GeneratedValue
	private Long idarquivo;
	private String nome;
	private String descricao;
	private String caminho;
	private Long disciplina;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataCadastro;
	
	public Calendar getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Long getIdarquivo() {
		return idarquivo;
	}
	public void setIdarquivo(Long idarquivo) {
		this.idarquivo = idarquivo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public Long getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Long disciplina) {
		this.disciplina = disciplina;
	}
	
	
	
}
