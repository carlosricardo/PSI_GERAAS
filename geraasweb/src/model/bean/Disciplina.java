package model.bean;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Entity
public class Disciplina {

	@Id
	@GeneratedValue
	private Long iddisciplina;
	
	private String descricao;
	
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="usuario",insertable=true,updatable=true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)*/
			
	//private Usuario usuario;
	private Long usuario;
	
	
	@Temporal(TemporalType.DATE)
	private Calendar datacadastro;
	
	private int tipopermissao;

	public Long getIddisciplina() {
		return iddisciplina;
	}

	public void setIddisciplina(Long iddisciplina) {
		this.iddisciplina = iddisciplina;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	/*public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}*/

	public Calendar getDatacadastro() {
		return datacadastro;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public void setDatacadastro(Calendar datacadastro) {
		this.datacadastro = datacadastro;
	}

	public int getTipopermissao() {
		return tipopermissao;
	}

	public void setTipopermissao(int tipopermissao) {
		this.tipopermissao = tipopermissao;
	}

	
	

}
