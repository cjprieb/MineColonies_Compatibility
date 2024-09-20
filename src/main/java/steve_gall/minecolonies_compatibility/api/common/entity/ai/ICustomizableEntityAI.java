package steve_gall.minecolonies_compatibility.api.common.entity.ai;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.equipment.registry.EquipmentTypeEntry;

import steve_gall.minecolonies_compatibility.core.common.entity.AbstractEntityAIBasicExtension;

public interface ICustomizableEntityAI
{
	@NotNull
	EquipmentTypeEntry getHandToolType();

	@Nullable
	default CustomizedAI getSelectedAI()
	{
		return ((AbstractEntityAIBasicExtension) this).minecolonies_compatibility$getSelectedAI();
	}

	@Nullable
	default CustomizedAIContext getAIContext()
	{
		return ((AbstractEntityAIBasicExtension) this).minecolonies_compatibility$getAIContext();
	}

}
