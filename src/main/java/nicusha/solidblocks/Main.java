package nicusha.solidblocks;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.function.Supplier;

@Mod(Main.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Main.MODID)
public class Main
{
    public static final String MODID = "solidblocks";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> SCT = TAB.register("tab", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.solidblocks")).icon(() -> new ItemStack(Main.red.get())).build());

    public static final RegistryObject<Block> white = registerBlock("white", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> orange = registerBlock("orange", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> magenta = registerBlock("magenta", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_MAGENTA).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> light_blue = registerBlock("light_blue", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> yellow = registerBlock("yellow", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> light_green = registerBlock("light_green", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> pink = registerBlock("pink", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> gray = registerBlock("gray", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> light_gray = registerBlock("light_gray", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> cyan = registerBlock("cyan", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> purple = registerBlock("purple", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> blue = registerBlock("blue", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> brown = registerBlock("brown", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> green = registerBlock("green", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> red = registerBlock("red", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> black = registerBlock("black", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(1.5F, 6.0F)));

    private static <T extends Block> RegistryObject<T> registerBlock(String registryName, Supplier<T> block) {
        RegistryObject<T> registeredBlock = BLOCKS.register(registryName, block);
        ITEMS.register(registryName, () -> new BlockItem(registeredBlock.get(), new Item.Properties()));
        return registeredBlock;
    }
    public Main()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
        BLOCKS.register(bus);
        ITEMS.register(bus);
        TAB.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    @SubscribeEvent
    public static void creativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == SCT.get()) {
            for (var regObj : ITEMS.getEntries()) {
                event.accept(regObj.get());
            }
        }
    }
}
