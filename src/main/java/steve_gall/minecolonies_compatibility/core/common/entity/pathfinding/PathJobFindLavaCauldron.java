package steve_gall.minecolonies_compatibility.core.common.entity.pathfinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import steve_gall.minecolonies_tweaks.api.common.pathfinding.SimplePathJob;

public class PathJobFindLavaCauldron extends SimplePathJob<MatchBlocksPathResult>
{
	public PathJobFindLavaCauldron(@NotNull Level level, @NotNull BlockPos start, @NotNull BlockPos home, int range, @Nullable Mob entity)
	{
		super(level, start, home, range, entity, new MatchBlocksPathResult());
	}

	public PathJobFindLavaCauldron(@NotNull Level level, @NotNull BlockPos start, @NotNull BoundingBox restrictionBox, @Nullable Mob entity)
	{
		super(level, start, restrictionBox, entity, new MatchBlocksPathResult());
	}

	@Override
	protected boolean testPos(@NotNull MutableBlockPos pos)
	{
		if (this.world.getBlockState(pos).getBlock() == Blocks.LAVA_CAULDRON)
		{
			this.getResult().positions.add(pos.immutable());
			return true;
		}
		else
		{
			return false;
		}

	}

}
