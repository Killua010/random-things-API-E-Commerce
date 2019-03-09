package br.com.randomthings.command;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.randomthings.facade.IFacade;

public abstract class AbstractCommand implements ICommand {
	
	@Autowired
	protected IFacade facade;
}
