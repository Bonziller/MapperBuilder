package helper;
import java.util.List;

import enums.DaoMethodName;
import enums.JavaVersion;

public class StaticHelper {

	private Class<?> entityClass;
	private List<Class<?>> entityClassList;
	private List<Class<?>> dtoClassList;
	private JavaVersion javaVersion;
	private Boolean getter = null;
	private Boolean setter = null;
	private DaoMethodName daoMethodName;
	private static StaticHelper instance;

	public static StaticHelper getInstance() {
		if (instance == null)
			instance = new StaticHelper();
		return instance;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	public List<Class<?>> getEntityClassList() {
		return entityClassList;
	}

	public void setEntityClassList(List<Class<?>> entityClassList) {
		this.entityClassList = entityClassList;
	}

	public JavaVersion getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(JavaVersion javaVersion) {
		this.javaVersion = javaVersion;
	}

	public Boolean isGetter() {
		return getter;
	}

	public void setGetter(Boolean getter) {
		this.getter = getter;
	}

	public Boolean isSetter() {
		return setter;
	}

	public void setSetter(Boolean setter) {
		this.setter = setter;
	}

	public DaoMethodName getDaoMethodName() {
		return daoMethodName;
	}

	public void setDaoMethodName(DaoMethodName daoMethodName) {
		this.daoMethodName = daoMethodName;
	}
	
	public List<Class<?>> getDtoClassList() {
		return dtoClassList;
	}

	public void setDtoClassList(List<Class<?>> dtoClassList) {
		this.dtoClassList = dtoClassList;
	}

	public boolean isMapperAndDto() {
		return Boolean.logicalAnd(getDaoMethodName() == null,
				Boolean.logicalOr(this.entityClass != null, this.entityClassList != null));
	}

	public boolean isMapperAndDtoAndDao() {
		return Boolean.logicalAnd(getDaoMethodName() != null,
				Boolean.logicalOr(this.entityClass != null, this.entityClassList != null));
	}

}
