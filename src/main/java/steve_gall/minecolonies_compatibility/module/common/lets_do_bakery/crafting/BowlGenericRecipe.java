package steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.crafting;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.satisfy.bakery.recipe.CraftingBowlRecipe;
import net.satisfy.bakery.registry.ObjectRegistry;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleGenericRecipe;

public class BowlGenericRecipe extends SimpleGenericRecipe
{
	public BowlGenericRecipe(CraftingBowlRecipe recipe, RegistryAccess registryAccess)
	{
		super(recipe, registryAccess);
	}

	public BowlGenericRecipe(ResourceLocation recipeId, List<List<ItemStack>> ingredients, ItemStack output)
	{
		super(recipeId, ingredients, output);
	}

	@Override
	public @NotNull Block getIntermediate()
	{
		return ObjectRegistry.CRAFTING_BOWL.get();
	}

}
