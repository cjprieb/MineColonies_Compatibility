package steve_gall.minecolonies_compatibility.module.client.refinedstorage;

import com.refinedmods.refinedstorage.blockentity.NetworkNodeBlockEntity;
import com.refinedmods.refinedstorage.screen.BaseScreen;
import com.refinedmods.refinedstorage.screen.widget.sidebutton.RedstoneModeSideButton;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.refinedstorage.CitizenGridContainerMenu;

public class CitizenGridScreen extends BaseScreen<CitizenGridContainerMenu>
{
	private static final ResourceLocation TEXTURE = MineColoniesCompatibility.rl("textures/gui/citizen_grid.png");
	private static final Component TEXT_INVENTORY = Component.translatable("container.inventory");

	public CitizenGridScreen(CitizenGridContainerMenu containerMenu, Inventory inventory, Component title)
	{
		super(containerMenu, 176, 137, inventory, title);
	}

	@Override
	public void onPostInit(int x, int y)
	{
		this.addSideButton(new RedstoneModeSideButton(this, NetworkNodeBlockEntity.REDSTONE_MODE));
	}

	@Override
	public void renderBackground(GuiGraphics graphics, int x, int y, int mouseX, int mouseY)
	{
		graphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
	}

	@Override
	public void renderForeground(GuiGraphics graphics, int mouseX, int mouseY)
	{
		this.renderString(graphics, 7, 7, this.title.getString());
		this.renderString(graphics, 7, 43, TEXT_INVENTORY.getString());
	}

	@Override
	public void tick(int x, int y)
	{

	}

}
