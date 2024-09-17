package com.yu.fishycreatestuffs;

import com.tterrag.registrate.util.entry.BlockEntityEntry;

import com.yu.fishycreatestuffs.content.throughshaftgearbox.ThroughShaftGearboxEntity;

import com.yu.fishycreatestuffs.content.throughshaftgearbox.ThroughShaftGearboxInstance;
import com.yu.fishycreatestuffs.content.throughshaftgearbox.ThroughShaftGearboxRenderer;
//import com.yu.fishycreatestuffs.content.linkedtransmitter.LinkedAnalogLeverBlockEntity;
//import com.yu.fishycreatestuffs.content.linkedtransmitter.LinkedAnalogLeverRenderer;
//import com.yu.fishycreatestuffs.content.linkedtransmitter.LinkedTransmitterBlockEntity;


public class BlockEntityTypes {

    public static final BlockEntityEntry<ThroughShaftGearboxEntity> THROUGH_SHAFT_GEARBOX = FishyCreateStuffs.REGISTRATE
            .blockEntity("through_shaft_gearbox", ThroughShaftGearboxEntity::new)
            .instance(() -> ThroughShaftGearboxInstance::new, false)
            .validBlocks(Blocks.THROUGH_SHAFT_GEARBOX)
            .renderer(() -> ThroughShaftGearboxRenderer::new)
            .register();

	/*
    public static final BlockEntityEntry<LinkedTransmitterBlockEntity> LINKED_TRANSMITTER = REGISTRATE
            .blockEntity("linked_transmitter", LinkedTransmitterBlockEntity::new)
            .transform(b -> {Blocks.LINKED_BUTTONS.values().forEach(b::validBlock);return b;})
            .validBlocks(Blocks.LINKED_LEVER)
            .renderer(() -> SmartBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<LinkedAnalogLeverBlockEntity> LINKED_ANALOG_LEVER = REGISTRATE
            .blockEntity("linked_analog_lever", LinkedAnalogLeverBlockEntity::new)
            .instance(() -> AnalogLeverInstance::new)
            .validBlocks(Blocks.LINKED_ANALOG_LEVER)
            .renderer(() -> LinkedAnalogLeverRenderer::new)
            .register();
	*/
	public static void register() {}

}
