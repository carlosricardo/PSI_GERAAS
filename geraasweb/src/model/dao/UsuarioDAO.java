
package model.dao;


	
	import java.util.List;
	import javax.persistence.EntityManager;
	import javax.persistence.Query;
import model.bean.Usuario;

	@SuppressWarnings("unchecked")
	public class UsuarioDAO {

		private EntityManager entityManager;
		
		public UsuarioDAO(EntityManager entityManager) {
			this.entityManager = entityManager;
		}
		public void cadastrar(Usuario usuario){
			entityManager.persist(usuario);
		}
		public void alterar(Usuario usuario){
			entityManager.merge(usuario);
		}
		public void excluir(Usuario usuario){
			entityManager.remove(entityManager.merge(usuario));
		}
		
		public Usuario consultar(Long id){
			return entityManager.getReference(Usuario.class, id);
		}
		
		public List<Usuario> listar(){
			String jpql = "Select p from Usuario p order by dataCadastro desc";
			Query query = entityManager.createQuery(jpql);
			return query.getResultList();
		}
		
		public Usuario validaLogin (String login , String senha){
			String jpql= "Select p from Usuario p Where p.email = :user and p.senha = :senha";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("user",login);
			query.setParameter("senha",senha);
			if (query.getSingleResult().equals(null)) return null;
			else return (Usuario) query.getSingleResult();	
		}
				
		public List<Usuario> Login (String user, String senha){
	        String sql;
	        sql= "Select p from Usuario p Where p.email = :user and p.senha = :senha";
	        Query query = entityManager.createQuery(sql);
	        query.setParameter("user",user);
			query.setParameter("senha",senha);
	        return query.getResultList();

	        }
	}


