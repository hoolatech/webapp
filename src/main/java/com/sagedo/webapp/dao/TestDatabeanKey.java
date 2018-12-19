package com.sagedo.webapp.dao;

import java.util.Arrays;
import java.util.List;

import io.datarouter.model.field.Field;
import io.datarouter.model.field.imp.StringField;
import io.datarouter.model.field.imp.StringFieldKey;
import io.datarouter.model.key.primary.BasePrimaryKey;

public class TestDatabeanKey extends BasePrimaryKey<TestDatabeanKey>{

	private String id;

	public static class FieldKeys{
		public static final StringFieldKey id = new StringFieldKey("id");
	}

	public TestDatabeanKey(){
	}

	public TestDatabeanKey(String id){
		this.id = id;
	}

	@Override
	public List<Field<?>> getFields(){ // the list of columns of the PRIMARY KEY
		return Arrays.asList(new StringField(FieldKeys.id, id));
	}

}
