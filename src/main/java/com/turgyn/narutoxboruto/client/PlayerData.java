package com.turgyn.narutoxboruto.client;

public class PlayerData {
	private static String clan, affiliation, rank;

	private static int chakra, currentMaxChakra;

	private static int taijutsu, ninjutsu, genjutsu, kenjutsu, kinjutsu, medical, senjutsu, shurikenjutsu, speed, summoning, shinobi_points;

	public static int getChakra() {
		return chakra;
	}

	public static void setChakra(int chakra) {
		PlayerData.chakra = chakra;
	}

	public static int getCurrentMaxChakra() {
		return currentMaxChakra;
	}

	public static void setCurrentMaxChakra(int currentMaxChakra) {
		PlayerData.currentMaxChakra = currentMaxChakra;
	}

	public static int getGenjutsu() {
		return genjutsu;
	}

	public static void setGenjutsu(int chakra) {
		PlayerData.genjutsu = chakra;
	}

	public static int getKenjutsu() {
		return kenjutsu;
	}

	public static void setKenjutsu(int kenjutsu) {
		PlayerData.kenjutsu = kenjutsu;
	}

	public static int getTaijutsu() {
		return taijutsu;
	}

	public static void setTaijutsu(int taijutsu) {
		PlayerData.taijutsu = taijutsu;
	}

	public static int getNinjutsu() {
		return ninjutsu;
	}

	public static void setNinjutsu(int ninjutsu) {
		PlayerData.ninjutsu = ninjutsu;
	}

	public static int getKinjutsu() {
		return kinjutsu;
	}

	public static void setKinjutsu(int kinjutsu) {
		PlayerData.kinjutsu = kinjutsu;
	}

	public static int getMedical() {
		return medical;
	}

	public static void setMedical(int medical) {
		PlayerData.medical = medical;
	}

	public static int getSenjutsu() {
		return senjutsu;
	}

	public static void setSenjutsu(int senjutsu) {
		PlayerData.senjutsu = senjutsu;
	}

	public static int getShurikenjutsu() {
		return shurikenjutsu;
	}

	public static void setShurikenjutsu(int shurikenjutsu) {
		PlayerData.shurikenjutsu = shurikenjutsu;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		PlayerData.speed = speed;
	}

	public static int getSummoning() {
		return summoning;
	}

	public static void setSummoning(int summoning) {
		PlayerData.summoning = summoning;
	}

	public static int getShinobiPoints() {
		return shinobi_points;
	}

	public static void setShinobi_points(int shinobi_points) {
		PlayerData.shinobi_points = shinobi_points;
	}

	public static String getClan() {
		return clan;
	}

	public static void setClan(String clan) {
		PlayerData.clan = clan;
	}

	public static String getRank() {
		return rank;
	}

	public static void setRank(String rank) {
		PlayerData.rank = rank;
	}

	public static String getAffiliation() {
		return affiliation;
	}

	public static void setAffiliation(String affiliation) {
		PlayerData.affiliation = affiliation;
	}
}
