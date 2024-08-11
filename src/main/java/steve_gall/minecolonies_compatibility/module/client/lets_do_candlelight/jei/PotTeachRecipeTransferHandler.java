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
import net.satisfy.candlelight.compat.jei.category.CookingPotCategory;
import net.satisfy.candlelight.recipe.CookingPotRecipe;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.menu.PotTeachMenu;

public class PotTeachRecipeTransferHandler extends TeachRecipeTransferHandler<PotTeachMenu, CookingPotRecipe, CookingPotRecipe>
{
	public PotTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends PotTeachMenu> getContainerClass()
	{
		return PotTeachMenu.class;
	}

	@Override
	public Optional<MenuType<PotTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<CookingPotRecipe> getRecipeType()
	{
		return CookingPotCategory.COOKING_POT;
	}

	@Override
	protected CookingPotRecipe getRecipe(PotTeachMenu menu, CookingPotRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return categoryRecipe;
	}

	@Override
	protected void serializePayload(PotTeachMenu menu, CookingPotRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		input = input.subList(1, input.size());
		NBTUtils2.serializeCollection(tag, "input", input, ItemStack::serializeNBT);
	}

}
