package sgr.app.webapp.loginPanel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import sgr.app.api.account.AccountType;
import sgr.app.frontend.panels.AbstractLoginPanel;

/**
 * @author dawbes
 */
@Controller
public class LoginPanel extends AbstractLoginPanel
{

   private static final long serialVersionUID = -7242960918445825945L;

   @Override
   protected List<AccountType> supportedAccountTypes()
   {
      final List<AccountType> list = new ArrayList<>();
      list.add(AccountType.STUDENT);
      list.add(AccountType.TEACHER);
      return list;
   }

}
