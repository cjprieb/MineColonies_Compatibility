package steve_gall.minecolonies_compatibility.mixin.client.minecolonies;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.ldtteam.blockui.Pane;
import com.ldtteam.blockui.PaneBuilders;
import com.ldtteam.blockui.controls.Text;
import com.ldtteam.blockui.views.BOWindow;
import com.minecolonies.api.colony.buildings.modules.settings.ISetting;
import com.minecolonies.api.colony.buildings.modules.settings.ISettingKey;
import com.minecolonies.api.colony.buildings.modules.settings.ISettingsModuleView;
import com.minecolonies.api.colony.buildings.views.IBuildingView;
import com.minecolonies.core.client.gui.modules.SettingsModuleWindow;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fml.ModList;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.core.common.util.ReflectionUtils;

@Mixin(targets = "com.minecolonies.core.client.gui.modules.SettingsModuleWindow$1", remap = false)
public class SettingsModuleWindow1Mixin
{
	@Unique
	private final SettingsModuleWindow minecolonies_compatibility$this$0 = ReflectionUtils.getOuter(this);

	@Redirect(method = "updateElement", remap = false, at = @At(value = "INVOKE", target = "com/minecolonies/api/colony/buildings/modules/settings/ISetting.render"))
	private <S> void setting_render(ISetting<S> setting, ISettingKey<S> key, Pane rowPane, ISettingsModuleView settingsModuleView, IBuildingView building, BOWindow window)
	{
		setting.render(key, rowPane, settingsModuleView, building, window);

		var id = key.getUniqueId();

		if (id.getNamespace().equals(MineColoniesCompatibility.MOD_ID))
		{
			var rowDescriptionField = rowPane.findPaneOfTypeByID("desc", Text.class);

			if (rowDescriptionField != null && rowDescriptionField.getHoverPane() == null)
			{
				var ttBuilder = PaneBuilders.tooltipBuilder().hoverPane(rowDescriptionField);
				ttBuilder.append(Component.translatable("com.minecolonies.coremod.setting." + id.getNamespace() + ".tooltip", ModList.get().getModContainerById(id.getNamespace()).get().getModInfo().getDisplayName()));

				var settingTooltipKey = "com.minecolonies.coremod.setting." + id.toString() + ".tooltip";

				if (I18n.exists(settingTooltipKey))
				{
					ttBuilder.newLine();
					ttBuilder.append(Component.translatable(settingTooltipKey));
				}

				rowDescriptionField.setHoverPane(ttBuilder.build());
			}

		}

	}

}
