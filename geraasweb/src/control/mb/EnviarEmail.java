package control.mb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Aluno;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EnviarEmail extends Thread {
	private String msg;
	private EnviarMensagemParaDevice notifiDevice;
	private List<Aluno> alunos;

	public EnviarEmail(String menssagem,List<Aluno> alunos){
		this.msg = menssagem;
		this.alunos = new ArrayList<Aluno>();
		this.alunos = alunos;
		//	this.notifiDevice = new EnviarMensagemParaDevice();
		start();
	}



	//public void initEmail(){
	////notifiDevice = new EnviarMensagemParaDevice();
	//		System.out.println("######ENTRO1########");
	//		//Thread thead = new Thread();
	//thead.run();
	//start();
	//}


	@Override
	public void run() {
		System.out.println("######ENTRO1.1########");
		try {
			EnviarEmail();
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

	public void EnviarEmail() throws EmailException{

		if( this.alunos!=null){
			System.out.println("######ENTRO2########"+alunos.size());	
			for (Aluno aluno:alunos){
				SimpleEmail email = new SimpleEmail();
				email.setHostName("smtp.gmail.com" ); // o servidor SMTP para envio do e-mail email.addTo("jdoe@somewhere.org", "John Doe"); //destinatário 
				email.setSslSmtpPort( "465" );
				email.setStartTLSRequired(true);
				email.setSSLOnConnect(true);
				email.addTo(aluno.getEmail(), aluno.getNome());//destinatário
				email.setFrom("geraasnotificacao@gmail.com", "Me"); // remetente 
				email.setSubject("Geraas Notificacao"); // assunto do e-mail 		  
				email.setMsg(this.msg); //conteudo do e-mail 
				email.setAuthentication("geraasnotificacao@gmail.com", "geraasfucapi");  
				//email.setSmtpPort(465);  
				email.send(); //envia o e-mail

				try {
					notifiDevice.EnviarMsgDevice();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
