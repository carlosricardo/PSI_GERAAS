package control.mb;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.swing.text.AbstractDocument.LeafElement;

import model.bean.Usuario;
import model.dao.UsuarioDAO;
import model.dao.JPAUtil;
//@ViewScoped
@ManagedBean
@SessionScoped

public class UsuarioMB  {
	
	  private String email;
	  private String senha;

	  public String getEmail(){
	    return this.email; 
	  }

	  public void setEmail(String no){ 
	    this.email = no;
	  }

	  public String getSenha(){
	    return this.senha;
	  }

	  public void setSenha(String se){
	    this.senha = se;
	  }
	  
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	//Atributo que guarda a colecao Usuarios armazenados em BD
	public List<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	//Atributo que guarda a colecao Usuarios armazenados em BD
	public List<Usuario> lUsuario = new ArrayList<Usuario>();
	
	public List<Usuario> getLUsuario() {
		return lUsuario;
	}	
	
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}	

	//Metodo invocado apos a classe ser carregada pelo servidor
	@PostConstruct
	public void carregarUsuarios(){
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);
		listaUsuarios = dao.listar();
		em.close();
	}
	
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);
		em.getTransaction().begin();
		dao.excluir(usuario);
		em.getTransaction().commit();
		em.close();
		carregarUsuarios();
	}
	
	
	public void salvar(){
		
		
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);
		em.getTransaction().begin();
		usuario.setDataCadastro(Calendar.getInstance());
		if(usuario.getIdusuario()!=null){
			dao.alterar(usuario);
		}else{
			dao.cadastrar(usuario);
		}
		em.getTransaction().commit();
		em.close();
		usuario  = new Usuario();
		carregarUsuarios();
	}
	
	public String visualizar(){
		
		return "visualizar";
		
	}
	
	public String operacao(){
		
		return "resultado";
		
	}
	
	public String novo(){
		
		return "usuario";
		//return "usuario?faces-redirect=true";
	}
	
	public String login(){
		
		String user = email;// FacesUtils.getActionAttribute(event, "email");
		String senh = senha;//FacesUtils.getActionAttribute(event, "senha");
		int flag=0;
		
		if ( email.length() > 0 && senha.length() > 0){
			
			EntityManager em = JPAUtil.getEntityManager();
			UsuarioDAO dao = new UsuarioDAO(em);
			lUsuario = dao.Login(user,senh);
			
			if ( lUsuario.isEmpty() )flag=0;
			else usuario  = dao.consultar(lUsuario.get(0).getIdusuario());
			
			em.close();
			
			if ( lUsuario.isEmpty() )flag=0;
			else flag=lUsuario.size();
		
		
		}
		
		if (flag==0){
			return "errologin";
		} 
		else {
			return "principal";
		}
		
	}
}
