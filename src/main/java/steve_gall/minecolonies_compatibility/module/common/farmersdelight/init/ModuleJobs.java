package steve_gall.minecolonies_compatibility.module.common.farmersdelight.init;

import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.core.colony.jobs.JobCookAssistant;
import com.minecolonies.core.colony.jobs.views.CrafterJobView;

import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_tweaks.api.registries.JobRegister;

public class ModuleJobs
{
	public static final JobRegister REGISTER = new JobRegister(MineColoniesCompatibility.MOD_ID);

	@Deprecated
	public static final RegistryObject<JobEntry> FARMERS_COOK = REGISTER.register("farmers_cook", builder ->
	{
		builder.setJobProducer(JobCookAssistant::new);
		builder.setJobViewProducer(() -> CrafterJobView::new);
	});

	private ModuleJobs()
	{

	}

}
