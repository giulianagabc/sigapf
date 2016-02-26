package br.fatec.sigapf.framework.utils;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;

import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.sigapf.framework.context.AuthenticationContext;

@SessionScoped
@Service(value = "sessionBean")
public class SessionBean implements Serializable {

	private static final long serialVersionUID = -758220012612751178L;

	@Autowired
	private AuthenticationContext authenticationContext;

	private int menuIndex = 0;

	public void menuTabChange(TabChangeEvent event) {
		Tab tab = event.getTab();
		AccordionPanel accordionPanel = (AccordionPanel) tab.getParent();
		setMenuIndex(accordionPanel.getChildren().indexOf(tab));
	}

	public int getMenuIndex() {
		return menuIndex;
	}

	public void setMenuIndex(int menuIndex) {
		this.menuIndex = menuIndex;
	}

}