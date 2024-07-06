package steve_gall.minecolonies_compatibility.core.common.entity.ai.fluid_manager;

import com.minecolonies.api.entity.ai.statemachine.states.IAIState;

public enum FluidManagerAIState implements IAIState
{
	SEARCH(true),
	PICKUP(false),
	//
	;

	private boolean isOkayToEat;

	FluidManagerAIState(boolean isOkayToEat)
	{
		this.isOkayToEat = isOkayToEat;
	}

	@Override
	public boolean isOkayToEat()
	{
		return this.isOkayToEat;
	}

}
