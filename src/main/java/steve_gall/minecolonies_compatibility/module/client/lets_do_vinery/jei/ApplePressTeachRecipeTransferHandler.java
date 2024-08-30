package steve_gall.minecolonies_compatibility.module.client.lets_do_vinery.jei;

import java.util.Optional;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.satisfy.vinery.compat.jei.category.ApplePressCategory;
import net.satisfy.vinery.recipe.ApplePressRecipe;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.menu.ApplePressTeachMenu;

public class ApplePressTeachRecipeTransferHandler extends TeachRecipeTransferHandler<ApplePressTeachMenu, ApplePressRecipe, ApplePressRecipe>
{
	public ApplePressTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends ApplePressTeachMenu> getContainerClass()
	{
		return ApplePressTeachMenu.class;
	}

	@Override
	public Optional<MenuType<ApplePressTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<ApplePressRecipe> getRecipeType()
	{
		return ApplePressCategory.APPLE_PRESS;
	}

	@Override
	protected ApplePressRecipe getRecipe(ApplePressTeachMenu menu, ApplePressRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return categoryRecipe;
	}

	@Override
	protected void serializePayload(ApplePressTeachMenu menu, ApplePressRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		tag.put("input", input.get(0).serializeNBT());
	}

}
