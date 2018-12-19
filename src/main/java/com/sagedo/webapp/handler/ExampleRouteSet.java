package com.sagedo.webapp.handler;

import io.datarouter.web.dispatcher.BaseRouteSet;

public class ExampleRouteSet extends BaseRouteSet{

	public ExampleRouteSet(){
		super(""); // this route set will match /context-path

		handleOthers(HelloWorldHandler.class); // all requests will go to this handler
	}

}
