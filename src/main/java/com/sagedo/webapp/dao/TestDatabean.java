package com.sagedo.webapp.dao;

import java.util.Arrays;
import java.util.List;

import io.datarouter.model.databean.BaseDatabean;
import io.datarouter.model.field.Field;
import io.datarouter.model.field.imp.comparable.IntegerField;
import io.datarouter.model.field.imp.comparable.IntegerFieldKey;
import io.datarouter.model.serialize.fielder.BaseDatabeanFielder;

public class TestDatabean extends BaseDatabean<TestDatabeanKey,TestDatabean>{

	private TestDatabeanKey key;
	private Integer someInt;

	private static class FieldKeys{
		private static final IntegerFieldKey someInt = new IntegerFieldKey("someInt");
	}

	public TestDatabean(){
		this.key = new TestDatabeanKey(); // it is required to initialize the key field of a databean
	}

	public TestDatabean(String id, Integer someInt){
		this.key = new TestDatabeanKey(id);
		this.someInt = someInt;
	}

	public static class TestDatabeanFielder extends BaseDatabeanFielder<TestDatabeanKey,TestDatabean>{

		protected TestDatabeanFielder(){
			super(TestDatabeanKey.class);
		}

		@Override
		public List<Field<?>> getNonKeyFields(TestDatabean databean){
			return Arrays.asList(
					new IntegerField(FieldKeys.someInt, databean.someInt));
		}

	}

	@Override
	public Class<TestDatabeanKey> getKeyClass(){
		return TestDatabeanKey.class;
	}

	@Override
	public TestDatabeanKey getKey(){
		return key;
	}

	public Integer getSomeInt(){
		return someInt;
	}

}