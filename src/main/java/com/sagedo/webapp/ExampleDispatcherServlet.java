package com.sagedo.webapp;

import com.sagedo.webapp.handler.ExampleRouteSet;

import io.datarouter.web.dispatcher.DispatcherServlet;

public class ExampleDispatcherServlet  extends DispatcherServlet{

	@Override
	protected void registerRouteSets(){
		register(new ExampleRouteSet());
	}

}