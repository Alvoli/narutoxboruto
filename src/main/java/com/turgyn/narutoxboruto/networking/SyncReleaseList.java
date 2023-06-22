package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncReleaseList {
	private final String releaseList;

	public SyncReleaseList(String releaseList) {
		this.releaseList = releaseList;
	}

	public SyncReleaseList(FriendlyByteBuf buf) {
		this.releaseList = buf.readComponent().getString();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeComponent(Component.literal(releaseList));
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setRank(releaseList));
	}
}