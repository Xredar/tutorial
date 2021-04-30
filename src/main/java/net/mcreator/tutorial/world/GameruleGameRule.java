package net.mcreator.tutorial.world;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.GameRules;

import net.mcreator.tutorial.TutorialModElements;

import java.lang.reflect.Method;

@TutorialModElements.ModElement.Tag
public class GameruleGameRule extends TutorialModElements.ModElement {
	public static final GameRules.RuleKey<GameRules.BooleanValue> gamerule = GameRules.register("gamerule", GameRules.Category.SPAWNING,
			create(true));
	public GameruleGameRule(TutorialModElements instance) {
		super(instance, 14);
	}

	public static GameRules.RuleType<GameRules.BooleanValue> create(boolean defaultValue) {
		try {
			Method createGameruleMethod = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "func_223568_b", boolean.class);
			createGameruleMethod.setAccessible(true);
			return (GameRules.RuleType<GameRules.BooleanValue>) createGameruleMethod.invoke(null, defaultValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
