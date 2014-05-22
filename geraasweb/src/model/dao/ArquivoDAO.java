package model.dao;



import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.bean.Arquivo;


@SuppressWarnings("unchecked")
public class ArquivoDAO {

	private EntityManager entityManager;
	
	public ArquivoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Arquivo arquivo){
		entityManager.persist(arquivo);
	}
	public void alterar(Arquivo arquivo){
		entityManager.merge(arquivo);
	}
	public void excluir(Arquivo arquivo){
		entityManager.remove(entityManager.merge(arquivo));
	}
	
	public Arquivo consultar(Long id){
		return entityManager.getReference(Arquivo.class, id);
	}
	
	public List<Arquivo> listar(){
		String jpql = "Select p from Arquivo p order by dataCadastro";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
}

