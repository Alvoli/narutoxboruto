package com.turgyn.narutoxboruto.capabilities;

import com.turgyn.narutoxboruto.networking.ModPacketHandler;
import com.turgyn.narutoxboruto.networking.SyncClan;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class Clan {
	private String clan = "";

	public String getValue() {
		return clan;
	}

	public void setClan(String clan, ServerPlayer serverPlayer) {
		this.clan = clan;
		this.syncValue(serverPlayer);
	}

	public void syncValue(ServerPlayer serverPlayer) {
		ModPacketHandler.sendToPlayer(new SyncClan(this.clan), serverPlayer);
	}

	public void copyFrom(Clan source, ServerPlayer serverPlayer) {
		this.clan = source.getValue();
		this.syncValue(serverPlayer);
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putString("clan", clan);
	}

	public void loadNBTData(CompoundTag nbt) {
		clan = nbt.getString("clan");
	}
}