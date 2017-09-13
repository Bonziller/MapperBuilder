package register;
import java.util.Arrays;
import java.util.List;

import creator.DTOCreator;
import exception.ClassNullException;
import helper.StaticHelper;

public class ClassRegister {

	private Class<?> entityClass;
	private List<Class<?>> entityClassList;

	public ClassRegister(Class<?> entityClass) throws ClassNullException {
		if (entityClass == null)
			throw new ClassNullException();
		this.entityClass = entityClass;
		init();
	}

	public ClassRegister(Class<?>[] entityClasses) throws ClassNullException {
		if (entityClasses != null && entityClasses.length > 0) {
			Arrays.sort(entityClasses);
			this.entityClassList = Arrays.asList(entityClasses);
			init();
		} else {
			throw new ClassNullException();
		}
	}

	public ClassRegister(List<Class<?>> entityClasses) throws ClassNullException {
		if (entityClasses == null || entityClasses.isEmpty())
			throw new ClassNullException();
		this.entityClassList = entityClasses;
		init();
	}
	
	private void init(){
		StaticHelper.getInstance().setEntityClass(this.entityClass);
		StaticHelper.getInstance().setEntityClassList(this.entityClassList);
	}

	public DTOCreator createDTOs(Boolean getter, Boolean setter) {
		return new DTOCreator(getter, setter);
	}
	
	public DTOCreator withDTOs(List<Class<?>> dtoClassList) throws ClassNullException{
		if(dtoClassList == null || dtoClassList.isEmpty())
			throw new ClassNullException();
		StaticHelper.getInstance().setDtoClassList(dtoClassList);
		return new DTOCreator();
	}

}
