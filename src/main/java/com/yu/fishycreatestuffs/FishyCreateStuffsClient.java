package com.yu.fishycreatestuffs;

import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Client-only entry point. Registers the Ponder plugin, replacing the Fabric
 * {@code ClientModInitializer} of the 1.20.1 project.
 */
@Mod(value = FishyCreateStuffs.ID, dist = Dist.CLIENT)
public class FishyCreateStuffsClient {

	public FishyCreateStuffsClient(IEventBus modEventBus) {
		modEventBus.addListener(FishyCreateStuffsClient::init);
	}

	public static void init(final FMLClientSetupEvent event) {
		PonderIndex.addPlugin(new FishyCreateStuffsPonderPlugin());
	}
}
