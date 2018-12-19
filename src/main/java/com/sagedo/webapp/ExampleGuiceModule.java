package com.sagedo.webapp;

import com.google.inject.servlet.ServletModule;

import io.datarouter.storage.config.DatarouterProperties;
import io.datarouter.storage.config.SimpleDatarouterProperties;
import io.datarouter.web.config.DatarouterWebGuiceModule;

public class ExampleGuiceModule extends ServletModule{

	public static final String SERVICE_NAME = "example";

	@Override
	protected void configureServlets(){
		install(new DatarouterWebGuiceModule());
		bind(DatarouterProperties.class).toInstance(new SimpleDatarouterProperties(SERVICE_NAME));
		serve("/*").with(ExampleDispatcherServlet.class);
	}

}