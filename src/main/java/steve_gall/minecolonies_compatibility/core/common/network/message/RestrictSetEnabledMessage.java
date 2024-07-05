package steve_gall.minecolonies_compatibility.core.common.network.message;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import steve_gall.minecolonies_compatibility.api.common.building.module.IRestrictableModule;
import steve_gall.minecolonies_compatibility.api.common.building.module.IRestrictableModuleView;

public class RestrictSetEnabledMessage extends BuildingModuleMessage
{
	private final boolean enabled;

	public RestrictSetEnabledMessage(IRestrictableModuleView module, boolean enabled)
	{
		super(module);

		this.enabled = enabled;
	}

	public RestrictSetEnabledMessage(FriendlyByteBuf buffer)
	{
		super(buffer);

		this.enabled = buffer.readBoolean();
	}

	@Override
	public void encode(FriendlyByteBuf buffer)
	{
		super.encode(buffer);

		buffer.writeBoolean(this.enabled);
	}

	@Override
	public void handle(NetworkEvent.Context context)
	{
		super.handle(context);

		if (this.getModule() instanceof IRestrictableModule module)
		{
			module.setRestrictEnabled(this.enabled);
		}

	}

	public boolean isRestrictEnabled()
	{
		return this.enabled;
	}

}
