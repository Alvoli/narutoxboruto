package com.turgyn.narutoxboruto.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.turgyn.narutoxboruto.Main;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.turgyn.narutoxboruto.client.PlayerData.*;

public class ShinobiStatsGui extends Screen {
	private static final ResourceLocation BACKGROUND = new ResourceLocation(Main.MOD_ID,
			"textures/gui/shinobi_stats.png");

	private final String[] statList = {
			"taijutsu", "ninjutsu", "genjutsu", "kenjutsu", "kinjutsu", "medical", "senjutsu", "shurikenjutsu", "speed",
			"summoning", "affiliation", "clan", "rank", "shinobi_points"
	};

	private final List<Integer> lockedList = new ArrayList<>(Arrays.asList(1, 2, 4, 6, 7, 9));

	private final int[] valueIntList = {
			getTaijutsu(), getNinjutsu(), getGenjutsu(), getKenjutsu(), getKinjutsu(), getMedical(), getSenjutsu(),
			getShurikenjutsu(), getSpeed(), getSummoning(), 0, 0, 0, getShinobiPoints()
	};

	private final String[] valueStringList = {
			getAffiliation(), getClan(), getRank()
	};

	public ShinobiStatsGui() {
		super(Component.translatable("gui.narutoxboruto.shinobi_stats"));
	}

	@Override
	public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		this.renderBackground(pPoseStack);
		this.renderInfo(pPoseStack);
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
	}

	protected void createMenuControls() {
		this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (button) -> this.onClose())
				.bounds((this.width - 157) / 2, this.height / 2 + 90, 150, 20).build());
	}

	public void renderInfo(PoseStack pPoseStack) {
		for (int l = 0; l < this.statList.length; ++l) {
			if (!this.statList[l].isBlank()) {
				if(l<10){
					drawIntStat(pPoseStack,  l, 0, 0);
				}else if (l<13){
					drawStringStat(pPoseStack,l);
					switch (l) {
						case 10 -> drawIcon(pPoseStack, 147, 9, l);
						case 11 -> drawIcon(pPoseStack, 120, 11, l);
					}
				}else{
					drawIntStat(pPoseStack,l,-1, 101);
				}
			}
		}
	}

	private void drawIntStat(PoseStack pPoseStack, int index, int yOffset, int xOffset) {
		String value = this.lockedList.contains(index)? "-" : String.valueOf(this.valueIntList[index]);
		this.font.draw(pPoseStack, Component.translatable("stat.narutoxboruto." + this.statList[index]).append(": "),
				(float) ((this.width - 192) / 2 - 5) + xOffset, (float) (this.height / 2 - 79 + (index + yOffset) * 12), 0);
		this.font.draw(pPoseStack, Component.literal(value),
				(float) ((this.width - 192) / 2 + 72 + xOffset), (float) (this.height / 2 - 79 + (index + yOffset) * 12), 0);
	}

	private void drawStringStat(PoseStack pPoseStack, int index) {
		String spaces = index == 12 ? "" : "   ";
		this.font.draw(pPoseStack, Component.translatable("stat.narutoxboruto." + this.statList[index]).append(": " + spaces)
						.append(Component.translatable(
								this.statList[index] + ".narutoxboruto." + this.valueStringList[index - 10])),
				(float) ((this.width - 192) / 2 + 95), (float) (this.height / 2 - 79 + (index - 10) * 12), 0);

	}
	private void drawIcon(PoseStack pPoseStack, int x, int size, int l) {
		RenderSystem.setShaderTexture(0, new ResourceLocation(Main.MOD_ID,
				"textures/" + this.statList[l] + "s/" + this.valueStringList[l - 10] + ".png"));
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		blit(pPoseStack, (this.width - 192) / 2 + x, this.height / 2 - 80 + (l - 10) * 12, 0, 0.0F, 0.0F, size, size,
				size, size);
	}

	@Override
	protected void init() {
		createMenuControls();
		super.init();
	}

	@Override
	public void renderBackground(PoseStack pPoseStack) {
		super.renderBackground(pPoseStack);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, BACKGROUND);
		blit(pPoseStack, (this.width - 234) / 2, (this.height - 192) / 2, 0, 0, 234, 192);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
