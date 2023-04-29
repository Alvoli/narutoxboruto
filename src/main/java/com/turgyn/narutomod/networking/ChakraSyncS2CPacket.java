package com.turgyn.narutomod.networking;

import com.turgyn.narutomod.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ChakraSyncS2CPacket {
	private final int chakra;

	public ChakraSyncS2CPacket(int chakra) {
		this.chakra = chakra;
	}

	public ChakraSyncS2CPacket(FriendlyByteBuf buf) {
		this.chakra = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(chakra);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			PlayerData.setChakra(chakra);
			PlayerData.setCurrentMaxChakra(50);
		});
	}
}
