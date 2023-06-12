package com.turgyn.narutoxboruto.capabilities;

import com.turgyn.narutoxboruto.networking.SyncChakra;
import com.turgyn.narutoxboruto.networking.ModPacketHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class Chakra {
	public final int MAX_VALUE = 50;

	private int value;

	public int getValue() {
		return value;
	}

	public void addValue(int add, ServerPlayer serverPlayer) {
		this.value = Math.min(this.value + add, MAX_VALUE);
		this.syncValue(serverPlayer);
	}

	public void subValue(int sub, ServerPlayer serverPlayer) {
		this.value = Math.max(value - sub, 0);
		this.syncValue(serverPlayer);
	}

	public void syncValue(ServerPlayer serverPlayer) {
		ModPacketHandler.sendToPlayer(new SyncChakra(this.value), serverPlayer);
	}

	public void copyFrom(Chakra source, ServerPlayer serverPlayer) {
		this.value = source.value;
		addValue(source.getValue(), serverPlayer);
		this.syncValue(serverPlayer);
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putInt("chakra", value);
	}

	public void loadNBTData(CompoundTag nbt) {
		value = nbt.getInt("chakra");
	}
}