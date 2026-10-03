package com.yu.fishycreatestuffs;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;

import com.yu.fishycreatestuffs.content.throughshaftgearbox.ThroughShaftGearboxBlock;
import com.yu.fishycreatestuffs.content.trainstair.TrainStairBlock;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

/**
 * Block registration. Blockstates, models, loot tables and recipes are shipped as
 * hand-written resources under src/main/resources (same as the 1.20.1 project), so
 * no blockstate/item-model data generators are declared here.
 *
 * {@code addLayer} is deprecated in 1.21 but still honoured; it is also what gives
 * the BlockItems their render layer, so the 1.20.1 behaviour is preserved verbatim.
 *
 * The 1.20.1 sources called {@code BlockStressDefaults.setNoImpact()} here. Create 6.0
 * dropped that for addons: {@code CStress} now throws for non-Create blocks, and
 * {@code BlockStressValues.getImpact} already returns 0 for any block that is neither
 * registered nor covered by Create's own config provider. The gearbox therefore keeps
 * its "no stress impact" behaviour with no call at all.
 */
@SuppressWarnings("removal")
public class Blocks {

	private static final CreateRegistrate REGISTRATE = FishyCreateStuffs.getRegistrate();

	public static final BlockEntry<ThroughShaftGearboxBlock> THROUGH_SHAFT_GEARBOX = REGISTRATE
			.block("through_shaft_gearbox", ThroughShaftGearboxBlock::new)
			.initialProperties(SharedProperties::stone)
			.properties(p -> p.noOcclusion()
					.mapColor(MapColor.PODZOL))
			.addLayer(() -> RenderType::cutoutMipped)
			.transform(axeOrPickaxe())
			.item()
			.build()
			.register();

	public static final BlockEntry<TrainStairBlock> TRAIN_STAIR_BLOCK = REGISTRATE
			.block("train_stair", TrainStairBlock::new)
			.initialProperties(SharedProperties::softMetal)
			.addLayer(() -> RenderType::cutoutMipped)
			.properties(p -> p.noOcclusion()
					.mapColor(MapColor.STONE))
			.item()
			.build()
			.register();

	public static void register() {
	}
}
