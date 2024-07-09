package steve_gall.minecolonies_compatibility.module.client.jei;

import java.util.Optional;

import com.minecolonies.api.crafting.IGenericRecipe;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import steve_gall.minecolonies_compatibility.core.common.crafting.BucketFillingGenericRecipe;
import steve_gall.minecolonies_compatibility.core.common.crafting.BucketFillingRecipeStorage;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachBucketFillingMenu;

public class TeachBucketFillingRecipeTransferHandler extends TeachRecipeTransferHandler<TeachBucketFillingMenu, BucketFillingRecipeStorage, IGenericRecipe>
{
	private final RecipeType<IGenericRecipe> recipeType;

	public TeachBucketFillingRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper, RecipeType<IGenericRecipe> recipeType)
	{
		super(recipeTransferHandlerHelper);
		this.recipeType = recipeType;
	}

	@Override
	public Class<? extends TeachBucketFillingMenu> getContainerClass()
	{
		return TeachBucketFillingMenu.class;
	}

	@Override
	public Optional<MenuType<TeachBucketFillingMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<IGenericRecipe> getRecipeType()
	{
		return this.recipeType;
	}

	@Override
	protected BucketFillingRecipeStorage getRecipe(TeachBucketFillingMenu menu, IGenericRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		if (categoryRecipe instanceof BucketFillingGenericRecipe fillingRecipe)
		{
			return new BucketFillingRecipeStorage(fillingRecipe.getEmptyBucket(), fillingRecipe.getFluid(), fillingRecipe.getFluidTag(), fillingRecipe.getFilledBucket());
		}
		else
		{
			return null;
		}

	}

	@Override
	protected void serializePayload(TeachBucketFillingMenu menu, BucketFillingRecipeStorage recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{

	}

}
