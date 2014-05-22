package control.mb;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import model.bean.Evento;
import model.dao.EventoDAO;
import model.dao.JPAUtil;

@RequestScoped
@ManagedBean

public class EventoMB {


	private Evento evento = new Evento();
	
	public Evento getEvento() {
		return evento;
	}
	

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	//Atributo que guarda a colecao Eventos armazenados em BD
	public List<Evento> listaEventos = new ArrayList<Evento>();
	
	public List<Evento> getListaEventos() {
		return listaEventos;
	}	

	//Metodo invocado apos a classe ser carregada pelo servidor
	@PostConstruct
	public void carregarEventos(){
		//UsuarioMB usuario = new UsuarioMB();
		
		EntityManager em = JPAUtil.getEntityManager();
		EventoDAO dao = new EventoDAO(em);
		
		//usuario.login();
		listaEventos = dao.listar();
		em.close();
	}
		
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		EventoDAO dao = new EventoDAO(em);
		em.getTransaction().begin();
		dao.excluir(evento);
		em.getTransaction().commit();
		em.close();
		carregarEventos();
	}
	
	
	public void salvar(){		
		EntityManager em = JPAUtil.getEntityManager();
		EventoDAO dao = new EventoDAO(em);
		em.getTransaction().begin();
		evento.setDataCadastro(Calendar.getInstance());	
		if( evento.getIdevento()!=null){
			dao.alterar(evento);
		}else{
			dao.cadastrar(evento);
		}
		em.getTransaction().commit();
		em.close();
		evento  = new Evento();
		carregarEventos();
		
	}
	

	public String anexarArq(){
		
		return "arquivos";
		//return "usuario?faces-redirect=true";
	}
	
public void notificaArquivo(){
	AlunoMB alunos = new AlunoMB();
	
	alunos.carregarAlunos();
	
	new EnviarEmail("Geraas - Evento da turma",alunos.getListaAlunos());
		
	}
	
	
	
	public String Cadastrar(){
		
		return "evento";
		//return "usuario?faces-redirect=true";
	}
}
