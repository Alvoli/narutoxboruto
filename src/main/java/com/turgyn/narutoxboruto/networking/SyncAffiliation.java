package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncAffiliation {
	private final String affiliation;

	public SyncAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public SyncAffiliation(FriendlyByteBuf buf) {
		this.affiliation = buf.readComponent().getString();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeComponent(Component.literal(affiliation));
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setAffiliation(affiliation));
	}
}
