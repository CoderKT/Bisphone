package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.Map;

public abstract class MapperConfigBase<CFG extends ConfigFeature, T extends MapperConfigBase<CFG, T>> extends MapperConfig<T> implements Serializable {
    private static final int DEFAULT_MAPPER_FEATURES;
    protected final ContextAttributes _attributes;
    protected final Map<ClassKey, Class<?>> _mixInAnnotations;
    protected final String _rootName;
    protected final SubtypeResolver _subtypeResolver;
    protected final Class<?> _view;

    static {
        DEFAULT_MAPPER_FEATURES = MapperConfig.collectFeatureDefaults(MapperFeature.class);
    }

    protected MapperConfigBase(BaseSettings baseSettings, SubtypeResolver subtypeResolver, Map<ClassKey, Class<?>> map) {
        super(baseSettings, DEFAULT_MAPPER_FEATURES);
        this._mixInAnnotations = map;
        this._subtypeResolver = subtypeResolver;
        this._rootName = null;
        this._view = null;
        this._attributes = ContextAttributes.getEmpty();
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, int i) {
        super(mapperConfigBase._base, i);
        this._mixInAnnotations = mapperConfigBase._mixInAnnotations;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
        this._attributes = mapperConfigBase._attributes;
    }

    public final SubtypeResolver getSubtypeResolver() {
        return this._subtypeResolver;
    }

    public final String getRootName() {
        return this._rootName;
    }

    public final Class<?> getActiveView() {
        return this._view;
    }

    public final ContextAttributes getAttributes() {
        return this._attributes;
    }

    public final Class<?> findMixInClassFor(Class<?> cls) {
        return this._mixInAnnotations == null ? null : (Class) this._mixInAnnotations.get(new ClassKey(cls));
    }
}
