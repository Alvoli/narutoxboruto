package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncClan {
	private final String clan;

	public SyncClan(String clan) {
		this.clan = clan;
	}

	public SyncClan(FriendlyByteBuf buf) {
		this.clan = buf.readComponent().getString();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeComponent(Component.literal(clan));
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setClan(clan));
	}
}