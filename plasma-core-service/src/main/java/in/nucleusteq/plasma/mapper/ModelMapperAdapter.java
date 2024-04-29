package in.nucleusteq.plasma.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
/**
 * ModelMapperAdapter.
 */
@Component
public class ModelMapperAdapter implements Mapper {
/**
 * ModelMapper.
 */
    private final ModelMapper modelMapper;
    /**
     * ModelMapperAdapter.
     */
    public ModelMapperAdapter() {
        this.modelMapper = new ModelMapper();
    }
    /**
     * map.
     */
    @Override
    public <T, U> U map(T source, Class<U> destinationType) {
        return modelMapper.map(source, destinationType);
    }
}
