package com.turgyn.narutoxboruto.capabilities;

import com.turgyn.narutoxboruto.networking.ModPacketHandler;
import com.turgyn.narutoxboruto.networking.SyncSpeed;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import static com.turgyn.narutoxboruto.capabilities.CapabilityProvider.SHINOBI_POINTS;

@AutoRegisterCapability
public class Speed {
	public final int MAX_VALUE = 20;

	private int value;

	public int getValue() {
		return value;
	}

	public void addValue(int set, ServerPlayer serverPlayer) {
		this.value = Math.min(value + set, MAX_VALUE);
		this.syncValue(serverPlayer);
		serverPlayer.getCapability(SHINOBI_POINTS).ifPresent(
				shinobiPoints -> shinobiPoints.addValue(getValue(), serverPlayer));
	}

	public void syncValue(ServerPlayer serverPlayer) {
		ModPacketHandler.sendToPlayer(new SyncSpeed(this.value), serverPlayer);
	}

	public void copyFrom(Speed source, ServerPlayer serverPlayer) {
		this.value = source.getValue();
		this.syncValue(serverPlayer);
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putInt("speed", value);
	}

	public void loadNBTData(CompoundTag nbt) {
		value = nbt.getInt("speed");
	}
}