package steve_gall.minecolonies_compatibility.module.common.ae2;

import appeng.api.parts.IPartItem;
import appeng.api.parts.IPartModel;
import appeng.items.parts.PartModels;
import appeng.menu.MenuOpener;
import appeng.menu.locator.MenuLocators;
import appeng.parts.PartModel;
import appeng.parts.reporting.AbstractDisplayPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.ae2.init.ModuleMenuTypes;

public class CitizenTerminalPart extends AbstractDisplayPart
{
	@PartModels
	public static final ResourceLocation MODEL_OFF = MineColoniesCompatibility.rl("part/citizen_terminal_off");
	@PartModels
	public static final ResourceLocation MODEL_ON = MineColoniesCompatibility.rl("part/citizen_terminal_on");

	public static final IPartModel MODELS_OFF = new PartModel(MODEL_BASE, MODEL_OFF, MODEL_STATUS_OFF);
	public static final IPartModel MODELS_ON = new PartModel(MODEL_BASE, MODEL_ON, MODEL_STATUS_ON);
	public static final IPartModel MODELS_HAS_CHANNEL = new PartModel(MODEL_BASE, MODEL_ON, MODEL_STATUS_HAS_CHANNEL);

	public CitizenTerminalPart(IPartItem<?> partItem)
	{
		super(partItem, false);
	}

	@Override
	public boolean onPartActivate(Player player, InteractionHand hand, Vec3 pos)
	{
		if (!super.onPartActivate(player, hand, pos) && !isClientSide())
		{
			MenuOpener.open(ModuleMenuTypes.CITIZEN_TERMINAL.get(), player, MenuLocators.forPart(this));
		}

		return true;
	}

	@Override
	public IPartModel getStaticModels()
	{
		return this.selectModel(MODELS_OFF, MODELS_ON, MODELS_HAS_CHANNEL);
	}

}
