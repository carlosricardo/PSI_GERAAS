package control.mb;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class EnviarMensagemParaDevice {

	//Chave de registro do device. Resultado da chamada GCMRegistrar.register(...);
//	private static final String DEVICE_REGISTRATION_ID = "APA91bH6C7Rotz_0wdORv_Hd-f6DNvNA2GPlAvHffd_jquYRVxYYR0UwRFVY-rGoMosx6fHajSrc0-SUH54HKe2KVyVeX8QLkaKvKflgO2EjMr2WDXwX8_"+
//			"VCX1DNe7pVJ4hO0X8GFbMda6s2WAIj6UOzLCOgN5Qvgg";
	
//	private static final String DEVICE_REGISTRATION_ID = "APA91bHXQ_SwKTO7OKEtN0LENWB2-_aeTWeqHrEa0GkIEZJex_ka5tRCbqDTsVnZekpP17BxpqFW-uIM_1eHFFGj3zZwN6vZlbbM55UeWSVT29OEXz-"+
//			"B1pqN4GS-4z5mIWjg1dod4TyBfkWrxLMG5kEq-C1xMLt-tQrASs5yn8lTOixEFsJUJxc";
	
	private static final String ID_DEVICE_CARLOS = "APA91bHp-JSIydVDS5ntuOc7YP5POKDe3xdWfHW7VCCskPEwlpiYy64XnG60XuajWiyBP1EPEfr-F0NHuJ7p7C0ATrWdWjUEhrm51RrrBz8BpY_nD4ODD0md" +
			"N9SIbTUAXikREqtRVsTp5nhDT2B8P8OCxJnPGGyEOL7yZeowN1svmL_1lCM5HT0";
	
	//chave criada no console
	private static final String API_KEY = "AIzaSyB28xb-mEPqjrjnuczaIXene5pyoE9gBkQ";
	
	public static void EnviarMsgDevice() throws IOException {
		Sender sender =  new Sender(API_KEY);
		Message message =  new Message.Builder().addData("msg","Ola Aluno um novo arquivo esta disponivel para voce no GERRAS").build();
		Result result = sender.send(message,ID_DEVICE_CARLOS,5);
		System.out.println(result);
	}
		
}
