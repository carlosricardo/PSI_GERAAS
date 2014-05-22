package control.mb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import model.bean.Arquivo;
import model.dao.ArquivoDAO;
import model.dao.JPAUtil;
import control.mb.AlunoMB;
@ViewScoped
@ManagedBean

public class ArquivoMB  {

	private Arquivo arquivo = new Arquivo();
	
	public Arquivo getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	//Atributo que guarda a colecao Arquivos armazenados em BD
	public List<Arquivo> listaArquivos = new ArrayList<Arquivo>();
	
	public List<Arquivo> getListaArquivos() {
		return listaArquivos;
	}	

	//Metodo invocado apos a classe ser carregada pelo servidor
	@PostConstruct
	public void carregarArquivos(){
		EntityManager em = JPAUtil.getEntityManager();
		ArquivoDAO dao = new ArquivoDAO(em);
		listaArquivos = dao.listar();
		em.close();
	}
	
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		ArquivoDAO dao = new ArquivoDAO(em);
		em.getTransaction().begin();
		dao.excluir(arquivo);
		em.getTransaction().commit();
		em.close();
		carregarArquivos();
	}
	
	public void salvar( ){		
		EntityManager em = JPAUtil.getEntityManager();
		ArquivoDAO dao = new ArquivoDAO(em);
		em.getTransaction().begin();
		
		arquivo.setDataCadastro(Calendar.getInstance());
		
		if(arquivo.getIdarquivo()!=null){
			dao.alterar(arquivo);
		}else{
			arquivo.setDisciplina(1L);
			dao.cadastrar(arquivo);			
		}
		em.getTransaction().commit();
		em.close();
		arquivo  = new Arquivo();
		carregarArquivos();
		
	}
	
	public void notificaArquivo(){
		
		AlunoMB alunos = new AlunoMB();
		
		alunos.carregarAlunos();
		
		
		new EnviarEmail("Geraas - Arquivo disponivel da disciplina",alunos.getListaAlunos());
		
	}
	
	
}
