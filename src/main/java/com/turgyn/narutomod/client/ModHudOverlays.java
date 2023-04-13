package com.turgyn.narutomod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.turgyn.narutomod.Main;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ModHudOverlays {
	private static final ResourceLocation FULL_CHAKRA = new ResourceLocation(Main.MOD_ID,
			"textures/chakra/full_chakra.png");

	private static final ResourceLocation EMPTY_CHAKRA = new ResourceLocation(Main.MOD_ID,
			"textures/chakra/empty_chakra.png");

	public static final IGuiOverlay CHAKRA = ((gui, poseStack, partialTick, width, height) -> {
		int x = width / 2;
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, EMPTY_CHAKRA);
		for (int i = 0; i < 10; i++) {
			GuiComponent.blit(poseStack, x - 94 + (i * 9), height - 54, 0, 0, 10, 10, 10, 10);
		}
		RenderSystem.setShaderTexture(0, FULL_CHAKRA);
		for (int i = 0; i < 10; i++) {
			if (ChakraData.getPlayerChackra() > i) {
				GuiComponent.blit(poseStack, x - 94 + (i * 9), height - 54, 0, 0, 10, 10, 10, 10);
			}
			else {
				break;
			}
		}
	});
}
