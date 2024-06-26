package maxhyper.dtquark.loot;

import com.mojang.serialization.Codec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static maxhyper.dtquark.DynamicTreesQuark.MOD_ID;

public class LootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MOD_ID);

            public static final RegistryObject<Codec<? extends IGlobalLootModifier>> REPLACE_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("replace_item", ReplaceItemModifier.CODEC);

    public static void register(IEventBus bus) {
        LOOT_MODIFIER_SERIALIZERS.register(bus);
    }
}