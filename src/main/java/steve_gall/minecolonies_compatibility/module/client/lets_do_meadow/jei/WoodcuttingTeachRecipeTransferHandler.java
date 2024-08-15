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
import net.satisfyu.meadow.recipes.woodcutting.WoodcuttingRecipe;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_compatibility.module.common.lets_do_meadow.menu.WoodcuttingTeachMenu;

public class WoodcuttingTeachRecipeTransferHandler extends TeachRecipeTransferHandler<WoodcuttingTeachMenu, WoodcuttingRecipe, IGenericRecipe>
{
	private final RecipeType<IGenericRecipe> recipeType;

	public WoodcuttingTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper, RecipeType<IGenericRecipe> recipeType)
	{
		super(recipeTransferHandlerHelper);

		this.recipeType = recipeType;
	}

	@Override
	public Class<? extends WoodcuttingTeachMenu> getContainerClass()
	{
		return WoodcuttingTeachMenu.class;
	}

	@Override
	public Optional<MenuType<WoodcuttingTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<IGenericRecipe> getRecipeType()
	{
		return this.recipeType;
	}

	@Override
	protected WoodcuttingRecipe getRecipe(WoodcuttingTeachMenu menu, IGenericRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		var r = player.level.getRecipeManager().byKey(categoryRecipe.getRecipeId()).orElse(null);
		return r instanceof WoodcuttingRecipe recipe ? recipe : null;
	}

	@Override
	protected void serializePayload(WoodcuttingTeachMenu menu, WoodcuttingRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		tag.put("input", input.get(0).serializeNBT());
	}

}
