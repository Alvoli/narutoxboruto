package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncNinjutsu {
	private final int ninjutsu;

	public SyncNinjutsu(int ninjutsu) {
		this.ninjutsu = ninjutsu;
	}

	public SyncNinjutsu(FriendlyByteBuf buf) {
		this.ninjutsu = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(ninjutsu);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setNinjutsu(ninjutsu));
	}
}
