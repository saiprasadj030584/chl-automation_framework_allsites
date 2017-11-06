package com.jda.wms.datasetup.gm;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import org.junit.Assert;


import com.google.inject.Inject;
import com.jda.wms.context.Context;



public class GetTcData {
	public static DbConnection dataBase = new DbConnection();
	private Context context;

	@Inject
	public GetTcData(Context context) {
		this.context = context;
	}

	public String getPo() {
		String value = null;
		value = context.getPreAdviceId();
		if (!context.getUniqueTagInRunStatus()) {
			// ResultSet resultSet = null;
			// try {
			// dataBase.connectAutomationDB();
			// resultSet = dataBase.dbConnection.createStatement()
			// .executeQuery("SELECT * FROM DBO.JDA_GM_RUN_STATUS WHERE
			// PARENT_REQUEST_ID ='"
			// + context.getParentRequestId() + "' AND UNIQUE_TAG ='" +
			// context.getUniqueTag() + "'");
			//
			// while (resultSet.next()) {
			// value = resultSet.getString("PO_ID");
			// }
			// dataBase.disconnectAutomationDB();
			// } catch (Exception exception) {
			// System.out.println("AT exception");
			// exception.printStackTrace();
			// }
		}
		return value;
	}

	public String getSto() {

		String value = null;
		System.out.println("UNIQ TAGG"+context.getUniqueTagInRunStatus());
		if (!context.getUniqueTagInRunStatus()) {

			// ResultSet resultSet = null;
			// try {
			// dataBase.connectAutomationDB();
			// resultSet = dataBase.dbConnection.createStatement()
			// .executeQuery("SELECT * FROM DBO.JDA_GM_RUN_STATUS WHERE
			// PARENT_REQUEST_ID ='"
			// + context.getParentRequestId() + "' AND UNIQUE_TAG ='" +
			// context.getUniqueTag() + "'");
			//
			// while (resultSet.next()) {
			// value = resultSet.getString("STO_ID");
			//
			// }
			// dataBase.disconnectAutomationDB();
			// } catch (Exception exception) {
			// exception.printStackTrace();
			// }


//			ResultSet resultSet = null;
//			try {
//				dataBase.connectAutomationDB();
//				resultSet = dataBase.dbConnection.createStatement()
//						.executeQuery("SELECT * FROM DBO.JDA_GM_RUN_STATUS WHERE PARENT_REQUEST_ID ='"
//								+ context.getParentRequestId() + "' AND UNIQUE_TAG ='" + context.getUniqueTag() + "'");
//
//				while (resultSet.next()) {
//					value = resultSet.getString("STO_ID");
//
//				}
//				dataBase.disconnectAutomationDB();
//			} catch (Exception exception) {
//				exception.printStackTrace();
//			}

		} else {
			value = context.getOrderId();
		}
		System.out.println("COOOOOOOO"+value);
		return value;
	}

	public String getUpi() {

		String value = null;
		value = context.getPalletID();
		if (!context.getUniqueTagInRunStatus()) {
			// ResultSet resultSet = null;
			// try {
			// dataBase.connectAutomationDB();
			// resultSet = dataBase.dbConnection.createStatement()
			// .executeQuery("SELECT * FROM DBO.JDA_GM_RUN_STATUS WHERE
			// PARENT_REQUEST_ID ='"
			// + context.getParentRequestId() + "' AND UNIQUE_TAG ='" +
			// context.getUniqueTag() + "'");
			//
			// while (resultSet.next()) {
			// value = resultSet.getString("PALLET_ID");
			//
			// }
			// dataBase.disconnectAutomationDB();
			// } catch (Exception exception) {
			// exception.printStackTrace();
			// }
		}
		// else {
		// value = context.getPalletID();
		// }
		return value;
	}

	public String getAsn() {
		String value = null;
		value = context.getAsnId();
		if (!context.getUniqueTagInRunStatus()) {

			// ResultSet resultSet = null;
			//
			// try {
			// dataBase.connectAutomationDB();
			// resultSet = dataBase.dbConnection.createStatement()
			// .executeQuery("SELECT * FROM DBO.JDA_GM_RUN_STATUS WHERE
			// PARENT_REQUEST_ID ='"
			// + context.getParentRequestId() + "' AND UNIQUE_TAG ='" +
			// context.getUniqueTag() + "'");
			//
			// while (resultSet.next()) {
			// value = resultSet.getString("ASN_ID");
			//
			// }
			// dataBase.disconnectAutomationDB();
			// } catch (Exception exception) {
			// exception.printStackTrace();
			// }

		} else {
			value = context.getAsnId();
		}
		return value;
	}

