package control.mb;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import sun.text.normalizer.ReplaceableString;
	

	/**
	 * Servlet to handle File upload request from Client
	 * @author Javin Paul
	 */
	public class FileUploadHandler extends HttpServlet {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String UPLOAD_DIRECTORY = "C:/java/desenvolvimento/geraasweb/WebContent/GERAAS";
	  
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	
	        String nomeArquivo = null;
	        
	        //process only if its multipart content
	        if(ServletFileUpload.isMultipartContent(request)){
	            try {
	                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	              
	                for(FileItem item : multiparts){
	                    if(!item.isFormField()){
	                        String name = new File(item.getName()).getName();
	                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
	                        nomeArquivo = UPLOAD_DIRECTORY + "/" + name;
	                        
	                    }
	                }
	               //File uploaded successfully
	                
	               request.setAttribute("message",nomeArquivo);
	            } catch (Exception ex) {
	               request.setAttribute("message", "Upload do arquivo com falha " + ex);
	            }          
	         
	        }else{
	            request.setAttribute("message","Desculpe este Servlet só lida com pedido de upload de arquivos");
	        }
	    
	        request.getRequestDispatcher("/arquivos.xhtml").forward(request, response);
	     
	    }
	  
	}


