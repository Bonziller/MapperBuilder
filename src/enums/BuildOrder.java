package enums;

public enum BuildOrder {
	MAPPER_AND_DTOS("MAPPER_AND_DTOS"), MAPPER_AND_DTOS_AND_DAOS("MAPPER_AND_DTOS_AND_DAOS");

	private String buildOrder;

	BuildOrder(String buildOrder) {
		this.buildOrder = buildOrder;
	}

	public String getBuildOrder() {
		return this.buildOrder;
	}

	public boolean isMapperAndDtos() {
		return this.buildOrder == BuildOrder.MAPPER_AND_DTOS.getBuildOrder();
	}

	public boolean isMapperAndDtosAndDaos() {
		return this.buildOrder == BuildOrder.MAPPER_AND_DTOS_AND_DAOS.getBuildOrder();
	}
}
