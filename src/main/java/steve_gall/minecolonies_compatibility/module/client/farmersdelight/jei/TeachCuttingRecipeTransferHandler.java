package steve_gall.minecolonies_compatibility.module.client.farmersdelight.jei;

import java.util.Optional;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.menu.TeachCuttingMenu;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;

public class TeachCuttingRecipeTransferHandler extends TeachRecipeTransferHandler<TeachCuttingMenu, CuttingBoardRecipe, CuttingBoardRecipe>
{
	public TeachCuttingRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends TeachCuttingMenu> getContainerClass()
	{
		return TeachCuttingMenu.class;
	}

	@Override
	public Optional<MenuType<TeachCuttingMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<CuttingBoardRecipe> getRecipeType()
	{
		return FDRecipeTypes.CUTTING;
	}

	@Override
	protected CuttingBoardRecipe getRecipe(TeachCuttingMenu menu, CuttingBoardRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return categoryRecipe;
	}

	@Override
	protected void serializePayload(TeachCuttingMenu menu, CuttingBoardRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		tag.put("input", input.get(1).serializeNBT());
	}

}
