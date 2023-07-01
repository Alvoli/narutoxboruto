package com.turgyn.narutoxboruto.capabilities;

import com.turgyn.narutoxboruto.networking.ModPacketHandler;
import com.turgyn.narutoxboruto.networking.SyncAffiliation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class Affiliation {
	private String affiliation = "";

	public String getValue() {
		return affiliation;
	}

	public void setAffiliation(String affiliation, ServerPlayer serverPlayer) {
		this.affiliation = affiliation;
		this.syncValue(serverPlayer);
	}

	public void syncValue(ServerPlayer serverPlayer) {
		ModPacketHandler.sendToPlayer(new SyncAffiliation(this.affiliation), serverPlayer);
	}

	public void copyFrom(Affiliation source, ServerPlayer serverPlayer) {
		this.affiliation = source.getValue();
		this.syncValue(serverPlayer);
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putString("affiliation", affiliation);
	}

	public void loadNBTData(CompoundTag nbt) {
		affiliation = nbt.getString("affiliation");
	}
}