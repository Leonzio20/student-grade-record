package sgr.app.webapp.loginPanel;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.login.LoginService;
import sgr.app.frontend.Bean;

/**
 * @author dawbes
 */
@Controller
public class LoginPanel implements Serializable
{
   private static final long serialVersionUID = -7242960918445825945L;

   @Autowired
   public LoginService loginService;

   public <T> void checkLogin() throws IOException
   {
      final InputText loginField = Bean.get("loginForm", "loginInput");
      final Password passwordField = Bean.get("loginForm", "passwordInput");

      final Optional<T> existUser = loginService.checkLogin(loginField.getValue().toString(),
            passwordField.getValue().toString());

      if (existUser.isPresent())
      {
         final ExternalContext externalContext = FacesContext.getCurrentInstance()
               .getExternalContext();
         externalContext.redirect(externalContext.getRequestContextPath() + "/app/index.jsf");
      }
      else
      {
         final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd",
               "Niepoprawny login lub hasło");
         RequestContext.getCurrentInstance().showMessageInDialog(message);
      }
   }

   public void logout() throws IOException
   {
      final ExternalContext externalContext = FacesContext.getCurrentInstance()
            .getExternalContext();
      externalContext.redirect(externalContext.getRequestContextPath());
   }
}
