package com.turgyn.narutomod.networking;

import com.turgyn.narutomod.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class GenjutsuSyncS2CPacket {
	private final int genjutsu;

	public GenjutsuSyncS2CPacket(int genjutsu) {
		this.genjutsu = genjutsu;
	}

	public GenjutsuSyncS2CPacket(FriendlyByteBuf buf) {
		this.genjutsu = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(genjutsu);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setGenjutsu(genjutsu));
	}
}
