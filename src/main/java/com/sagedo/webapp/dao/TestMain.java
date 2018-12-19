package com.sagedo.webapp.dao;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;

import com.google.inject.Guice;
import com.google.inject.Injector;

import io.datarouter.storage.config.Config;
import io.datarouter.storage.config.PutMethod;
import io.datarouter.util.tuple.Range;

public class TestMain {
	public static void main(String[] args){
		// create the Injector with our test module
		Injector injector = Guice.createInjector(Arrays.asList(new TestGuiceModule()));
		// get an instance of our router with the injector
		TestRouter router = injector.getInstance(TestRouter.class);
		// instantiate a databean
		Integer someInt = ThreadLocalRandom.current().nextInt();
		TestDatabean databean = new TestDatabean("foo", someInt);
		// write the databean to the database, will issue an INSERT ... ON DUPLICATE KEY UPDATE by default
		router.node.put(databean, null);
		// other put behaviors are available with PutMethod, this one will issue an INSERT IGNORE
		router.node.put(databean, new Config().setPutMethod(PutMethod.INSERT_IGNORE));
		// read the databean using the same primary key
		TestDatabean roundTripped = router.node.get(new TestDatabeanKey("foo"), null);
		// check that we were able to read the someInt column
		Assert.assertEquals(roundTripped.getSomeInt(), someInt);
		// databeans are equal if their keys are equal, they also sort by primary key
		Assert.assertEquals(roundTripped, databean);
		// let's put another databean, with a different key
		Integer anotherInt = ThreadLocalRandom.current().nextInt();
		TestDatabean anotherDatabean = new TestDatabean("bar", anotherInt);
		router.node.put(anotherDatabean, null);
		// you can fetch the rows given a range of primary keys, here, we fetch everything
		long sum = router.node.stream(Range.everything(), null) // built-in Java 8 stream
				.mapToInt(TestDatabean::getSomeInt)
				.sum();
		Assert.assertEquals(sum, someInt + anotherInt);
	}
}
