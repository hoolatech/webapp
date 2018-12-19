package com.sagedo.webapp.dao;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.sagedo.webapp.dao.TestDatabean.TestDatabeanFielder;

import io.datarouter.storage.Datarouter;
import io.datarouter.storage.client.ClientId;
import io.datarouter.storage.config.DatarouterProperties;
import io.datarouter.storage.config.setting.DatarouterSettings;
import io.datarouter.storage.node.factory.NodeFactory;
import io.datarouter.storage.node.op.combo.SortedMapStorage;
import io.datarouter.storage.router.BaseRouter;

@Singleton
public class TestRouter extends BaseRouter{

	private static final ClientId MYSQL_CLIENT = new ClientId("mysqlClient", true);

	public final SortedMapStorage<TestDatabeanKey,TestDatabean> node;

	@Inject
	public TestRouter(Datarouter datarouter, DatarouterProperties datarouterProperties, NodeFactory nodeFactory,
			DatarouterSettings datarouterSettings){
		super(datarouter, datarouterProperties, "test", nodeFactory, datarouterSettings);

		node = createAndRegister(MYSQL_CLIENT, TestDatabean::new, TestDatabeanFielder::new);
	}

}