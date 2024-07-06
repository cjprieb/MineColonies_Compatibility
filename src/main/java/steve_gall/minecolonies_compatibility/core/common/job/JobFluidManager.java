package steve_gall.minecolonies_compatibility.core.common.job;

import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.core.colony.jobs.AbstractJobCrafter;
import com.minecolonies.core.entity.citizen.EntityCitizen;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import steve_gall.minecolonies_compatibility.core.common.entity.ai.fluid_manager.EntityAIWorkFluidManager;

public class JobFluidManager extends AbstractJobCrafter<EntityAIWorkFluidManager, JobFluidManager>
{
	public JobFluidManager(ICitizenData entity)
	{
		super(entity);
	}

	@Override
	public EntityAIWorkFluidManager generateAI()
	{
		return new EntityAIWorkFluidManager(this);
	}

	@Override
	public void playSound(BlockPos blockPos, EntityCitizen worker)
	{
		worker.queueSound(SoundEvents.LAVA_POP, blockPos, 1, 0);
	}

}
