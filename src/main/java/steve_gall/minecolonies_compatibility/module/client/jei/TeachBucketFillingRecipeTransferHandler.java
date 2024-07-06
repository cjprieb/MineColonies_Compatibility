package steve_gall.minecolonies_compatibility.module.client.jei;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.crafting.IGenericRecipe;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.core.common.crafting.BucketFillingGenericRecipe;
import steve_gall.minecolonies_compatibility.core.common.crafting.BucketFillingRecipeStorage;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachBucketFillingMenu;
import steve_gall.minecolonies_compatibility.core.common.network.message.JEIRecipeTransferMessage;

public class TeachBucketFillingRecipeTransferHandler implements IRecipeTransferHandler<TeachBucketFillingMenu, IGenericRecipe>
{
	private final IRecipeTransferHandlerHelper recipeTransferHandlerHelper;
	private final RecipeType<IGenericRecipe> recipeType;

	public TeachBucketFillingRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper, RecipeType<IGenericRecipe> recipeType)
	{
		this.recipeTransferHandlerHelper = recipeTransferHandlerHelper;
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
	@Nullable
	public IRecipeTransferError transferRecipe(TeachBucketFillingMenu menu, IGenericRecipe recipe, IRecipeSlotsView recipeSlots, Player player, boolean maxTransfer, boolean doTransfer)
	{
		if (recipe instanceof BucketFillingGenericRecipe fillingRecipe)
		{
			if (doTransfer)
			{
				var r = new BucketFillingRecipeStorage(fillingRecipe.getEmptyBucket(), fillingRecipe.getFluid(), fillingRecipe.getFilledBucket());
				MineColoniesCompatibility.network().sendToServer(new JEIRecipeTransferMessage<>(menu, r, new CompoundTag()));
			}

			return null;
		}

		return this.recipeTransferHandlerHelper.createInternalError();
	}

}
