package com.turgyn.narutomod.client;

public class ChakraData {
	private static int playerChackra;

	public static void set(int chackra) {
		ChakraData.playerChackra = chackra;
	}

	public static int getPlayerChackra() {
		return playerChackra;
	}
}
