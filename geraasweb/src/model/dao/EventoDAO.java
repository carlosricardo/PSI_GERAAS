package model.dao;



import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.bean.Evento;


@SuppressWarnings("unchecked")
public class EventoDAO {

	private EntityManager entityManager;
	
	public EventoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Evento evento){
		entityManager.persist(evento);
	}
	public void alterar(Evento evento){
		entityManager.merge(evento);
	}
	public void excluir(Evento evento){
		entityManager.remove(entityManager.merge(evento));
	}
	
	public Evento consultar(Long id){
		return entityManager.getReference(Evento.class, id);
	}
	
	public List<Evento> listar(){
		String jpql = "Select p from Evento p order by dataCadastro";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
}
