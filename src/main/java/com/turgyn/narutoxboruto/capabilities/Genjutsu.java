package com.turgyn.narutoxboruto.capabilities;

import com.turgyn.narutoxboruto.networking.SyncGenjutsu;
import com.turgyn.narutoxboruto.networking.ModPacketHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

import static com.turgyn.narutoxboruto.capabilities.CapabilityProvider.SHINOBI_POINTS;

public class Genjutsu {
	public final int MAX_VALUE = 300;

	private int value;

	public int getValue() {
		return value;
	}

	public void addValue(int add, ServerPlayer serverPlayer) {
		this.value = Math.min(value + add, MAX_VALUE);
		this.syncValue(serverPlayer);
		serverPlayer.getCapability(SHINOBI_POINTS).ifPresent(
				shinobiPoints -> shinobiPoints.addValue(add, serverPlayer));
	}

	public void subValue(int sub, ServerPlayer serverPlayer) {
		this.value = Math.max(value - sub, 0);
		this.syncValue(serverPlayer);
	}

	public void syncValue(ServerPlayer serverPlayer) {
		ModPacketHandler.sendToPlayer(new SyncGenjutsu(this.value), serverPlayer);
	}

	public void copyFrom(Genjutsu source, ServerPlayer serverPlayer) {
		this.value = source.value;
		this.syncValue(serverPlayer);
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putInt("genjutsu", value);
	}

	public void loadNBTData(CompoundTag nbt) {
		value = nbt.getInt("genjutsu");
	}
}