package com.bmod;

import com.bmod.events.BlockBreakEvent;
import com.bmod.events.EntityDeathEvent;
import com.bmod.events.client.RenderOverlayEvent;
import com.bmod.events.UseBlockEvent;
import com.bmod.events.CommandRegisterEvent;
import com.bmod.registry.enchantment.ModEnchantments;
import com.bmod.registry.recipe.ModRecipeTypes;
import com.bmod.registry.entity.client.*;
import com.bmod.registry.entity.custom.BehemothEntity;
import com.bmod.registry.entity.custom.RotFlyEntity;
import com.bmod.registry.entity.custom.SnowFlinxEntity;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.entity.ModEntities;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.ModSounds;
import com.bmod.registry.menu.ModMenus;
import com.bmod.registry.world.biome.ModBiomes;
import com.bmod.registry.world.feature.ModFeatures;
import com.bmod.registry.world.feature.tree_grower.DreadwoodTreeGrower;
import com.bmod.registry.world.feature.tree_grower.EbonTreeGrower;
import com.bmod.util.TickHandlerUtils;
import com.bmod.util.WoodUtils;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public final class BlubbysMod {
    public static final String MOD_ID = "blubbysmod";

    public static void init() {
        TickHandlerUtils.initialize();
        UseBlockEvent.initialize();
        BlockBreakEvent.initialize();
        EntityDeathEvent.initialize();
        CommandRegisterEvent.initialize();

        ModMenus.MENUS.register();

        WoodUtils.registerWood(WoodUtils.DREADWOOD, new DreadwoodTreeGrower());
        WoodUtils.registerWood(WoodUtils.EBON, new EbonTreeGrower());

        ModEntities.ENTITY_TYPES.register();
        ModBlocks.BLOCKS.register();
        ModItems.ITEMS.register();
        ModEnchantments.ENCHANTMENTS.register();
        ModSounds.SOUNDS.register();
        ModRecipeTypes.RECIPE_SERIALIZERS.register();
        ModRecipeTypes.RECIPE_TYPES.register();
        ModBiomes.BIOMES.register();
        ModFeatures.init();

        EntityAttributeRegistry.register(ModEntities.ROT_FLY, RotFlyEntity::createAttributes);
        EntityAttributeRegistry.register(ModEntities.BEHEMOTH, BehemothEntity::createAttributes);
        EntityAttributeRegistry.register(ModEntities.SNOW_FLINX, SnowFlinxEntity::createAttributes);

        EnvExecutor.runInEnv(Env.CLIENT, () -> Client::initializeClient);
    }

    @Environment(EnvType.CLIENT)
    public static class Client {

        @Environment(EnvType.CLIENT)
        public static void initializeClient() {
            RenderOverlayEvent.initialize();

            EntityModelLayerRegistry.register(ModModelLayers.ROT_FLY_LAYER, RotFlyModel::createBodyLayer);
            EntityModelLayerRegistry.register(ModModelLayers.SNOW_FLINX_LAYER, SnowFlinxModel::createBodyLayer);
            EntityModelLayerRegistry.register(ModModelLayers.BEHEMOTH_LAYER, BehemothModel::createBodyLayer);

            EntityRendererRegistry.register(ModEntities.ROT_FLY, RotFlyRenderer::new);
            EntityRendererRegistry.register(ModEntities.SNOW_FLINX, SnowFlinxRenderer::new);
            EntityRendererRegistry.register(ModEntities.BEHEMOTH, BehemothRenderer::new);
        }
    }
}
