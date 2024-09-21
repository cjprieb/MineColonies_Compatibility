package steve_gall.minecolonies_compatibility.module.client.ae2;

import appeng.client.gui.AEBaseScreen;
import appeng.client.gui.style.ScreenStyle;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import steve_gall.minecolonies_compatibility.module.common.ae2.CitizenTerminalMenu;
import steve_gall.minecolonies_compatibility.module.common.ae2.CitizenTerminalPart;

public class CitizenTerminalScreen extends AEBaseScreen<CitizenTerminalMenu>
{
	public CitizenTerminalScreen(CitizenTerminalMenu menu, Inventory playerInventory, Component title, ScreenStyle style)
	{
		super(menu, playerInventory, title, style);

		var part = (CitizenTerminalPart) this.getMenu().getTarget();
		this.setTextContent(TEXT_ID_DIALOG_TITLE, part.getPartItem().asItem().getDescription());
	}

}
