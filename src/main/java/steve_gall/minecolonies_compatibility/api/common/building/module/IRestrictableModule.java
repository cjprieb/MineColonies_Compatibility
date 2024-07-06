package steve_gall.minecolonies_compatibility.api.common.building.module;

import org.jetbrains.annotations.NotNull;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;

import net.minecraft.core.BlockPos;

public interface IRestrictableModule extends IBuildingModule
{
	void setRestrictEnabled(boolean enabled);

	boolean isRestrictEnabled();

	void setRestrictArea(@NotNull BlockPos pos1, @NotNull BlockPos pos2);

	@NotNull
	BlockPos getRestrictAreaPos1();

	@NotNull
	BlockPos getRestrictAreaPos2();

	int getSearchRange();
}
