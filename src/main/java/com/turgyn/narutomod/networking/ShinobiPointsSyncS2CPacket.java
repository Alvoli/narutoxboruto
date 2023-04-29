package com.turgyn.narutomod.networking;

import com.turgyn.narutomod.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ShinobiPointsSyncS2CPacket {
	private final int shinobi_points;

	public ShinobiPointsSyncS2CPacket(int shinobi_points) {
		this.shinobi_points = shinobi_points;
	}

	public ShinobiPointsSyncS2CPacket(FriendlyByteBuf buf) {
		this.shinobi_points = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(shinobi_points);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setShinobi_points(shinobi_points));
	}
}
