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
import java.util.List;

import static com.turgyn.narutoxboruto.client.PlayerData.*;

public class ShinobiStatsGui extends Screen {
	private static final ResourceLocation BACKGROUND = new ResourceLocation(Main.MOD_ID,
			"textures/gui/shinobi_stats.png");

	private final String[] statList = new String[] {
			"taijutsu", "ninjutsu", "genjutsu", "kenjutsu", "kinjutsu", "medical", "senjutsu", "shurikenjutsu", "speed",
			"summoning", "affiliation", "clan", "rank", "shinobi_points"
	};

	private final List<Integer> lockedList = new ArrayList<>() {
		{
			add(1);
			add(2);
			add(4);
			add(6);
			add(7);
			add(9);
		}
	};

	private final int[] valueIntList = new int[] {
			getTaijutsu(), getNinjutsu(), getGenjutsu(), getKenjutsu(), getKinjutsu(), getMedical(), getSenjutsu(),
			getShurikenjutsu(), getSpeed(), getSummoning(), 0, 0, 0, getShinobiPoints()
	};

	private final String[] valueStringList = new String[] {
			getAffiliation(), getClan(), getRank()
	};

	public ShinobiStatsGui() {
		super(Component.translatable("gui.narutoxboruto.shinobi_stats"));
	}

	@Override
	public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		this.renderBackground(pPoseStack);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, BACKGROUND);
		blit(pPoseStack, (this.width - 234) / 2, (this.height - 192) / 2, 0, 0, 234, 192);
		this.renderInfo(pPoseStack);
		this.renderIcons(pPoseStack);
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
	}

	protected void createMenuControls() {
		this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (button) -> this.onClose())
				.bounds((this.width - 157) / 2, this.height / 2 + 90, 150, 20).build());
	}

	public void renderInfo(PoseStack pPoseStack) {
		for (int l = 0; l < this.statList.length; ++l) {
			if (!this.statList[l].isBlank()) {
				if (l > 10 && l < 13) {
					drawString(pPoseStack, this.statList[l],
							this.statList[l] + ".narutoxboruto." + this.valueStringList[l - 10], 101, 40, l - 10);
				}
				else if (l == 10) {
					drawString(pPoseStack, this.statList[l],
							this.statList[l] + ".narutoxboruto." + this.valueStringList[l - 10], 101, 60, 0);
				}
				else if (l == 13) {
					drawString(pPoseStack, this.statList[l], String.valueOf(this.valueIntList[l]), 101, 75, l - 3);
				}
				else {
					drawString(pPoseStack, this.statList[l],
							this.lockedList.contains(l) ? "-" : String.valueOf(this.valueIntList[l]), -5, 77, l);
				}
			}
		}
	}

	public void renderIcons(PoseStack pPoseStack) {
		for (int l = 0; l < this.valueStringList.length; ++l) {
			if (!this.valueStringList[l].equals("none")) {
				int x = l == 0 ? 150 : 130;
				int size = l == 0 ? 9 : 11;
				int offset = l == 0 ? 0 : 2;
				RenderSystem.setShaderTexture(0, new ResourceLocation(Main.MOD_ID,
						"textures/" + this.statList[l + 10] + "s/" + this.valueStringList[l] + ".png"));
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				blit(pPoseStack, (this.width - 192) / 2 + x - offset, this.height / 2 - 80 + l * 12, 0, 0.0F, 0.0F,
						size, size, size, size);
			}
		}
	}

	private void drawString(PoseStack pPoseStack, String stat, String value, int x, int x1, int l) {
		this.font.draw(pPoseStack, Component.translatable("stat.narutoxboruto." + stat).append(": "),
				(float) ((this.width - 192) / 2 + x), (float) (this.height / 2 - 79 + l * 12), 0);
		this.font.draw(pPoseStack, Component.translatable(value), (float) ((this.width - 192) / 2 + x + x1),
				(float) (this.height / 2 - 79 + l * 12), 0);
	}

	@Override
	protected void init() {
		createMenuControls();
		super.init();
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
