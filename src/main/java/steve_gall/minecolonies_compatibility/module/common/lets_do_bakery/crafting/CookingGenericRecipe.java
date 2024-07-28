package steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.crafting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.satisfy.bakery.recipe.CookingPotRecipe;
import net.satisfy.bakery.registry.ObjectRegistry;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleGenericRecipe;

public class CookingGenericRecipe extends SimpleGenericRecipe
{
	private final List<ItemStack> container;

	public CookingGenericRecipe(CookingPotRecipe recipe, RegistryAccess registryAccess)
	{
		super(recipe, registryAccess);

		this.container = Arrays.asList(recipe.getContainer());
	}

	public CookingGenericRecipe(ResourceLocation recipeId, List<List<ItemStack>> ingredients, ItemStack container, ItemStack output)
	{
		super(recipeId, ingredients, output);

		this.container = ImmutableList.of(container);
	}

	@Override
	public @NotNull List<List<ItemStack>> getInputs()
	{
		var list = new ArrayList<List<ItemStack>>();
		list.addAll(super.getInputs());

		if (this.container.size() > 0)
		{
			list.add(this.container);
		}

		return list;
	}

	@Override
	public @NotNull Block getIntermediate()
	{
		return ObjectRegistry.SMALL_COOKING_POT.get();
	}

}
