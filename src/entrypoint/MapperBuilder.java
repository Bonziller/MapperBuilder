package entrypoint;
import java.util.List;

import enums.JavaVersion;
import exception.ClassNullException;
import register.ClassRegister;

public class MapperBuilder {

	private JavaVersion javaVersion = JavaVersion.JAVA_1_7;

	public MapperBuilder() {
		System.out.println("Mapper Inizialized with default Version: " + this.javaVersion.getVersionName());
	}

	public MapperBuilder(JavaVersion javaVersion) {
		this.javaVersion = javaVersion;
		System.out.println("Mapper Inizialized with Version: " + javaVersion.getVersionName());
	}

	public ClassRegister registerClass(Class<?> EntityClass) throws ClassNullException {
		return new ClassRegister(EntityClass);
	}

	public ClassRegister registerClasses(Class<?>... classes) throws ClassNullException {
		return new ClassRegister(classes);
	}

	public ClassRegister registerClasses(List<Class<?>> classes) throws ClassNullException {
		return new ClassRegister(classes);
	}

}
