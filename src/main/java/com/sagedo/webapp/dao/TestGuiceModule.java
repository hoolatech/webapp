package com.sagedo.webapp.dao;

import io.datarouter.client.mysql.field.codec.factory.MysqlFieldCodecFactory;
import io.datarouter.client.mysql.field.codec.factory.StandardMysqlFieldCodecFactory;
import io.datarouter.inject.guice.BaseModule;
import io.datarouter.storage.config.DatarouterProperties;
import io.datarouter.storage.config.SimpleDatarouterProperties;
import io.datarouter.storage.config.guice.DatarouterStorageGuiceModule;
import io.datarouter.storage.router.RouterClasses;

public class TestGuiceModule extends BaseModule{

	@Override
	protected void configure(){
		// install the bindings of datarouter-storage
		install(new DatarouterStorageGuiceModule());
		// bind the standard codec factory - you can create your own if you want to define your own field types
		bindOptional(MysqlFieldCodecFactory.class).setDefault().to(StandardMysqlFieldCodecFactory.class);
		// datarouter will use the application's name to look for configuration files
		bind(DatarouterProperties.class).toInstance(new SimpleDatarouterProperties("testApp"));
		// we register all the routers of our application here
		bind(RouterClasses.class).toInstance(new RouterClasses(TestRouter.class));
	}

}