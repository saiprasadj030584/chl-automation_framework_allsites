package com.jda.wms.stepdefs.rdt;

public class Sample {

	public static void main(String[] args) {
		String destination = "CZECSD________";
		String [] dest = destination.split("_");
		System.out.println(dest[0]);
	}
}
