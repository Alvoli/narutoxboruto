package com.turgyn.narutomod.capabilities;

import com.turgyn.narutomod.networking.ModPacketHandler;
import com.turgyn.narutomod.networking.ShinobiPointsSyncS2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class ShinobiPoints {
	public final int MAX_VALUE = Integer.MAX_VALUE;

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
		ModPacketHandler.sendToPlayer(new ShinobiPointsSyncS2CPacket(this.value), serverPlayer);
	}

	public void copyFrom(ShinobiPoints source, ServerPlayer serverPlayer) {
		this.value = source.value;
		this.syncValue(serverPlayer);
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putInt("shinobi_points", value);
	}

	public void loadNBTData(CompoundTag nbt) {
		value = nbt.getInt("shinobi_points");
	}
}