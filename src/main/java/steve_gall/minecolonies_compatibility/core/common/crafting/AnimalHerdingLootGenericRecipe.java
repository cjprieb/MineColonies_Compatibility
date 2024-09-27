package steve_gall.minecolonies_compatibility.core.common.crafting;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.api.util.OptionalPredicate;
import com.minecolonies.api.util.constant.IToolType;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

public class AnimalHerdingLootGenericRecipe implements IGenericRecipe
{
	private final Animal animal;
	private final ResourceLocation entityType;
	private final ResourceLocation lootTable;
	private final List<List<ItemStack>> breedingItems;
	private final IToolType toolType;

	public AnimalHerdingLootGenericRecipe(Animal animal, List<List<ItemStack>> breedingItems, IToolType toolType)
	{
		this.animal = animal;
		this.entityType = ForgeRegistries.ENTITY_TYPES.getKey(animal.getType());
		this.lootTable = animal.getLootTable();
		this.breedingItems = breedingItems.stream().map(l -> l.stream().map(ItemStack::copy).toList()).toList();
		this.toolType = toolType;
	}

	@Override
	public int getGridSize()
	{
		return 0;
	}

	@Override
	public @Nullable ResourceLocation getRecipeId()
	{
		return this.entityType;
	}

	@Override
	public @NotNull ItemStack getPrimaryOutput()
	{
		return ItemStack.EMPTY;
	}

	@Override
	public @NotNull List<ItemStack> getAllMultiOutputs()
	{
		return Collections.emptyList();
	}

	@Override
	public @NotNull List<ItemStack> getAdditionalOutputs()
	{
		return Collections.emptyList();
	}

	@Override
	public @NotNull List<List<ItemStack>> getInputs()
	{
		return this.breedingItems;
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
		return Blocks.AIR;
	}

	@Override
	public @Nullable ResourceLocation getLootTable()
	{
		return this.lootTable;
	}

	@Override
	public @NotNull IToolType getRequiredTool()
	{
		return this.toolType;
	}

	@Override
	public @Nullable LivingEntity getRequiredEntity()
	{
		return this.animal;
	}

	@Override
	public @NotNull List<Component> getRestrictions()
	{
		return Collections.emptyList();
	}

	@Override
	public int getLevelSort()
	{
		return 0;
	}

}
