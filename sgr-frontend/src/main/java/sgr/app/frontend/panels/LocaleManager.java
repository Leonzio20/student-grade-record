package sgr.app.frontend.panels;

import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

/**
 * @author leonzio
 */
@Controller
public class LocaleManager implements Serializable
{

	private static final long serialVersionUID = 7660987543766456301L;

	private Locale locale;

	@PostConstruct
	public void init()
	{
		locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
	}

	public Locale getLocale()
	{
		return locale;
	}

	public String getLanguage()
	{
		return locale.getLanguage();
	}

	public void setLanguage(String language)
	{
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

}
