package steve_gall.minecolonies_compatibility.core.common.crafting;

import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.lothrazar.cyclic.util.FluidHelpers.FluidAttributes;
import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class BucketFillingCraftingType extends CraftingType
{
	public BucketFillingCraftingType(@NotNull ResourceLocation id)
	{
		super(id);
	}

	@Override
	public @NotNull List<IGenericRecipe> findRecipes(@NotNull RecipeManager recipeManager, @Nullable Level world)
	{
		return Collections.emptyList();
	}

	public static BucketFillingRecipeStorage parse(ItemStack filledBucket)
	{
		return parse(filledBucket, filledBucket.getCraftingRemainingItem());
	}

	public static BucketFillingRecipeStorage parse(ItemStack filledBucket, ItemStack emptyBucket)
	{
		if (filledBucket.getItem() instanceof BucketItem item)
		{
			var fluid = item.getFluid();

			if (!fluid.isSame(Fluids.EMPTY) && fluid.isSource(fluid.defaultFluidState()))
			{
				var tank = new FluidTank(FluidAttributes.BUCKET_VOLUME);
				tank.setFluid(new FluidStack(fluid, tank.getCapacity()));
				var result = FluidUtil.tryFillContainer(emptyBucket, tank, tank.getCapacity(), null, true);

				if (result.isSuccess() && ItemStack.matches(result.getResult(), filledBucket))
				{
					return new BucketFillingRecipeStorage(emptyBucket, fluid, filledBucket);
				}

			}

		}

		return null;

	}

}
