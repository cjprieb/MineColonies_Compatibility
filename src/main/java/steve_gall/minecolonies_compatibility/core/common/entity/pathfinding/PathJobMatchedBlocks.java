package steve_gall.minecolonies_compatibility.core.common.entity.pathfinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import steve_gall.minecolonies_tweaks.api.common.pathfinding.SimplePathJob;

public class PathJobMatchedBlocks extends SimplePathJob<MatchBlocksPathResult>
{
	@NotNull
	private final MatchPredicate predicate;

	public PathJobMatchedBlocks(@NotNull Level level, @NotNull BlockPos start, @NotNull BlockPos home, int range, @Nullable Mob entity, @NotNull MatchPredicate predicate)
	{
		super(level, start, home, range, entity, new MatchBlocksPathResult());
		this.predicate = predicate;
	}

	public PathJobMatchedBlocks(@NotNull Level level, @NotNull BlockPos start, @NotNull BoundingBox restrictionBox, @Nullable Mob entity, @NotNull MatchPredicate predicate)
	{
		super(level, start, restrictionBox, entity, new MatchBlocksPathResult());
		this.predicate = predicate;
	}

	@Override
	protected boolean testPos(@NotNull MutableBlockPos pos)
	{
		if (this.predicate.match(this.world, pos))
		{
			this.getResult().positions.add(pos.immutable());
			return true;
		}
		else
		{
			return false;
		}

	}

	@FunctionalInterface
	public static interface MatchPredicate
	{
		boolean match(@NotNull LevelReader level, @NotNull BlockPos.MutableBlockPos position);
	}

}
