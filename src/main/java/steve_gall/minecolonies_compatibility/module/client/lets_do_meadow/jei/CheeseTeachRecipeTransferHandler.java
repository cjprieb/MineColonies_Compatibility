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
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_compatibility.module.common.lets_do_meadow.menu.CheeseTeachMenu;

public class CheeseTeachRecipeTransferHandler extends TeachRecipeTransferHandler<CheeseTeachMenu, CheeseFormRecipe, IGenericRecipe>
{
	private final RecipeType<IGenericRecipe> recipeType;

	public CheeseTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper, RecipeType<IGenericRecipe> recipeType)
	{
		super(recipeTransferHandlerHelper);

		this.recipeType = recipeType;
	}

	@Override
	public Class<? extends CheeseTeachMenu> getContainerClass()
	{
		return CheeseTeachMenu.class;
	}

	@Override
	public Optional<MenuType<CheeseTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<IGenericRecipe> getRecipeType()
	{
		return this.recipeType;
	}

	@Override
	protected CheeseFormRecipe getRecipe(CheeseTeachMenu menu, IGenericRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		var r = player.level.getRecipeManager().byKey(categoryRecipe.getRecipeId()).orElse(null);
		return r instanceof CheeseFormRecipe recipe ? recipe : null;
	}

	@Override
	protected void serializePayload(CheeseTeachMenu menu, CheeseFormRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		NBTUtils2.serializeCollection(tag, "input", input, ItemStack::serializeNBT);
	}

}
