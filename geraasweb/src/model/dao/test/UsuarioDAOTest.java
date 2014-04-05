package model.dao.test;

/*import static org.junit.Assert.*;
import junit.framework.Assert;
import org.junit.Test;*/

import java.util.Calendar;
import javax.persistence.EntityManager;

//import com.sun.tracing.dtrace.ArgsAttributes;


import model.bean.Usuario;
import model.dao.UsuarioDAO;
import model.dao.JPAUtil;



public class UsuarioDAOTest {	

	public static void main ( String[] args) {
		// Solicitacao de conexao ao SGBD
				EntityManager entityManager = JPAUtil.getEntityManager();
				UsuarioDAO dao = new UsuarioDAO(entityManager);
				// Inicio da transacao
				entityManager.getTransaction().begin();
				// Criacao de um novo produto
				Usuario usuario = new Usuario();
				usuario.setNome("carlos");
				usuario.setEmail("ssssssss");
				usuario.setDataCadastro(Calendar.getInstance());
				usuario.setConfirmasenha("123");
				usuario.setSenha("123");
				usuario.setEmailconfirmacao("aaaaaaa");
				//Execucao do cadastro
				dao.cadastrar(usuario);
				//Fechamento da conexao
				entityManager.getTransaction().commit();
				entityManager.close();
	}
}
