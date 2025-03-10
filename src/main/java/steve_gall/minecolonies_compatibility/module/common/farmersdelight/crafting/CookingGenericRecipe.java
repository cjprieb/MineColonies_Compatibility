package steve_gall.minecolonies_compatibility.module.common.farmersdelight.crafting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.api.util.OptionalPredicate;
import com.minecolonies.api.util.constant.IToolType;
import com.minecolonies.api.util.constant.ToolType;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import steve_gall.minecolonies_compatibility.core.common.crafting.GenericRecipeHelper;
import steve_gall.minecolonies_compatibility.core.common.crafting.IngredientHelper;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class CookingGenericRecipe implements IGenericRecipe
{
	private final ResourceLocation recipeId;
	private final List<List<ItemStack>> input;
	private final List<ItemStack> container;
	private final ItemStack output;
	private final List<ItemStack> additionalOutputs;

	public CookingGenericRecipe(CookingPotRecipe recipe)
	{
		this(recipe.getId(), IngredientHelper.getStacksList(recipe.getIngredients()), Collections.singletonList(recipe.getOutputContainer()), recipe.getResultItem());
	}

	public CookingGenericRecipe(ResourceLocation recipeId, List<List<ItemStack>> ingredients, List<ItemStack> container, ItemStack output)
	{
		this.recipeId = recipeId;
		this.input = new ArrayList<>(ingredients);
		this.container = new ArrayList<>();

		if (!container.isEmpty() && !container.stream().allMatch(ItemStack::isEmpty))
		{
			this.container.addAll(container);
		}

		this.output = output;
		this.additionalOutputs = GenericRecipeHelper.getAdditionalOutputs(ingredients, CookingGenericRecipe::getCraftingRemainingStack);
	}

	@Override
	public int getGridSize()
	{
		return 1;
	}

	@Override
	public @Nullable ResourceLocation getRecipeId()
	{
		return this.recipeId;
	}

	@Override
	public @NotNull ItemStack getPrimaryOutput()
	{
		return this.output;
	}

	@Override
	public @NotNull List<ItemStack> getAllMultiOutputs()
	{
		return Collections.singletonList(this.output);
	}

	@Override
	public @NotNull List<ItemStack> getAdditionalOutputs()
	{
		return this.additionalOutputs;
	}

	public static ItemStack getCraftingRemainingStack(ItemStack stack)
	{
		if (stack.hasCraftingRemainingItem())
		{
			return stack.getCraftingRemainingItem();
		}
		else if (CookingPotBlockEntity.INGREDIENT_REMAINDER_OVERRIDES.containsKey(stack.getItem()))
		{
			return CookingPotBlockEntity.INGREDIENT_REMAINDER_OVERRIDES.get(stack.getItem()).getDefaultInstance();
		}
		else
		{
			return ItemStack.EMPTY;
		}

	}

	@Override
	public @NotNull List<List<ItemStack>> getInputs()
	{
		var list = new ArrayList<List<ItemStack>>();
		list.addAll(this.input);

		if (this.container.size() > 0)
		{
			list.add(this.container);
		}

		return list;
	}

	@Override
	public Optional<Boolean> matchesOutput(@NotNull OptionalPredicate<ItemStack> predicate)
	{
		return Optional.empty();
	}

	@Override
	public Optional<Boolean> matchesInput(@NotNull OptionalPredicate<ItemStack> predicate)
	{
		return Optional.empty();
	}

	@Override
	public @NotNull Block getIntermediate()
	{
		return ModBlocks.COOKING_POT.get();
	}

	@Override
	public @Nullable ResourceLocation getLootTable()
	{
		return null;
	}

	@Override
	public @NotNull IToolType getRequiredTool()
	{
		return ToolType.NONE;
	}

	@Override
	public @Nullable LivingEntity getRequiredEntity()
	{
		return null;
	}

	@Override
	public @NotNull List<Component> getRestrictions()
	{
		return Collections.emptyList();
	}

	@Override
	public int getLevelSort()
	{
		return -1;
	}

}