	public String getSkuList() {
		String value = null;
		value = context.getSkuId();
		if (!context.getUniqueTagInRunStatus()) {

			ResultSet resultSet = null;

			try {
				dataBase.connectAutomationDB();
				System.out.println(
						"SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag() + "'");
				// resultSet = dataBase.dbConnection.createStatement()
				// .executeQuery("SELECT * FROM DBO.JDA_GM_RUN_STATUS WHERE
				// PARENT_REQUEST_ID ='"
				// + context.getParentRequestId() + "' AND UNIQUE_TAG ='" +
				// context.getUniqueTag() + "'");

				resultSet = dataBase.dbConnection.createStatement().executeQuery(
						"SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag() + "'");

				while (resultSet.next()) {
					value = resultSet.getString("SKU_ID");
				}
				dataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		// else {
		// value = context.getSkuId();
		// }
		return value;
	}

	public void setPo(String value) {
		context.setPreAdviceId(value);
		System.out.println("inside Set po " + context.getPreAdviceId());
		// if (!context.getUniqueTagInRunStatus()) {
		// dataBase.connectAutomationDB();
		// try {
		// System.out.println("UPDATE DBO.JDA_GM_RUN_STATUS SET PO_ID= '" +
		// value + "' WHERE PARENT_REQUEST_ID ='"
		// + context.getParentRequestId() + "' AND UNIQUE_TAG ='" +
		// context.getUniqueTag() + "'");
		// dataBase.dbConnection.createStatement()
		// .execute("UPDATE DBO.JDA_GM_RUN_STATUS SET PO_ID= '" + value + "'
		// WHERE PARENT_REQUEST_ID ='"
		// + context.getParentRequestId() + "' AND UNIQUE_TAG ='" +
		// context.getUniqueTag() + "'");
		// dataBase.dbConnection.commit();
		// dataBase.disconnectAutomationDB();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }
	}

	public void setSto(String value) {
		if (!context.getUniqueTagInRunStatus()) {
			// dataBase.connectAutomationDB();
			// try {
			// dataBase.dbConnection.createStatement()
			// .execute("UPDATE DBO.JDA_GM_RUN_STATUS SET STO_ID= '" + value +
			// "' WHERE PARENT_REQUEST_ID ='"
			// + context.getParentRequestId() + "' AND UNIQUE_TAG ='" +
			// context.getUniqueTag() + "'");
			// dataBase.dbConnection.commit();
			// dataBase.disconnectAutomationDB();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
		}
		context.setOrderId(value);

	}

	public void setPalletId(String value) {

		// if (!context.getUniqueTagInRunStatus()) {
		// dataBase.connectAutomationDB();
		// try {
		// dataBase.dbConnection.createStatement()
		// .execute("UPDATE DBO.JDA_GM_RUN_STATUS SET PALLET_ID= '" + value
		// + "' WHERE PARENT_REQUEST_ID ='" + context.getParentRequestId() + "'
		// AND UNIQUE_TAG ='"
		// + context.getUniqueTag() + "'");
		// dataBase.dbConnection.commit();
		// dataBase.disconnectAutomationDB();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }
		context.setPalletID(value);

	}

	public void setAsnId(String value) {

		// if (!context.getUniqueTagInRunStatus()) {
		// dataBase.connectAutomationDB();
		// try {
		// dataBase.dbConnection.createStatement()
		// .execute("UPDATE DBO.JDA_GM_RUN_STATUS SET ASN_ID= '" + value + "'
		// WHERE PARENT_REQUEST_ID ='"
		// + context.getParentRequestId() + "' AND UNIQUE_TAG ='" +
		// context.getUniqueTag() + "'");
		// dataBase.dbConnection.commit();
		// dataBase.disconnectAutomationDB();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }
		context.setAsnId(value);
	}

	public void setSkuQtySupplier() {
		if (!context.getUniqueTagInRunStatus()) {
			try {
				dataBase.connectAutomationDB();
				dataBase.dbConnection.createStatement()
						.execute("UPDATE DBO.JDA_GM_RUN_STATUS	"
								+ "SET SKU_ID = (SELECT SKU_ID FROM DBO.JDA_GM_TEST_DATA WHERE " + "UNIQUE_TAG='"
								+ context.getUniqueTag() + "' AND SITE_NO='" + context.getSiteId() + "' )"
								+ ", QTY = (SELECT QTY FROM DBO.JDA_GM_TEST_DATA WHERE " + "UNIQUE_TAG='"
								+ context.getUniqueTag() + "' AND SITE_NO='" + context.getSiteId() + "' )"
								+ ", SUPPLIER_ID = (SELECT SUPPLIER_ID FROM DBO.JDA_GM_TEST_DATA WHERE "
								+ "UNIQUE_TAG='" + context.getUniqueTag() + "' AND SITE_NO='" + context.getSiteId()
								+ "' )" + "WHERE PARENT_REQUEST_ID ='" + context.getParentRequestId()
								+ "' AND UNIQUE_TAG ='" + context.getUniqueTag() + "'");
				dataBase.dbConnection.commit();
				dataBase.disconnectAutomationDB();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		context.setSkuId(getSkuListFromTestData());

	}

	public String getPoFromTestData() {
		ResultSet resultSet = null;
		String value = null;
		try {
			dataBase.connectAutomationDB();
			System.out.println("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
					+ "' AND SITE_NO='" + context.getSiteId() + "'");
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
							+ "' AND SITE_NO='" + context.getSiteId() + "'");

			while (resultSet.next()) {
				value = resultSet.getString("PO_ID");
			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
			//TODO include assertion statements
			if (exception.getMessage().contains("Exhausted Resultset")) {
				Assert.assertTrue("No PO reference data present in DB ", false);
			}
		}
		return value;
	}

	public String getStoFromTestData() {

		ResultSet resultSet = null;
		String value = null;

		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
							+ "' AND SITE_NO='" + context.getSiteId() + "'");

			while (resultSet.next()) {
				value = resultSet.getString("STO_ID");

			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}

	public String getUpiFromTestData() {

		ResultSet resultSet = null;
		String value = null;

		try {
			System.out.println("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
					+ "' AND SITE_NO='" + context.getSiteId() + "'");
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
							+ "' AND SITE_NO='" + context.getSiteId() + "'");

			while (resultSet.next()) {
				value = resultSet.getString("PALLET_ID");

			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
			//TODO include assertion statements
			if (exception.getMessage().contains("Exhausted Resultset")) {
				Assert.assertTrue("No UPI reference data present in DB ", false);
			}
		}
		return value;
	}

	public String getAsnFromTestData() {
		ResultSet resultSet = null;
		String value = null;
		try {
			dataBase.connectAutomationDB();
			System.out.println("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()

					+ "' AND SITE_NO='" + context.getSiteId() + "'");

			resultSet = dataBase.dbConnection.createStatement()
					// System.out.println("SELECT * FROM DBO.JDA_GM_TEST_DATA
					// WHERE UNIQUE_TAG ='" + context.getUniqueTag()
					// + "' AND SITE_NO='" + context.getSiteId() + "'");
					.executeQuery("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
							+ "' AND SITE_NO='" + context.getSiteId() + "'");

			while (resultSet.next()) {
				value = resultSet.getString("ASN_ID");
			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
			//TODO include assertion statements
			if (exception.getMessage().contains("Exhausted Resultset")) {
				Assert.assertTrue("No ASN reference data present in DB ", false);
			}
		}
		return value;
	}

	public String getSkuListFromTestData() {
		ResultSet resultSet = null;
		String value = null;
		try {
			dataBase.connectAutomationDB();
			System.out.println("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
					+ "' AND SITE_NO='" + context.getSiteId() + "'");
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
							+ "' AND SITE_NO='" + context.getSiteId() + "'");

			while (resultSet.next()) {
				value = resultSet.getString("SKU_ID");
			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}

	public String getQtyListFromTestData() {
		ResultSet resultSet = null;
		String value = null;
		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
							+ "' AND SITE_NO='" + context.getSiteId() + "'");

			while (resultSet.next()) {
				value = resultSet.getString("QTY");
			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}

	public void setOdn(String value) {
		context.setOrderId(value);
		if (!context.getUniqueTagInRunStatus()) {
			dataBase.connectAutomationDB();
			try {
				dataBase.dbConnection.createStatement()
						.execute("UPDATE DBO.JDA_GM_RUN_STATUS SET STO_ID= '" + value + "' WHERE PARENT_REQUEST_ID ='"
								+ context.getParentRequestId() + "' AND UNIQUE_TAG ='" + context.getUniqueTag() + "'");
				dataBase.dbConnection.commit();
				dataBase.disconnectAutomationDB();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public String getQtyFromTestData() {
		ResultSet resultSet = null;
		String value = null;
		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
							+ "' AND SITE_NO='" + context.getSiteId() + "'");

			while (resultSet.next()) {
				value = resultSet.getString("QTY");
			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}


	public String getOdnFromTestData() {
		ResultSet resultSet = null;
		String value = null;
		try {
			dataBase.connectAutomationDB();
			resultSet = dataBase.dbConnection.createStatement()
					.executeQuery("SELECT * FROM DBO.JDA_GM_TEST_DATA WHERE UNIQUE_TAG ='" + context.getUniqueTag()
							+ "' AND SITE_NO='" + context.getSiteId() + "'");

			while (resultSet.next()) {
				value = resultSet.getString("QTY");
			}
			dataBase.disconnectAutomationDB();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}

	public String getReferenceUpi() {
		String value = null;
		value = context.getPalletID();
		if (!context.getUniqueTagInRunStatus()) {

			ResultSet resultSet = null;

			try {
				dataBase.connectAutomationDB();
				resultSet = dataBase.dbConnection.createStatement()
						.executeQuery("SELECT PALLET_ID FROM DBO.JDA_GM_TEST_DATA where UNIQUE_TAG ='" + context.getUniqueTag() + "'");

				while (resultSet.next()) {
					value = resultSet.getString("PALLET_ID");

				}
				dataBase.disconnectAutomationDB();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} 
//		else {
//			value = context.getPalletID();
//		}
		return value;
	}


}
