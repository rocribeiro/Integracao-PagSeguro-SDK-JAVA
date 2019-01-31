package com.teste.api.pagseguro.testes.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.session.CreatedSession;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

@Controller
@RequestMapping("/session")
public class SessionController {
	
	@GetMapping("/prod")
	public void SesssionIdProd(HttpServletResponse res) {
		String sellerEmail = "rocribeiro@uolinc.com";
	    String sellerToken = "C472362591CF4BCE850597172A6BA1E0";
	    final PagSeguro pagSeguro = PagSeguro
	            .instance(new SimpleLoggerFactory(), new JSEHttpClient(), Credential.sellerCredential(sellerEmail,
	                sellerToken), PagSeguroEnv.PRODUCTION);
	        try {
	          // Criacao de sessao de seller
	          CreatedSession createdSession = pagSeguro.sessions().create();
	          
	          System.out.println(createdSession.getId());
	          PrintWriter out = res.getWriter();
	          out.print(createdSession.getId());
	          out.close();
	          
	        }catch (Exception e){
	          e.printStackTrace();
	        }		
	}
	@GetMapping("/sand")
	public void SesssionIdSand(HttpServletResponse res) {
		String sellerEmail = "rocribeiro@uolinc.com";
	    String sellerToken = "A72E5888819B44B5AFB11A43C58A97E7";
	    final PagSeguro pagSeguro = PagSeguro
	            .instance(new SimpleLoggerFactory(), new JSEHttpClient(), Credential.sellerCredential(sellerEmail,
	                sellerToken), PagSeguroEnv.SANDBOX);
	        try {
	          // Criacao de sessao de seller
	          CreatedSession createdSession = pagSeguro.sessions().create();
	          System.out.println(createdSession.getId());
	          
	          PrintWriter out = res.getWriter();
	          out.print(createdSession.getId());
	          out.close();
	          
	        }catch (Exception e){
	          e.printStackTrace();
	        }
		
	}
}
