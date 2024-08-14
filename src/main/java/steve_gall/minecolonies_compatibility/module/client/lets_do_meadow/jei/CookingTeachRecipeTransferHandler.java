package steve_gall.minecolonies_compatibility.module.client.lets_do_meadow.jei;

import java.util.Optional;

import com.minecolonies.api.crafting.IGenericRecipe;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_compatibility.module.common.lets_do_meadow.menu.CookingTeachMenu;

public class CookingTeachRecipeTransferHandler extends TeachRecipeTransferHandler<CookingTeachMenu, CookingCauldronRecipe, IGenericRecipe>
{
	private final RecipeType<IGenericRecipe> recipeType;

	public CookingTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper, RecipeType<IGenericRecipe> recipeType)
	{
		super(recipeTransferHandlerHelper);

		this.recipeType = recipeType;
	}

	@Override
	public Class<? extends CookingTeachMenu> getContainerClass()
	{
		return CookingTeachMenu.class;
	}

	@Override
	public Optional<MenuType<CookingTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<IGenericRecipe> getRecipeType()
	{
		return this.recipeType;
	}

	@Override
	protected CookingCauldronRecipe getRecipe(CookingTeachMenu menu, IGenericRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		var r = player.level.getRecipeManager().byKey(categoryRecipe.getRecipeId()).orElse(null);
		return r instanceof CookingCauldronRecipe recipe ? recipe : null;
	}

	@Override
	protected void serializePayload(CookingTeachMenu menu, CookingCauldronRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		NBTUtils2.serializeCollection(tag, "input", input, ItemStack::serializeNBT);
	}

}
