package control.mb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import model.bean.Disciplina;
import model.bean.Usuario;
import model.dao.DisciplinaDAO;
import model.dao.JPAUtil;

@ViewScoped
@ManagedBean

public class DisciplinaMB {


	private Disciplina disciplina = new Disciplina();
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	//Atributo que guarda a colecao Disciplinas armazenados em BD
	public List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
	
	public List<Disciplina> getListaDisciplinas() {
		return listaDisciplinas;
	}	

	//Metodo invocado apos a classe ser carregada pelo servidor
	@PostConstruct
	public void carregarDisciplinas(){
	//	UsuarioMB usuario = new UsuarioMB();
		
		EntityManager em = JPAUtil.getEntityManager();
		DisciplinaDAO dao = new DisciplinaDAO(em);
		
//		usuario.login();
		listaDisciplinas = dao.listar();
		em.close();
	}
		
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		DisciplinaDAO dao = new DisciplinaDAO(em);
		em.getTransaction().begin();
		dao.excluir(disciplina);
		em.getTransaction().commit();
		em.close();
		carregarDisciplinas();
	}
	
	
	public void salvar(){		
		EntityManager em = JPAUtil.getEntityManager();
		DisciplinaDAO dao = new DisciplinaDAO(em);
		Usuario user = new Usuario();
		
		user.setIdusuario(1L);
		
		
		em.getTransaction().begin();
		disciplina.setDatacadastro(Calendar.getInstance());		
		if(disciplina.getIddisciplina()!=null){
			dao.alterar(disciplina);
		}else{
			dao.cadastrar(disciplina);
			disciplina.setUsuario(1L);
			disciplina.setTipopermissao(1);
		}
		em.getTransaction().commit();
		em.close();
		disciplina  = new Disciplina();
		carregarDisciplinas();
	}
	
	public String turma(){
		
		return "turma";
		//return "usuario?faces-redirect=true";
	}
	
	
	public String anexarArq(){
		
		return "arquivos";
		//return "usuario?faces-redirect=true";
	}
	
	
	public String Cadastrar(){
		
		return "disciplina";
		//return "usuario?faces-redirect=true";
	}
}