package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncShurikenjutsu {
	private final int senjutsu;

	public SyncShurikenjutsu(int senjutsu) {
		this.senjutsu = senjutsu;
	}

	public SyncShurikenjutsu(FriendlyByteBuf buf) {
		this.senjutsu = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(senjutsu);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setShurikenjutsu(senjutsu));
	}
}
