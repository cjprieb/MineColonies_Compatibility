package steve_gall.minecolonies_compatibility.module.common.farmersdelight.building.module;

import java.util.Collections;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableSet;
import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.api.colony.jobs.ModJobs;
import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.api.crafting.IRecipeStorage;
import com.minecolonies.api.crafting.registry.CraftingType;
import com.minecolonies.api.util.constant.IToolType;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import steve_gall.minecolonies_compatibility.api.common.building.module.AbstractCraftingModuleWithExternalWorkingBlocks;
import steve_gall.minecolonies_compatibility.core.common.util.InteractionMessageHelper;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.init.ModuleCraftingTypes;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class CuttingCraftingModule extends AbstractCraftingModuleWithExternalWorkingBlocks
{
	private final IToolType toolType;

	public CuttingCraftingModule(JobEntry jobEntry, IToolType toolType)
	{
		super(jobEntry);

		this.toolType = toolType;
	}

	@Override
	public void improveRecipe(IRecipeStorage recipe, int count, ICitizenData citizen)
	{

	}

	@Override
	public void serializeToView(@NotNull FriendlyByteBuf buf)
	{
		super.serializeToView(buf);

		buf.writeUtf(this.getToolType().getName());
	}

	@Override
	public boolean isWorkingBlock(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state)
	{
		return state.getBlock() == ModBlocks.CUTTING_BOARD.get();
	}

	@Override
	public @NotNull BlockPos getParticlePosition(@NotNull BlockPos pos)
	{
		return super.getParticlePosition(pos).below();
	}

	@Override
	public @NotNull String getId()
	{
		return "farmers_cutting_" + this.getToolType().getName();
	}

	@Override
	public Set<CraftingType> getSupportedCraftingTypes()
	{
		if (this.jobEntry == ModJobs.cookassistant.get())
		{
			if (this.building != null && this.building.getBuildingLevel() < 3)
			{
				return ImmutableSet.of();
			}

		}

		return Collections.singleton(ModuleCraftingTypes.CUTTING.get());
	}

	@Override
	public boolean isRecipeCompatible(@NotNull IGenericRecipe recipe)
	{
		return recipe.getRequiredTool() == this.getToolType();
	}

	@Override
	public @NotNull Component getWorkingBlockNotFoundMessage(@NotNull IRecipeStorage recipeStorage)
	{
		return InteractionMessageHelper.getWorkingBlockNotFound(ModBlocks.CUTTING_BOARD.get());
	}

	public IToolType getToolType()
	{
		return this.toolType;
	}

}
