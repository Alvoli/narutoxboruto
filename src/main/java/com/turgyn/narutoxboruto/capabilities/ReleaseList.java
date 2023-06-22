package com.turgyn.narutoxboruto.capabilities;

import com.turgyn.narutoxboruto.networking.ModPacketHandler;
import com.turgyn.narutoxboruto.networking.SyncReleaseList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class ReleaseList {
	private String release = "";

	public String getRelease() {
		return release;
	}

	public void updateReleaseList(String release) {
		this.release = this.release + release;
	}

	public void copyFrom(ReleaseList source) {
		this.release = source.release;
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putString("release", release);
	}

	public void loadNBTData(CompoundTag nbt) {
		release = nbt.getString("release");
	}
}