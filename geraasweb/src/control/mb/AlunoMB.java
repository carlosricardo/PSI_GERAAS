package control.mb;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import model.bean.Aluno;
import model.dao.AlunoDAO;
import model.dao.JPAUtil;

@RequestScoped
@ManagedBean

public class AlunoMB {


	private Aluno aluno = new Aluno();
	
	public Aluno getAluno() {
		return aluno;
	}
	

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	//Atributo que guarda a colecao Alunos armazenados em BD
	public List<Aluno> listaAlunos = new ArrayList<Aluno>();
	
	public List<Aluno> getListaAlunos() {
		return listaAlunos;
	}	

	//Metodo invocado apos a classe ser carregada pelo servidor
	@PostConstruct
	public void carregarAlunos(){
		//UsuarioMB usuario = new UsuarioMB();
		
		EntityManager em = JPAUtil.getEntityManager();
		AlunoDAO dao = new AlunoDAO(em);
		
		//usuario.login();
		listaAlunos = dao.listar();
		em.close();
	}
		
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		AlunoDAO dao = new AlunoDAO(em);
		em.getTransaction().begin();
		dao.excluir(aluno);
		em.getTransaction().commit();
		em.close();
		carregarAlunos();
	}
	
	
	public void salvar(){		
		EntityManager em = JPAUtil.getEntityManager();
		AlunoDAO dao = new AlunoDAO(em);
		em.getTransaction().begin();
		aluno.setDataCadastro(Calendar.getInstance());	
		if( aluno.getIdaluno()!=null){
			dao.alterar(aluno);
			
		}else{
			aluno.setTurma(1L);
			dao.cadastrar(aluno);
			
		}
		em.getTransaction().commit();
		em.close();
		aluno  = new Aluno();
		carregarAlunos();
		
	}
	
	public String anexarArq(){
		
		return "arquivos";
		//return "usuario?faces-redirect=true";
	}
	
}
