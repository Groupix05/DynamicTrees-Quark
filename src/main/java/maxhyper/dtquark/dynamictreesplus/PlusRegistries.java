package maxhyper.dtquark.dynamictreesplus;

import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictrees.worldgen.featurecancellation.MushroomFeatureCanceller;
import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
import maxhyper.dtquark.DynamicTreesQuark;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class PlusRegistries {

    public static final FeatureCanceller MUSHROOM_CANCELLER = new MushroomFeatureCanceller<>(DynamicTreesQuark.location("mushroom"), null){
        @Override
        public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, @NotNull Set<String> namespaces) {
            final ResourceLocation featureName = configuredFeature.feature().getRegistryName();
            if (featureName == null) return false;
            return namespaces.contains(featureName.getNamespace()) && featureName.getPath().equals("glow_shrooms");
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
