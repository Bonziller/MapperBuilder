package executor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import creator.FileCreator;
import enums.BuildOrder;
import exception.ClassNullException;
import helper.StaticHelper;
import logger.LifeCycleLogger;

public class BuildExecutor {

	private BuildOrder buildOrder;

	public BuildExecutor() {
		if (StaticHelper.getInstance().isMapperAndDto())
			this.buildOrder = BuildOrder.MAPPER_AND_DTOS;
		else if (StaticHelper.getInstance().isMapperAndDtoAndDao())
			this.buildOrder = BuildOrder.MAPPER_AND_DTOS_AND_DAOS;
		else
			this.buildOrder = BuildOrder.MAPPER_AND_DTOS;
		System.out.printf("BuildExecutorr Initialized with Build Order: %s", this.buildOrder.getBuildOrder());
		executeBuild();
	}

	public BuildExecutor(BuildOrder buildOrder) {
		this.buildOrder = buildOrder;
		System.out.printf("BuildExecutor Initialized with Build Order: %s", this.buildOrder.getBuildOrder());
		executeBuild();
	}

	private void executeBuild() {
		System.out.println("Execution Initialized...");
		if (this.buildOrder.isMapperAndDtos() || this.buildOrder.isMapperAndDtosAndDaos()) {
			createDTOFile();
			try {
				createMapperFile();
			} catch (IOException | ClassNullException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (this.buildOrder.isMapperAndDtosAndDaos()) {
			createDAOFile();
		}
		System.out.println("All Files are Ready to Use now");
	}

	private void createMapperFile() throws IOException, ClassNullException {
		LifeCycleLogger lifeCycleLogger;
		List<Class<?>> entities = new ArrayList<>();
		if (StaticHelper.getInstance().getEntityClass() != null) {
			Class<?> entity = StaticHelper.getInstance().getEntityClass();
			lifeCycleLogger = new LifeCycleLogger(1, 1, "Create " + entity.getSimpleName() + "Mapper");
			lifeCycleLogger.logCurrent();
			new FileCreator(entity).createMapper();
		} else if (StaticHelper.getInstance().getEntityClassList() != null) {
			entities = StaticHelper.getInstance().getEntityClassList();
			lifeCycleLogger = new LifeCycleLogger(1, entities.size(), "Create Mapper");
			if (!entities.isEmpty()) {
				for (Class<?> class1 : entities) {
					lifeCycleLogger.setStep("Create " + class1.getSimpleName() + "Mapper");
					lifeCycleLogger.logCurrent();
					new FileCreator(class1).createMapper();
					lifeCycleLogger.addCurrentVal();
				}
			}
		}
	}

	private void createDTOFile() {

	}

	private void createDAOFile() {

	}

}
