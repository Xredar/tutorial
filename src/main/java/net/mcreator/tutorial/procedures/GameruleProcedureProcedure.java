package net.mcreator.tutorial.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.tutorial.world.GameruleGameRule;
import net.mcreator.tutorial.TutorialModElements;
import net.mcreator.tutorial.TutorialMod;

import java.util.Map;

@TutorialModElements.ModElement.Tag
public class GameruleProcedureProcedure extends TutorialModElements.ModElement {
	public GameruleProcedureProcedure(TutorialModElements instance) {
		super(instance, 15);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TutorialMod.LOGGER.warn("Failed to load dependency world for procedure GameruleProcedure!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getWorldInfo().getGameRulesInstance().getBoolean(GameruleGameRule.gamerule)) == (false))) {
			return (false);
		}
		return (true);
	}
}
