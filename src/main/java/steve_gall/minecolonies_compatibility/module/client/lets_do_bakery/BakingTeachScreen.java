package steve_gall.minecolonies_compatibility.module.client.lets_do_bakery;

import java.util.List;

import com.minecolonies.api.crafting.ItemStorage;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import satisfy.bakery.recipe.BakerStationRecipe;
import steve_gall.minecolonies_compatibility.core.client.gui.TeachRecipeScreen;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.crafting.StoveRecipeStorage;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.menu.BakingTeachMenu;
import steve_gall.minecolonies_tweaks.api.common.crafting.ICustomizedRecipeStorage;

public class BakingTeachScreen extends TeachRecipeScreen<BakingTeachMenu, BakerStationRecipe>
{
	public static final ResourceLocation TEXTURE = MineColoniesCompatibility.rl("textures/gui/lets_do_bakery_baking_teach.png");

	public BakingTeachScreen(BakingTeachMenu menu, Inventory inventory, Component title)
	{
		super(menu, inventory, title);

		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	protected ICustomizedRecipeStorage createRecipeStorage(BakerStationRecipe recipe, List<ItemStorage> input)
	{
		var resultContainer = this.menu.getResultContainer();
		var output = resultContainer.getItem(0);
		return new StoveRecipeStorage(recipe.getId(), input, output);
	}

	@Override
	public void render(PoseStack pose, int mouseX, int mouseY, float partialTicks)
	{
		this.renderBackground(pose);

		super.render(pose, mouseX, mouseY, partialTicks);

		this.renderTooltip(pose, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack pose, float partialTicks, int mouseX, int mouseY)
	{
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);

		this.blit(pose, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
	}

}
