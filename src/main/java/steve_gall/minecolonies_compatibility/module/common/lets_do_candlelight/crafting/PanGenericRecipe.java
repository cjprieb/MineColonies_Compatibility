package steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.crafting;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import satisfyu.candlelight.recipe.CookingPanRecipe;
import satisfyu.candlelight.registry.ObjectRegistry;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleContainerGenericRecipe;

public class PanGenericRecipe extends SimpleContainerGenericRecipe
{
	public PanGenericRecipe(CookingPanRecipe recipe)
	{
		super(recipe, Arrays.asList(recipe.getContainer()));
	}

	public PanGenericRecipe(ResourceLocation recipeId, List<List<ItemStack>> ingredients, List<ItemStack> container, ItemStack output)
	{
		super(recipeId, ingredients, container, output);
	}

	@Override
	public @NotNull Block getIntermediate()
	{
		return ObjectRegistry.COOKING_PAN.get();
	}

}
