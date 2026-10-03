package com.yu.fishycreatestuffs;

import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.foundation.data.CreateRegistrate;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NeoForge 1.21.1 port of the Fabric/common 1.20.1 version of Fishy Create Stuffs.
 */
@Mod(FishyCreateStuffs.ID)
public class FishyCreateStuffs {

	public static final String ID = "fishycreatestuffs";
	public static final String NAME = "Fishy Create Stuffs";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ID);

	public FishyCreateStuffs(IEventBus modEventBus) {
		// Counterpart of the Fabric version's
		// ItemGroupEvents.modifyEntriesEvent(AllCreativeModeTabs.BASE_CREATIVE_TAB.key()):
		// both blocks belong in Create's own "Base" creative tab.
		//
		// This must run before the Blocks/Items/BlockEntityTypes classes are loaded,
		// because their Registrate builders capture the creative tab as they are built.
		// It also *replaces* Registrate's default of CreativeModeTabs.SEARCH: leaving that
		// in place makes Registrate insert the items into the vanilla search tab on top of
		// vanilla's own search-tab generator, and NeoForge rejects the duplicate entry with
		// "Itemstack ... already exists in the tab's list" when the tab is rebuilt.
		REGISTRATE.defaultCreativeTab(AllCreativeModeTabs.BASE_CREATIVE_TAB.getKey());
		REGISTRATE.registerEventListeners(modEventBus);

		Items.register();
		BlockEntityTypes.register();
		Blocks.register();
	}

	public static CreateRegistrate getRegistrate() {
		return REGISTRATE;
	}

	public static ResourceLocation asResource(String path) {
		return ResourceLocation.fromNamespaceAndPath(ID, path);
	}
}
