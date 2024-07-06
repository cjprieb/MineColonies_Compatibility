package steve_gall.minecolonies_compatibility.core.common.network.message;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import steve_gall.minecolonies_compatibility.api.common.building.module.IRestrictableModule;
import steve_gall.minecolonies_compatibility.api.common.building.module.IRestrictableModuleView;

public class RestrictSetAreaMessage extends BuildingModuleMessage
{
	private final BlockPos pos1;
	private final BlockPos pos2;

	public RestrictSetAreaMessage(IRestrictableModuleView module, BlockPos pos1, BlockPos pos2)
	{
		super(module);

		this.pos1 = pos1;
		this.pos2 = pos2;
	}

	public RestrictSetAreaMessage(FriendlyByteBuf buffer)
	{
		super(buffer);

		this.pos1 = buffer.readBlockPos();
		this.pos2 = buffer.readBlockPos();
	}

	@Override
	public void encode(FriendlyByteBuf buffer)
	{
		super.encode(buffer);

		buffer.writeBlockPos(this.pos1);
		buffer.writeBlockPos(this.pos2);
	}

	@Override
	public void handle(NetworkEvent.Context context)
	{
		super.handle(context);

		if (this.getModule() instanceof IRestrictableModule module)
		{
			module.setRestrictArea(this.pos1, this.pos2);
			module.markDirty();
		}

	}

	public BlockPos getRestrictAreaPos1()
	{
		return this.pos1;
	}

	public BlockPos getRestrictAreaPos2()
	{
		return this.pos2;
	}

}
