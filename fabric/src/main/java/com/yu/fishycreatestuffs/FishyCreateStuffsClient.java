package com.yu.fishycreatestuffs;

import net.fabricmc.api.ClientModInitializer;

public class FishyCreateStuffsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Ponders.register();
	}

}
