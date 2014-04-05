package model.bean;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Long idusuario;
	
	private String email;
	private String senha;
	private String confirmasenha;
	private String nome;
	private String emailconfirmacao;

	@Temporal(TemporalType.DATE)
	private Calendar dataCadastro;

	public Long getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmasenha() {
		return confirmasenha;
	}

	public void setConfirmasenha(String confirmasenha) {
		this.confirmasenha = confirmasenha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmailconfirmacao() {
		return emailconfirmacao;
	}

	public void setEmailconfirmacao(String emailconfirmacao) {
		this.emailconfirmacao = emailconfirmacao;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	/*public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		if (!this.senha.equalsIgnoreCase(this.confirmasenha)){
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha confirmada incorretamente",""));
			return "usuario";
		}
		return "sucesso";
		///return "mostraUsuario?faces-redirect=true";
	}*/
	
	
	

}
