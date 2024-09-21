package steve_gall.minecolonies_compatibility.module.common.refinedstorage;

import com.refinedmods.refinedstorage.blockentity.NetworkNodeBlockEntity;
import com.refinedmods.refinedstorage.blockentity.data.BlockEntitySynchronizationSpec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import steve_gall.minecolonies_compatibility.module.common.refinedstorage.init.ModuleBlockEntities;

public class CitizenGridBlockEntity extends NetworkNodeBlockEntity<CitizenGridNetworkNode>
{
	public static final BlockEntitySynchronizationSpec SYNC_SPEC = BlockEntitySynchronizationSpec.builder()//
			.addWatchedParameter(REDSTONE_MODE)//
			.build();

	public CitizenGridBlockEntity(BlockPos pos, BlockState state)
	{
		super(ModuleBlockEntities.CITIZEN_GRID.get(), pos, state, SYNC_SPEC, CitizenGridNetworkNode.class);
	}

	@Override
	public CitizenGridNetworkNode createNode(Level level, BlockPos pos)
	{
		return new CitizenGridNetworkNode(level, pos);
	}

}
