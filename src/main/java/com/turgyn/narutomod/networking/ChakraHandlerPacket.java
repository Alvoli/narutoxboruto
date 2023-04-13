package com.turgyn.narutomod.networking;

import com.turgyn.narutomod.chackra.ModCapabilityProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ChakraHandlerPacket {
	public ChakraHandlerPacket() {
	}

	public ChakraHandlerPacket(FriendlyByteBuf buf) {
	}

	public void toBytes(FriendlyByteBuf buf) {
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			player.getCapability(ModCapabilityProvider.CHAKRA).ifPresent(chakra -> {
				if (player.isCrouching() && chakra.getValue() < chakra.MAX_VALUE) {
					chakra.addValue(1);
				}else if(chakra.getValue() > 0) {
					chakra.subValue(1);
				}
				ModPacketHandler.sendToPlayer(new ChakraDataSyncPacket(chakra.getValue()), player);
			});
		});
	}
}