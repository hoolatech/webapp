package com.sagedo.webapp.handler;

import io.datarouter.web.handler.BaseHandler;

public class HelloWorldHandler extends BaseHandler {
	@Handler(defaultHandler = true)
	public String index(){
		return "Hello World";
	}
}
	