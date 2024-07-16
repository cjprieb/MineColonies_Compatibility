package steve_gall.minecolonies_compatibility.module.client.jei;

import java.util.Optional;

import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import steve_gall.minecolonies_compatibility.core.common.inventory.SmithingTeachMenu;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;

public class SmithingTeachRecipeTransferHandler extends TeachRecipeTransferHandler<SmithingTeachMenu, UpgradeRecipe, UpgradeRecipe>
{
	public SmithingTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends SmithingTeachMenu> getContainerClass()
	{
		return SmithingTeachMenu.class;
	}

	@Override
	public Optional<MenuType<SmithingTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<UpgradeRecipe> getRecipeType()
	{
		return RecipeTypes.SMITHING;
	}

	@Override
	protected UpgradeRecipe getRecipe(SmithingTeachMenu menu, UpgradeRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return categoryRecipe;
	}

	@Override
	protected Component getError(SmithingTeachMenu menu, UpgradeRecipe recipe, IRecipeSlotsView recipeSlots, Player player)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		var base = input.get(0);
		var addition = input.get(1);
		var error = menu.getRecipeError(base, addition);

		if (error != null)
		{
			return error;
		}

		return super.getError(menu, recipe, recipeSlots, player);
	}

	@Override
	protected void serializePayload(SmithingTeachMenu menu, UpgradeRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		NBTUtils2.serializeCollection(tag, "input", input, ItemStack::serializeNBT);
	}

}
