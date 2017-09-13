package creator;
import enums.DaoMethodName;
import executor.BuildExecutor;
import helper.StaticHelper;

public class DAOCreator {

	private DaoMethodName daoMethodName = null;

	public DAOCreator(DaoMethodName daoMethodNames) {
		this.daoMethodName = daoMethodNames;
		init();
		System.out.printf("DAO Creator Initialized with: %s%n", daoMethodNames.getMethodName());
	}
	
	private void init(){
		StaticHelper.getInstance().setDaoMethodName(this.daoMethodName);
	}
	
	public void build(){
		new BuildExecutor();
	}

}
