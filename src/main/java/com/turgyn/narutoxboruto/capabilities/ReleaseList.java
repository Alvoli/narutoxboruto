package com.turgyn.narutoxboruto.capabilities;

import net.minecraft.nbt.CompoundTag;

public class ReleaseList {
	private String release = "";

	public String getList() {
		return release;
	}

	public void updateReleaseList(String release) {
		this.release = this.release.concat(release);
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