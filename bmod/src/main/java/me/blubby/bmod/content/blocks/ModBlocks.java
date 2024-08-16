package me.blubby.bmod.content.blocks;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.content.armor.BlubbyArmorItem;
import me.blubby.bmod.content.armor.ModArmorMaterial;
import me.blubby.bmod.content.blocks.custom.BurnablePillarBlock;
import me.blubby.bmod.content.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, Blubby_sModOfDoom.MOD_ID);

    public static final RegistryObject<Block> VOID_LOG = registerBlock("void_log", () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), CreativeModeTab.TAB_MISC);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, Supplier<T> block, CreativeModeTab tab)
    {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    //private static Properties pom(Material material) {
    //    return () -> BlockBehaviour.Properties.of(material);
    //}

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
