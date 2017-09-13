package creator;
import enums.BuildOrder;
import enums.DaoMethodName;
import executor.BuildExecutor;
import helper.StaticHelper;

public class DTOCreator {

	private Boolean getter = null;
	private Boolean setter = null;

	public DTOCreator(Boolean getter, Boolean setter) {
		super();
		this.getter = getter;
		this.setter = setter;
		init();
		System.out.printf("Initialized DTO Creator with Getter: %s and Setter: %s%n", getter != null ? Boolean.toString(getter) : "Null",
				setter != null ? Boolean.toString(setter) : "Null");
	}
	
	public DTOCreator() {
		super();
		System.out.println("DTO's vom Programmierer erhalten. Es werden keine neuen angelegt.");
	}

	private void init(){
		StaticHelper.getInstance().setGetter(this.getter);
		StaticHelper.getInstance().setSetter(this.setter);
	}
	
	public DAOCreator createDAOs(DaoMethodName daoMethodName){
		return new DAOCreator(daoMethodName);
	}
	
	public void build(){
		new BuildExecutor(BuildOrder.MAPPER_AND_DTOS);
	}

}
