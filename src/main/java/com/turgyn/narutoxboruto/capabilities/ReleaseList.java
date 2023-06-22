package com.turgyn.narutoxboruto.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

import java.util.Arrays;

public class ReleaseList {
	private String release;

	public String getRelease() {
		return release;
	}

	public void updateReleaseList(String release, ServerPlayer serverPlayer) {
		this.release = release;
//		this.syncValue(serverPlayer);
	}

//	public void syncValue(ServerPlayer serverPlayer) {
//		ModPacketHandler.sendToPlayer(new SyncRelease(this.release), serverPlayer);
//	}

	public void copyFrom(ReleaseList source, ServerPlayer serverPlayer) {
		this.release = source.release;
//		this.syncValue(serverPlayer);
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putString("release", release);
	}

	public void loadNBTData(CompoundTag nbt) {
		release = nbt.getString("release");
	}
}