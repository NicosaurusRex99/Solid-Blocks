package nicusha.solidblocks;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.function.Supplier;

@Mod(Main.MODID)
public class Main
{
    public static final String MODID = "solidblocks";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Block> white = registerBlock("white", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> orange = registerBlock("orange", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> magenta = registerBlock("magenta", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_MAGENTA).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> light_blue = registerBlock("light_blue", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> yellow = registerBlock("yellow", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_YELLOW).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> light_green = registerBlock("light_green", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> pink = registerBlock("pink", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> gray = registerBlock("gray", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> light_gray = registerBlock("light_gray", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GRAY).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> cyan = registerBlock("cyan", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_CYAN).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> purple = registerBlock("purple", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> blue = registerBlock("blue", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> brown = registerBlock("brown", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> green = registerBlock("green", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> red = registerBlock("red", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> black = registerBlock("black", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(1.5F, 6.0F)));

    private static <T extends Block> RegistryObject<T> registerBlock(String registryName, Supplier<T> block) {
        RegistryObject<T> registeredBlock = BLOCKS.register(registryName, block);
        ITEMS.register(registryName, () -> new BlockItem(registeredBlock.get(), new Item.Properties()));
        return registeredBlock;
    }
    public Main()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(Main::registerTab);
        bus.addListener(this::commonSetup);
        BLOCKS.register(bus);
        ITEMS.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    public static final ResourceLocation TAB = new ResourceLocation(Main.MODID, "solidblocks");

    private static ItemStack makeIcon() {
        return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Main.MODID, "red")));
    }


    public static void registerTab(CreativeModeTabEvent.Register event){
        event.registerCreativeModeTab(TAB, builder -> builder.title(Component.translatable("itemGroup.solidblocks")).icon(Main::makeIcon).displayItems((flags, output) -> {
            for(RegistryObject<Item> item : Main.ITEMS.getEntries()){
                output.accept(item.get());
            }
        }));

    }

}
