package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncKinjutsu {
	private final int kinjutsu;

	public SyncKinjutsu(int kinjutsu) {
		this.kinjutsu = kinjutsu;
	}

	public SyncKinjutsu(FriendlyByteBuf buf) {
		this.kinjutsu = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(kinjutsu);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setKinjutsu(kinjutsu));
	}
}
