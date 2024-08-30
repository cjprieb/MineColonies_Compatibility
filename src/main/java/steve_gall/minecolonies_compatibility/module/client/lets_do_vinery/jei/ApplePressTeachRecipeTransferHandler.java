package steve_gall.minecolonies_compatibility.module.client.lets_do_vinery.jei;

import java.util.Optional;

import com.minecolonies.api.crafting.IGenericRecipe;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.crafting.ApplePressCraftingType;
import steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.crafting.ApplePressDummyRecipe;
import steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.menu.ApplePressTeachMenu;

public class ApplePressTeachRecipeTransferHandler extends TeachRecipeTransferHandler<ApplePressTeachMenu, ApplePressDummyRecipe, IGenericRecipe>
{
	private final RecipeType<IGenericRecipe> recipeType;

	public ApplePressTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper, RecipeType<IGenericRecipe> recipeType)
	{
		super(recipeTransferHandlerHelper);

		this.recipeType = recipeType;
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
	public RecipeType<IGenericRecipe> getRecipeType()
	{
		return this.recipeType;
	}

	@Override
	protected ApplePressDummyRecipe getRecipe(ApplePressTeachMenu menu, IGenericRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		var map = ApplePressCraftingType.getRecipes();
		var ingredient = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT).get(0).getItem();
		var result = map.get(ingredient);

		if (result != null)
		{
			return new ApplePressDummyRecipe(ingredient, result);
		}
		else
		{
			return null;
		}

	}

	@Override
	protected void serializePayload(ApplePressTeachMenu menu, ApplePressDummyRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		tag.put("input", input.get(0).serializeNBT());
	}

}
