package com.turgyn.narutoxboruto.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class ReleaseList {
	private String releaseList = "";

	public String getList() {
		return releaseList;
	}

	public void updateReleaseList(String releaseList) {
		this.releaseList = this.releaseList.concat(releaseList);
	}

	public void copyFrom(ReleaseList source) {
		this.releaseList = source.getList();
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putString("releaseList", releaseList);
	}

	public void loadNBTData(CompoundTag nbt) {
		releaseList = nbt.getString("releaseList");
	}
}