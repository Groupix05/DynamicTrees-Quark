package maxhyper.dtquark;

import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictrees.worldgen.featurecancellation.MushroomFeatureCanceller;
import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
import maxhyper.dtquark.mushroom.GlowShroomCapProperties;
import maxhyper.dtquark.mushroom.GlowShroomSpecies;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class PlusRegistries {

    public static final FeatureCanceller MUSHROOM_CANCELLER = new MushroomFeatureCanceller<>(DynamicTreesQuark.location("mushroom"), null){
        @Override
        public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
            final ResourceLocation featureName = ForgeRegistries.FEATURES.getKey(configuredFeature.feature());
            if (featureName == null) {
                return false;
            }
            return featureCancellations.shouldCancelNamespace(featureName.getNamespace()) && featureName.getPath().equals("glow_shrooms");
        }
    };

    @SubscribeEvent
    public static void onFeatureCancellerRegistry(final RegistryEvent<FeatureCanceller> event) {
        event.getRegistry().registerAll(MUSHROOM_CANCELLER);
    }

    @SubscribeEvent
    public static void registerCapPropertiesType(final TypeRegistryEvent<CapProperties> event) {
        event.registerType(DynamicTreesQuark.location("glow_shroom"), GlowShroomCapProperties.TYPE);
    }

    @SubscribeEvent
    public static void registerSpeciesType(final TypeRegistryEvent<Species> event) {
        event.registerType(DynamicTreesQuark.location("glow_shroom"), GlowShroomSpecies.TYPE);
    }

}
