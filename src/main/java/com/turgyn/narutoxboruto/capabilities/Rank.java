package com.turgyn.narutoxboruto.capabilities;

import com.turgyn.narutoxboruto.networking.ModPacketHandler;
import com.turgyn.narutoxboruto.networking.SyncRank;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class Rank {
	private String rank;

	public String getRank() {
		return rank;
	}

	public void setRank(String rank, ServerPlayer serverPlayer) {
		this.rank = rank;
		this.syncValue(serverPlayer);
	}

	public void syncValue(ServerPlayer serverPlayer) {
		ModPacketHandler.sendToPlayer(new SyncRank(this.rank), serverPlayer);
	}

	public void copyFrom(Rank source, ServerPlayer serverPlayer) {
		this.rank = source.rank;
		this.syncValue(serverPlayer);
	}

	public void saveNBTData(CompoundTag nbt) {
		nbt.putString("rank", rank);
	}

	public void loadNBTData(CompoundTag nbt) {
		rank = nbt.getString("rank");
	}
}