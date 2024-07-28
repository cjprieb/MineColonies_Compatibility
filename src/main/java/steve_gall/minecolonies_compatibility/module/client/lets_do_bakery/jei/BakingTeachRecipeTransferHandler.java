package steve_gall.minecolonies_compatibility.module.client.lets_do_bakery.jei;

import java.util.Optional;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.satisfy.bakery.compat.jei.category.BakerStationCategory;
import net.satisfy.bakery.recipe.BakingStationRecipe;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.menu.BakingTeachMenu;

public class BakingTeachRecipeTransferHandler extends TeachRecipeTransferHandler<BakingTeachMenu, BakingStationRecipe, BakingStationRecipe>
{
	public BakingTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends BakingTeachMenu> getContainerClass()
	{
		return BakingTeachMenu.class;
	}

	@Override
	public Optional<MenuType<BakingTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<BakingStationRecipe> getRecipeType()
	{
		return BakerStationCategory.CAKING;
	}

	@Override
	protected BakingStationRecipe getRecipe(BakingTeachMenu menu, BakingStationRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return categoryRecipe;
	}

	@Override
	protected void serializePayload(BakingTeachMenu menu, BakingStationRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		NBTUtils2.serializeCollection(tag, "input", input, ItemStack::serializeNBT);
	}

}
