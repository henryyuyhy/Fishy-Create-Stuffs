package com.yu.fishycreatestuffs;

import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.yu.fishycreatestuffs.content.throughshaftgearbox.ThroughShaftGearboxBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.MapColor;
import static com.simibubi.create.foundation.data.AssetLookup.partialBaseModel;
import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

@SuppressWarnings("removal")
public class Blocks {

	public static final CreateRegistrate REGISTRATE = FishyCreateStuffsForge.getRegistrate();

    public static final BlockEntry<ThroughShaftGearboxBlock> THROUGH_SHAFT_GEARBOX = REGISTRATE.block("through_shaft_gearbox", ThroughShaftGearboxBlock::new)
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



	static {
	}
    public static void register() {
    }

}
