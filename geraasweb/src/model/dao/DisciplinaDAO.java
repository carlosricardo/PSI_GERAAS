package model.dao;



import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.bean.Disciplina;


@SuppressWarnings("unchecked")
public class DisciplinaDAO {

	private EntityManager entityManager;
	
	public DisciplinaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Disciplina disciplina){
		entityManager.persist(disciplina);
	}
	public void alterar(Disciplina disciplina){
		entityManager.merge(disciplina);
	}
	public void excluir(Disciplina disciplina){
		entityManager.remove(entityManager.merge(disciplina));
	}
	
	public Disciplina consultar(Long id){
		return entityManager.getReference(Disciplina.class, id);
	}
	
	public List<Disciplina> listar(){
		String jpql = "Select p from Disciplina p where usuario = 1 order by descricao";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
}

