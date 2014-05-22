package model.dao;



import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.bean.Turma;


@SuppressWarnings("unchecked")
public class TurmaDAO {

	private EntityManager entityManager;
	
	public TurmaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Turma turma){
		entityManager.persist(turma);
	}
	public void alterar(Turma turma){
		entityManager.merge(turma);
	}
	public void excluir(Turma turma){
		entityManager.remove(entityManager.merge(turma));
	}
	
	public Turma consultar(Long id){
		return entityManager.getReference(Turma.class, id);
	}
	
	public List<Turma> listar(){
		String jpql = "Select p from Turma p order by dataCadastro";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
}

