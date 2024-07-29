package steve_gall.minecolonies_compatibility.api.common.entity.pathfinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.minecolonies.core.entity.pathfinding.MNode;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import steve_gall.minecolonies_tweaks.api.common.pathfinding.SimplePathJob;

public class PathJobFindWorkingBlocks<RESULT extends WorkingBlocksPathResult> extends SimplePathJob<RESULT>
{
	private int searchHeight = 2;

	public PathJobFindWorkingBlocks(@NotNull Level level, @NotNull BlockPos start, @NotNull BlockPos home, int range, @Nullable Mob entity, @NotNull RESULT result)
	{
		super(level, start, home, range, entity, result);
	}

	public PathJobFindWorkingBlocks(@NotNull Level level, @NotNull BlockPos start, @NotNull BoundingBox restrictionBox, @Nullable Mob entity, @NotNull RESULT result)
	{
		super(level, start, restrictionBox, entity, result);
	}

	@NotNull
	public LevelReader getLevel()
	{
		return this.world;
	}

	@Override
	protected double computeHeuristic(@NotNull BlockPos pos)
	{
		return 0.0D;
	}

	@Override
	protected boolean isPassable(BlockPos pos, boolean head, MNode parent)
	{
		return true;
	}

	@Override
	protected boolean isPassable(@NotNull BlockState block, BlockPos pos, MNode parent, boolean head)
	{
		return true;
	}

	@Override
	protected boolean testPos(@NotNull MutableBlockPos pos)
	{
		var y = pos.getY();

		for (var i = 0; i < this.searchHeight - 1; i++)
		{
			this.getResult().test(this, pos.setY(y + i));
		}

		return false;
	}

	public int getSearchHeight()
	{
		return this.searchHeight;
	}

	public void setSearchHeight(int searchHeight)
	{
		this.searchHeight = Math.max(searchHeight, 1);
	}

}
