package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncRank {
	private final String rank;

	public SyncRank(String rank) {
		this.rank = rank==null ? "academy" : rank;
	}

	public SyncRank(FriendlyByteBuf buf) {
		this.rank = buf.readComponent().getString();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeComponent(Component.literal(rank));
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setRank(rank));
	}
}