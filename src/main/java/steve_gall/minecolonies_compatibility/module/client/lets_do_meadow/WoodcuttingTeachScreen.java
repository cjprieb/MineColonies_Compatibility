package steve_gall.minecolonies_compatibility.module.client.lets_do_meadow;

import java.util.List;

import com.minecolonies.api.crafting.ItemStorage;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.satisfy.meadow.recipes.WoodcuttingRecipe;
import steve_gall.minecolonies_compatibility.core.client.gui.TeachRecipeScreen;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_meadow.crafting.WoodcuttingRecipeStorage;
import steve_gall.minecolonies_compatibility.module.common.lets_do_meadow.menu.WoodcuttingTeachMenu;
import steve_gall.minecolonies_tweaks.api.common.crafting.ICustomizedRecipeStorage;

public class WoodcuttingTeachScreen extends TeachRecipeScreen<WoodcuttingTeachMenu, WoodcuttingRecipe>
{
	public static final ResourceLocation TEXTURE = MineColoniesCompatibility.rl("textures/gui/lets_do_meadow_woodcutting_teach.png");

	public WoodcuttingTeachScreen(WoodcuttingTeachMenu menu, Inventory inventory, Component title)
	{
		super(menu, inventory, title);

		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	protected ICustomizedRecipeStorage createRecipeStorage(WoodcuttingRecipe recipe, List<ItemStorage> input)
	{
		var resultContainer = this.menu.getResultContainer();
		var output = resultContainer.getItem(0);
		return new WoodcuttingRecipeStorage(recipe.getId(), input, output);
	}

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
	{
		this.renderBackground(graphics);

		super.render(graphics, mouseX, mouseY, partialTicks);

		this.renderTooltip(graphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY)
	{
		graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
	}

}
