package com.turgyn.narutoxboruto.capabilities;

import com.turgyn.narutoxboruto.networking.ModPacketHandler;
import com.turgyn.narutoxboruto.networking.SyncAffiliation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class Affiliation {
	private String affiliation = "";

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation, ServerPlayer serverPlayer) {
		this.affiliation = affiliation;
		this.syncValue(serverPlayer);
	}

	public void syncValue(ServerPlayer serverPlayer) {
		ModPacketHandler.sendToPlayer(new SyncAffiliation(this.affiliation), serverPlayer);
	}

	public void copyFrom(Affiliation oldPLayer, ServerPlayer serverPlayer) {
		this.affiliation = oldPLayer.getAffiliation();
		this.syncValue(serverPlayer);
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putString("affiliation", affiliation);
	}

	public void loadNBTData(CompoundTag nbt) {
		affiliation = nbt.getString("affiliation");
	}
}