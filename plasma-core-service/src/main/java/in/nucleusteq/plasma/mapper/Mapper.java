package in.nucleusteq.plasma.mapper;
/**
 * Mapper.
 */
public interface Mapper {
/**
 * map.
 * @param <T>
 * @param <U>
 * @param source
 * @param destinationType
 * @return map
 */
    <T, U> U map(T source, Class<U> destinationType);
}
