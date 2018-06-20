package br.com.projetofinaljpw.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/*@author Andrade 19/11/2017*/
public class JSFUtil {
    public static void mensagenSucesso(String mensagem){
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMessage);        
    }
    
    public static void mensagenErro(String mensagem){
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMessage);        
    }
}
