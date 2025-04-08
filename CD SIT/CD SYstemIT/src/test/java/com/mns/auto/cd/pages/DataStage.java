package com.mns.auto.cd.pages;

import java.io.InputStream;

import org.junit.Assert;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.mns.auto.cd.memory.KeepInMemory;
import com.google.inject.Inject;

public class DataStage {

	private final KeepInMemory keepInMemory;

	@Inject
	public DataStage(KeepInMemory keepInMemory) {
		this.keepInMemory = keepInMemory;
	}

	public int TriggerDataStage(String region) {
		// System.out.println(region);
		String dsenvironinput = null;
		switch (region) {
		case "CATEB":

			dsenvironinput = "/ft";
			break;
		case "INTA":

			dsenvironinput = "/sit";
			break;
		case "INTB":

			dsenvironinput = "/pt";
			break;
		case "CATEA":
			dsenvironinput = "/ps";
			break;
		default:
			Assert.fail();
			break;
		}
		// inventoryDetailsOFDataStage =
		// queryResults.getInventoryDetails(region, "DATASTAGE");
		// System.out.println("inventoryDetailsOFDataStageUSERNAME" +
		// inventoryDetailsOFDataStage.get("userName"));

		try {

			java.util.Properties config = new java.util.Properties();
			JSch jsch = new JSch();
			// System.out.println(dsDetails.getUserName());
			// System.out.println(dsDetails.getPassWord());
			Session session = jsch.getSession(keepInMemory.getUserName(), keepInMemory.getServer(), 22);
			session.setPassword(keepInMemory.getPassWord());
			config.put("StrictHostKeyChecking", "no");
			session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
			session.setConfig(config);
			session.connect();
			System.out.println("Connected");

			Channel channel = session.openChannel("exec");
			System.out.println("Triggering the Data Stage job" + "    -- cd " + dsenvironinput
					+ "/InfoServer/Server/DSEngine && . ./dsenv && sh " + dsenvironinput
					+ keepInMemory.getShellScriptName());

			((ChannelExec) channel)
					.setCommand("cd " + dsenvironinput + "/InfoServer/Server/DSEngine && . ./dsenv && sh "
							+ dsenvironinput + keepInMemory.getShellScriptName());
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);
			InputStream in = channel.getInputStream();
			channel.connect();
			byte[] tmp = new byte[1024];
			String tempval, DSRetVal = null;
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					tempval = new String(tmp, 0, i);
					DSRetVal = DSRetVal + tempval;
					// System.out.println(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					// System.out.println("exit-status: " +
					// channel.getExitStatus());
					break;
				}
			}
			channel.disconnect();
			session.disconnect();
			if (DSRetVal.contains("Set To 0")) {
				return 0;
			} else {
				return 1;
			}

		} catch (Exception e) {
			System.out.println("At function: TriggerDS - Exception: " + e.getMessage().replace("'", ""));
			return 0;
		}

	}

}
