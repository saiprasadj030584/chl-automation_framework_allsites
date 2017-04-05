package com.jda.wms.context;

public class BackOfficeContext {
	String slaveNumber;
	int alocatednumber;
	int slaveCount;

	public int getSlaveCount() {
		return slaveCount;
	}

	public void setSlaveCount(int slaveCount) {
		this.slaveCount = slaveCount;
	}

	public int getAlocatednumber() {
		return alocatednumber;
	}

	public void setAlocatednumber(int alocatednumber) {
		this.alocatednumber = alocatednumber;
	}

	int reservedvalue;

	public int getReservedvalue() {
		return reservedvalue;
	}

	public void setReservedvalue(int reservedvalue) {
		this.reservedvalue = reservedvalue;
	}

	public String getSlaveNumber() {
		return slaveNumber;
	}

	public void setSlaveNumber(String slaveNumber) {
		this.slaveNumber = slaveNumber;
	}

}
