package com.turgyn.narutomod.capabilities;

import net.minecraft.nbt.CompoundTag;

public class PlayerChakra {
	public final int MAX_VALUE = 10;

	private int chakra;

	public int getValue() {
		return chakra;
	}

	public void addValue(int add) {
		this.chakra = Math.min(chakra + add, MAX_VALUE);
	}

	public void subValue(int sub) {
		this.chakra = Math.max(chakra - sub, 0);
	}

	public void copyFrom(PlayerChakra source) {
		this.chakra = source.chakra;
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putInt("chakra", chakra);
	}

	public void loadNBTData(CompoundTag nbt) {
		chakra = nbt.getInt("chakra");
	}
}