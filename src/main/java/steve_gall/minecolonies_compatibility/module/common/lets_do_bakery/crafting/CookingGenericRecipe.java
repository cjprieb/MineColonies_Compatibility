package steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.crafting;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import satisfy.bakery.recipe.CookingPotRecipe;
import satisfy.bakery.registry.ObjectRegistry;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleContainerGenericRecipe;

public class CookingGenericRecipe extends SimpleContainerGenericRecipe
{
	public CookingGenericRecipe(CookingPotRecipe recipe)
	{
		super(recipe, Arrays.asList(recipe.getContainer()));
	}

	public CookingGenericRecipe(ResourceLocation recipeId, List<List<ItemStack>> ingredients, List<ItemStack> container, ItemStack output)
	{
		super(recipeId, ingredients, container, output);
	}

	@Override
	public @NotNull Block getIntermediate()
	{
		return ObjectRegistry.SMALL_COOKING_POT.get();
	}

}
