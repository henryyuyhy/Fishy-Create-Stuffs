package com.yu.fishycreatestuffs;

import com.simibubi.create.foundation.data.CreateRegistrate;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FishyCreateStuffs implements ModInitializer {

	public static final String ID = "fishycreatestuffs";
	public static final String NAME = "Fishy Create Stuffs";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ID);

	@Override
	public void onInitialize() {
		Items.register();
		BlockEntityTypes.register();
		REGISTRATE.register();


	}

	public static ResourceLocation asResource(String path) {
		return new ResourceLocation(ID, path);
	}
}
