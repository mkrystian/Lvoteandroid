package pl.edu.agh.student.mprezes.lvoteandroid.model.converter;

/**
 * @author Krystian Majewski
 * @since 30.05.2017
 */

public interface ConverterDTO<T, D> {

    T convert(D object);
}
