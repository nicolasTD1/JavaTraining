package com.talos.javatraining.lesson9.commands.impl;

import com.talos.javatraining.lesson9.commands.AppCommand;
import com.talos.javatraining.lesson9.events.EventBus;
import com.talos.javatraining.lesson9.events.EventType;


public class CommandTemplate implements AppCommand
{

	private String[] args;
	private EventBus eventBus;
	private EventType event;

	public CommandTemplate(EventBus eventBus,EventType event,  String... args)
	{
		this.args = args;
		this.eventBus = eventBus;
		this.event = event;
	}

	@Override
	public void execute()
	{
		eventBus.notify(event, args);
	}

	//public abstract EventType getEvent();
}
