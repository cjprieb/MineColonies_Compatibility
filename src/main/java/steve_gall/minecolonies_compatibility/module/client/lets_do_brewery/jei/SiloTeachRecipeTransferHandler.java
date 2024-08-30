package steve_gall.minecolonies_compatibility.module.client.lets_do_brewery.jei;

import java.util.Optional;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.satisfy.brewery.compat.jei.BreweryJEIClientPlugin;
import net.satisfy.brewery.recipe.SiloRecipe;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_compatibility.module.common.lets_do_brewery.menu.SiloTeachMenu;

public class SiloTeachRecipeTransferHandler extends TeachRecipeTransferHandler<SiloTeachMenu, SiloRecipe, SiloRecipe>
{
	public SiloTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends SiloTeachMenu> getContainerClass()
	{
		return SiloTeachMenu.class;
	}

	@Override
	public Optional<MenuType<SiloTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<SiloRecipe> getRecipeType()
	{
		return BreweryJEIClientPlugin.DRYING_TYPE;
	}

	@Override
	protected SiloRecipe getRecipe(SiloTeachMenu menu, SiloRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return categoryRecipe;
	}

	@Override
	protected void serializePayload(SiloTeachMenu menu, SiloRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		tag.put("input", input.get(0).serializeNBT());
	}

}
