package steve_gall.minecolonies_compatibility.core.client.gui;

import java.util.Collection;

import net.minecraft.network.chat.Component;

public interface ItemIconExtension
{
	void minecolonies_compatibility$addTooltip(Component component);

	void minecolonies_compatibility$addTooltip(Collection<? extends Component> component);
}
