package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncKenjutsu {
	private final int kenjutsu;

	public SyncKenjutsu(int kenjutsu) {
		this.kenjutsu = kenjutsu;
	}

	public SyncKenjutsu(FriendlyByteBuf buf) {
		this.kenjutsu = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(kenjutsu);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setKenjutsu(kenjutsu));
	}
}
