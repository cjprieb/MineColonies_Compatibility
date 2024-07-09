package steve_gall.minecolonies_compatibility.module.client.farmersdelight.jei;

import java.util.Optional;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.menu.TeachCookingMenu;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;

public class TeachCookingRecipeTransferHandler extends TeachRecipeTransferHandler<TeachCookingMenu, CookingPotRecipe, CookingPotRecipe>
{
	public TeachCookingRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends TeachCookingMenu> getContainerClass()
	{
		return TeachCookingMenu.class;
	}

	@Override
	public Optional<MenuType<TeachCookingMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<CookingPotRecipe> getRecipeType()
	{
		return FDRecipeTypes.COOKING;
	}

	@Override
	protected CookingPotRecipe getRecipe(TeachCookingMenu menu, CookingPotRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return categoryRecipe;
	}

	@Override
	protected void serializePayload(TeachCookingMenu menu, CookingPotRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		NBTUtils2.serializeList(tag, "input", input, ItemStack::serializeNBT);
	}

}
