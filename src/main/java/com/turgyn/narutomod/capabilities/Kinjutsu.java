package com.turgyn.narutomod.capabilities;

import com.turgyn.narutomod.networking.KinjutsuSyncS2CPacket;
import com.turgyn.narutomod.networking.ModPacketHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class Kinjutsu {
	public final int MAX_VALUE = 500;

	private int value;

	public int getValue() {
		return value;
	}

	public void addValue(int add, ServerPlayer serverPlayer) {
		this.value = Math.min(value + add, MAX_VALUE);
		this.syncValue(serverPlayer);
	}

	public void subValue(int sub, ServerPlayer serverPlayer) {
		this.value = Math.max(value - sub, 0);
		this.syncValue(serverPlayer);
	}

	public void syncValue(ServerPlayer serverPlayer) {
		ModPacketHandler.sendToPlayer(new KinjutsuSyncS2CPacket(this.value), serverPlayer);
	}

	public void copyFrom(Kinjutsu source, ServerPlayer serverPlayer) {
		this.value = source.value;
		this.syncValue(serverPlayer);
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putInt("kinjutsu", value);
	}

	public void loadNBTData(CompoundTag nbt) {
		value = nbt.getInt("kinjutsu");
	}
}