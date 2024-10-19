package com.yu.fishycreatestuffs;

import com.yu.fishycreatestuffs.content.throughshaftgearbox.ThroughShaftGearboxBlock;
import com.simibubi.create.*;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;

import com.yu.fishycreatestuffs.content.trainstair.TrainStairBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

import static com.simibubi.create.foundation.data.AssetLookup.partialBaseModel;
import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;

@SuppressWarnings("removal")
public class Blocks {
    public static final BlockEntry<ThroughShaftGearboxBlock> THROUGH_SHAFT_GEARBOX = FishyCreateStuffs.REGISTRATE.block("through_shaft_gearbox", ThroughShaftGearboxBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(BlockStressDefaults.setNoImpact())
            .transform(axeOrPickaxe())
            .lang("Through Shaft Gearbox")
            .blockstate((c, p) -> axisBlock(c, p, $ -> partialBaseModel(c, p), false))
            .item()
            .transform(customItemModel())
			//.simpleItem()
            .register();

	public static final BlockEntry<TrainStairBlock> TRAIN_STAIR_BLOCK = FishyCreateStuffs.REGISTRATE.block("train_stair", TrainStairBlock::new)
			.initialProperties(SharedProperties::softMetal)
			.addLayer(() -> RenderType::cutoutMipped)
			.properties(p -> p.noOcclusion().mapColor(MapColor.STONE))
			.lang("Train Stair")
			.blockstate((c, p) -> axisBlock(c, p, $ -> partialBaseModel(c, p), false))
			.item()
			.transform(customItemModel())
			.register();


	static {

		ItemGroupEvents.modifyEntriesEvent(AllCreativeModeTabs.BASE_CREATIVE_TAB.key()).register(content -> {
			content.accept(THROUGH_SHAFT_GEARBOX);
			content.accept(TRAIN_STAIR_BLOCK);
		});
	}
    public static void register() {
    }

}
