package control.mb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import model.bean.Turma;
import model.dao.TurmaDAO;
import model.dao.JPAUtil;

@ViewScoped
@ManagedBean

public class TurmaMB  {

	private Turma turma = new Turma();
	
	public Turma getTurma() {
		return turma;
	}
	
	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	//Atributo que guarda a colecao Turmas armazenados em BD
	public List<Turma> listaTurmas = new ArrayList<Turma>();
	
	public List<Turma> getListaTurmas() {
		return listaTurmas;
	}	

	//Metodo invocado apos a classe ser carregada pelo servidor
	@PostConstruct
	public void carregarTurmas(){
		EntityManager em = JPAUtil.getEntityManager();
		TurmaDAO dao = new TurmaDAO(em);
		listaTurmas = dao.listar();
		em.close();
	}
	
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		TurmaDAO dao = new TurmaDAO(em);
		em.getTransaction().begin();
		dao.excluir(turma);
		em.getTransaction().commit();
		em.close();
		carregarTurmas();
	}
	
	public void salvar( ){		
		EntityManager em = JPAUtil.getEntityManager();
		TurmaDAO dao = new TurmaDAO(em);
		em.getTransaction().begin();
		
		turma.setDataCadastro(Calendar.getInstance());
		
		if(turma.getIdturma()!=null){
			dao.alterar(turma);
		}else{
			turma.setDisciplina(1L);
			dao.cadastrar(turma);			
		}
		em.getTransaction().commit();
		em.close();
		turma  = new Turma();
		carregarTurmas();
	}
	

	public String eventos(){
		
		return "evento";
		//return "usuario?faces-redirect=true";
	}
	
	public String alunos(){
		
		return "aluno";
		//return "usuario?faces-redirect=true";
	}

}
