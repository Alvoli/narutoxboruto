package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncGenjutsu {
	private final int genjutsu;

	private int senjutsu;

	public SyncGenjutsu(int genjutsu) {
		this.genjutsu = genjutsu;
	}

	public SyncGenjutsu(FriendlyByteBuf buf) {
		this.genjutsu = buf.readInt();
	}

	public void SyncSenjutsu(int senjutsu) {
		this.senjutsu = senjutsu;
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(genjutsu);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setGenjutsu(genjutsu));
	}
}
