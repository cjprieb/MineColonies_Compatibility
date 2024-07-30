package steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.building.module;

import java.util.Collections;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.api.crafting.IRecipeStorage;
import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import satisfy.bakery.entity.CookingPotEntity;
import steve_gall.minecolonies_compatibility.api.common.building.module.AbstractCraftingModuleWithExternalWorkingBlocks;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.init.ModuleCraftingTypes;

public class CookingCraftingModule extends AbstractCraftingModuleWithExternalWorkingBlocks
{
	public CookingCraftingModule(JobEntry jobEntry)
	{
		super(jobEntry);
	}

	@Override
	public void improveRecipe(IRecipeStorage recipe, int count, ICitizenData citizen)
	{

	}

	@Override
	public boolean isWorkingBlock(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state)
	{
		return level.getBlockEntity(pos) instanceof CookingPotEntity cookingPot && cookingPot.isBeingBurned();
	}

	@Override
	public boolean needWorkingBlock(@NotNull IRecipeStorage recipeStorage)
	{
		return true;
	}

	@Override
	public @NotNull BlockPos getWalkingPosition(@NotNull BlockPos pos)
	{
		return pos;
	}

	@Override
	public @NotNull BlockPos getParticlePosition(@NotNull BlockPos pos)
	{
		return pos.below();
	}

	@Override
	public @NotNull String getId()
	{
		return "lets_do_bakery_cooking";
	}

	@Override
	public Set<CraftingType> getSupportedCraftingTypes()
	{
		return Collections.singleton(ModuleCraftingTypes.COOKING.get());
	}

	@Override
	public boolean isRecipeCompatible(@NotNull IGenericRecipe recipe)
	{
		return true;
	}

	@Override
	@NotNull
	public Component getWorkingBlockNotFoundMessage()
	{
		return Component.translatable("minecolonies_compatibility.interaction.no_lets_do_heated_cooking_pot");
	}

}
