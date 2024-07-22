package steve_gall.minecolonies_compatibility.core.common.util;

public class ReflectionUtils
{
	@SuppressWarnings("unchecked")
	public static <T> T getOuter(Object object)
	{
		try
		{
			var field = object.getClass().getDeclaredField("this$0");
			field.setAccessible(true);
			return (T) field.get(object);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}

	}

	private ReflectionUtils()
	{

	}

}
