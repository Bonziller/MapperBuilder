package entrypoint;

import enums.DaoMethodName;
import exception.ClassNullException;

public class TestKlasse {

	public TestKlasse() {

	}

	public static void main(String[] args) throws ClassNullException {
		MapperBuilder mapperBuilder = new MapperBuilder();
		mapperBuilder
		.registerClass(TestKlasse.class)
		.createDTOs(null, null)
		.createDAOs(DaoMethodName.CREATE_READ_GETALL_UPDATE_DELETE)
		.build();
	}

}
