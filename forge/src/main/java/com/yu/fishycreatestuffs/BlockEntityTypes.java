package com.yu.fishycreatestuffs;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.yu.fishycreatestuffs.content.throughshaftgearbox.ThroughShaftGearboxEntity;
import com.yu.fishycreatestuffs.content.throughshaftgearbox.ThroughShaftGearboxInstance;
import com.yu.fishycreatestuffs.content.throughshaftgearbox.ThroughShaftGearboxRenderer;


public class BlockEntityTypes {
    public static final CreateRegistrate REGISTRATE = FishyCreateStuffsForge.getRegistrate();

    public static final BlockEntityEntry<ThroughShaftGearboxEntity> THROUGH_SHAFT_GEARBOX = REGISTRATE
            .blockEntity("through_shaft_gearbox", ThroughShaftGearboxEntity::new)
            .instance(() -> ThroughShaftGearboxInstance::new, false)
            .validBlocks(Blocks.THROUGH_SHAFT_GEARBOX)
            .renderer(() -> ThroughShaftGearboxRenderer::new)
            .register();


	public static void register() {}

}
