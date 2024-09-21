package steve_gall.minecolonies_compatibility.module.common.storagenetwork;

import com.lothrazar.storagenetwork.block.TileConnectable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import steve_gall.minecolonies_compatibility.module.common.storagenetwork.init.ModuleBlockEntities;

public class CitizenInventoryBlockEntity extends TileConnectable
{
	public CitizenInventoryBlockEntity(BlockPos pos, BlockState state)
	{
		super(ModuleBlockEntities.CITIZEN_INVENTORY.get(), pos, state);
	}

}
