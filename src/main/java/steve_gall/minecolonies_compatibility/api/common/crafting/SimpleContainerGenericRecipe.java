package steve_gall.minecolonies_compatibility.api.common.crafting;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableList;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

public class SimpleContainerGenericRecipe extends SimpleGenericRecipe
{
	private final List<ItemStack> container;

	public SimpleContainerGenericRecipe(Recipe<?> recipe, List<ItemStack> container)
	{
		super(recipe);

		this.container = ImmutableList.copyOf(container);
	}

	public SimpleContainerGenericRecipe(ResourceLocation recipeId, List<List<ItemStack>> ingredients, List<ItemStack> container, ItemStack output)
	{
		super(recipeId, ingredients, output);

		this.container = ImmutableList.copyOf(container);
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

}
