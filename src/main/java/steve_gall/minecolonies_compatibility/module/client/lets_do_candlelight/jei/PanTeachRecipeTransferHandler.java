package steve_gall.minecolonies_compatibility.module.client.lets_do_candlelight.jei;

import java.util.Optional;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.satisfy.candlelight.compat.jei.category.CookingPanCategory;
import net.satisfy.candlelight.recipe.CookingPanRecipe;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.menu.PanTeachMenu;

public class PanTeachRecipeTransferHandler extends TeachRecipeTransferHandler<PanTeachMenu, CookingPanRecipe, CookingPanRecipe>
{
	public PanTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends PanTeachMenu> getContainerClass()
	{
		return PanTeachMenu.class;
	}

	@Override
	public Optional<MenuType<PanTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<CookingPanRecipe> getRecipeType()
	{
		return CookingPanCategory.COOKING_PAN;
	}

	@Override
	protected CookingPanRecipe getRecipe(PanTeachMenu menu, CookingPanRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return categoryRecipe;
	}

	@Override
	protected void serializePayload(PanTeachMenu menu, CookingPanRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		input = input.subList(1, input.size());
		NBTUtils2.serializeCollection(tag, "input", input, ItemStack::serializeNBT);
	}

}
