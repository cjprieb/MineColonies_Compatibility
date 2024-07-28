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
import net.satisfy.bakery.compat.jei.category.CraftingBowlCategory;
import net.satisfy.bakery.recipe.CraftingBowlRecipe;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.menu.BowlTeachMenu;

public class BowlTeachRecipeTransferHandler extends TeachRecipeTransferHandler<BowlTeachMenu, CraftingBowlRecipe, CraftingBowlRecipe>
{
	public BowlTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends BowlTeachMenu> getContainerClass()
	{
		return BowlTeachMenu.class;
	}

	@Override
	public Optional<MenuType<BowlTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<CraftingBowlRecipe> getRecipeType()
	{
		return CraftingBowlCategory.DOUGHING;
	}

	@Override
	protected CraftingBowlRecipe getRecipe(BowlTeachMenu menu, CraftingBowlRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return categoryRecipe;
	}

	@Override
	protected void serializePayload(BowlTeachMenu menu, CraftingBowlRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		NBTUtils2.serializeCollection(tag, "input", input, ItemStack::serializeNBT);
	}

}
