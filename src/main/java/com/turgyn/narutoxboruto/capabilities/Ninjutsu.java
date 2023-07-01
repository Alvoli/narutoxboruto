package com.turgyn.narutoxboruto.capabilities;

import com.turgyn.narutoxboruto.networking.ModPacketHandler;
import com.turgyn.narutoxboruto.networking.SyncNinjutsu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import static com.turgyn.narutoxboruto.capabilities.CapabilityProvider.SHINOBI_POINTS;

@AutoRegisterCapability
public class Ninjutsu {
	public final int MAX_VALUE = 10;

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
		ModPacketHandler.sendToPlayer(new SyncNinjutsu(this.value), serverPlayer);
	}

	public void copyFrom(Ninjutsu source, ServerPlayer serverPlayer) {
		this.value = source.getValue();
		this.syncValue(serverPlayer);
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putInt("ninjutsu", value);
	}

	public void loadNBTData(CompoundTag nbt) {
		value = nbt.getInt("ninjutsu");
	}
}