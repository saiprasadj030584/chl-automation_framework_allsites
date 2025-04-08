package com.mns.auto.cd.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class TestDemo {

	// TODO Auto-generated method stub
	public static void main(String[] args) throws JSchException, SftpException, IOException {
		// TODO Auto-generated method stub

		JSch jsch = new JSch();
		Session session = null;
		Channel channel = null;

		// Variable Declaration.
		String user = "rpwms";
		String host = "huxc0562.unix.marksandspencercate.com";
		Integer port = 22;
		String password = "rpwms";
		String watchFolder = "D:\\Chandan\\Text\\1.txt";
		String outputDir = "/opt/RP/Divakar/";
		String filemask = "*.txt";
		ChannelSftp sftpChannel = (ChannelSftp) channel;

		try {
			session = jsch.getSession(user, host, port);

			/*
			 * StrictHostKeyChecking Indicates what to do if the server's host
			 * key changed or the server is unknown. One of yes (refuse
			 * connection), ask (ask the user whether to add/change the key) and
			 * no (always insert the new key).
			 */
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);

			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();

			// Go through watch folder looking for files.
			File[] files = findFile(watchFolder, filemask);
			for (File file : files) {
				// Upload file.
				putFile(file, sftpChannel, outputDir);
			}
		} finally {
			sftpChannel.exit();
			session.disconnect();
		}

	}

	public static void putFile(File file, ChannelSftp sftpChannel, String outputDir) throws SftpException, IOException {

		FileInputStream fis = null;

		// Change to output directory.
		sftpChannel.cd(outputDir);

		// Upload file.

		fis = new FileInputStream(file);
		sftpChannel.put(fis, file.getName());
		fis.close();

	}

	public static File[] findFile(String dirName, final String mask) {
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(mask);
			}
		});
	}

}
