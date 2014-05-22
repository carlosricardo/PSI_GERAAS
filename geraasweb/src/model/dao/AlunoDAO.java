package model.dao;



import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.bean.Aluno;


@SuppressWarnings("unchecked")
public class AlunoDAO {

	private EntityManager entityManager;
	
	public AlunoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Aluno aluno){
		entityManager.persist(aluno);
	}
	public void alterar(Aluno aluno){
		entityManager.merge(aluno);
	}
	public void excluir(Aluno aluno){
		entityManager.remove(entityManager.merge(aluno));
	}
	
	public Aluno consultar(Long id){
		return entityManager.getReference(Aluno.class, id);
	}
	
	public List<Aluno> listar(){
		String jpql = "Select p from Aluno p order by dataCadastro";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
}

