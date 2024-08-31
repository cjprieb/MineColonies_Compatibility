package steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.crafting;

import java.util.Collections;
import java.util.List;

import com.minecolonies.api.crafting.ItemStorage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleRecipeStorage;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;

public class ApplePressRecipeStorage extends SimpleRecipeStorage<ApplePressGenericRecipe>
{
	public static final ResourceLocation ID = MineColoniesCompatibility.rl("lets_do_vinery_apple_press");

	public ApplePressRecipeStorage(CompoundTag tag)
	{
		super(tag);
	}

	public ApplePressRecipeStorage(ResourceLocation recipeId, List<ItemStorage> ingredients, ItemStack output)
	{
		super(recipeId, ingredients, output);
	}

	@Override
	public ResourceLocation getId()
	{
		return ID;
	}

	@Override
	public List<ItemStack> getSecondaryOutputs()
	{
		return Collections.emptyList();
	}

	@Override
	protected GenericRecipeFactory<ApplePressGenericRecipe> getGenericRecipeFactory()
	{
		return ApplePressGenericRecipe::new;
	}

}
