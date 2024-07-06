package steve_gall.minecolonies_compatibility.core.common.crafting;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import com.minecolonies.api.crafting.ItemStorage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import steve_gall.minecolonies_compatibility.api.common.crafting.GenericedRecipeStorage;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;

public class BucketFillingRecipeStorage extends GenericedRecipeStorage<BucketFillingGenericRecipe>
{
	public static final ResourceLocation ID = MineColoniesCompatibility.rl("bucket_filling");
	public static String TAG_EMPTY_BUCKET = "emptyBucket";
	public static String TAG_FLUID = "fluid";
	public static String TAG_FILLED_BUCKET = "filledBucket";

	public static void serialize(CompoundTag tag, BucketFillingRecipeStorage recipe)
	{
		tag.put(TAG_EMPTY_BUCKET, recipe.emptyBucket.serializeNBT());
		tag.putString(TAG_FLUID, ForgeRegistries.FLUIDS.getKey(recipe.fluid).toString());
		tag.put(TAG_FILLED_BUCKET, recipe.filledBucket.serializeNBT());
	}

	public static BucketFillingRecipeStorage deserialize(CompoundTag tag)
	{
		var emptyBucket = ItemStack.of(tag.getCompound(TAG_EMPTY_BUCKET));
		var fluidId = new ResourceLocation(tag.getString(TAG_FLUID));
		var fluid = ForgeRegistries.FLUIDS.getValue(fluidId);
		var filledBucket = ItemStack.of(tag.getCompound(TAG_FILLED_BUCKET));
		return new BucketFillingRecipeStorage(emptyBucket, fluid, filledBucket);
	}

	private final ItemStack emptyBucket;
	private final Fluid fluid;
	private final ItemStack filledBucket;

	private final List<ItemStorage> input;
	private final BucketFillingGenericRecipe recipe;

	public BucketFillingRecipeStorage(ItemStack emptyBucket, Fluid fluid, ItemStack filledBucket)
	{
		this.emptyBucket = emptyBucket;
		this.fluid = fluid;
		this.filledBucket = filledBucket;

		this.input = Collections.singletonList(new ItemStorage(emptyBucket));
		this.recipe = new BucketFillingGenericRecipe(emptyBucket, fluid, filledBucket);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.emptyBucket.getItem().hashCode(), this.fluid.hashCode(), this.filledBucket.getItem().hashCode());
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		else if (o instanceof BucketFillingRecipeStorage other)
		{
			return ItemStack.matches(this.emptyBucket, other.emptyBucket)//
					&& this.fluid == other.fluid//
					&& ItemStack.matches(this.filledBucket, other.filledBucket);
		}

		return false;
	}

	@Override
	public ResourceLocation getId()
	{
		return ID;
	}

	public ItemStack getEmptyBucket()
	{
		return this.emptyBucket;
	}

	public Fluid getFluid()
	{
		return this.fluid;
	}

	public ItemStack getFilledBucket()
	{
		return this.filledBucket;
	}

	@Override
	public List<ItemStorage> getInput()
	{
		return this.input;
	}

	@Override
	public @NotNull BucketFillingGenericRecipe getGenericRecipe()
	{
		return this.recipe;
	}

}
