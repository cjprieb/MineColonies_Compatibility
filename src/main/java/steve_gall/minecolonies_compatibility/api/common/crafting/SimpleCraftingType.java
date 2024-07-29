package steve_gall.minecolonies_compatibility.api.common.crafting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.api.crafting.RecipeCraftingType;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class SimpleCraftingType<CONTAINER extends Container, RECIPE extends Recipe<CONTAINER>, GENERIC_RECIPE extends IGenericRecipe> extends RecipeCraftingType<CONTAINER, RECIPE>
{
	@NotNull
	private final Supplier<RecipeType<RECIPE>> recipeType;
	@NotNull
	private final BiFunction<RECIPE, RegistryAccess, GENERIC_RECIPE> genericRecipeFunc;

	public SimpleCraftingType(@NotNull ResourceLocation id, @NotNull Supplier<RecipeType<RECIPE>> recipeType, @NotNull BiFunction<RECIPE, RegistryAccess, GENERIC_RECIPE> genericRecipeFunc)
	{
		super(id, null, null);

		this.recipeType = recipeType;
		this.genericRecipeFunc = genericRecipeFunc;
	}

	@Override
	public @NotNull List<IGenericRecipe> findRecipes(@NotNull RecipeManager recipeManager, @Nullable Level world)
	{
		var recipes = new ArrayList<IGenericRecipe>();
		var registryAccess = world.registryAccess();

		for (var recipe : recipeManager.getAllRecipesFor(this.getRecipeType()))
		{
			if (this.testRecipe(recipe, registryAccess))
			{
				recipes.add(this.createGenericRecipe(recipe, registryAccess));
			}

		}

		return recipes;
	}

	protected boolean testRecipe(@NotNull RECIPE recipe, @NotNull RegistryAccess registryAccess)
	{
		return true;
	}

	@NotNull
	protected GENERIC_RECIPE createGenericRecipe(@NotNull RECIPE recipe, @NotNull RegistryAccess registryAccess)
	{
		return this.genericRecipeFunc.apply(recipe, registryAccess);
	}

	@NotNull
	public RecipeType<RECIPE> getRecipeType()
	{
		return this.recipeType.get();
	}

}
