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
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.menu.CuttingTeachMenu;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;

public class CuttingTeachRecipeTransferHandler extends TeachRecipeTransferHandler<CuttingTeachMenu, CuttingBoardRecipe, CuttingBoardRecipe>
{
	public CuttingTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends CuttingTeachMenu> getContainerClass()
	{
		return CuttingTeachMenu.class;
	}

	@Override
	public Optional<MenuType<CuttingTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<CuttingBoardRecipe> getRecipeType()
	{
		return FDRecipeTypes.CUTTING;
	}

	@Override
	protected CuttingBoardRecipe getRecipe(CuttingTeachMenu menu, CuttingBoardRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return categoryRecipe;
	}

	@Override
	protected void serializePayload(CuttingTeachMenu menu, CuttingBoardRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		tag.put("input", input.get(1).serializeNBT());
	}

}
