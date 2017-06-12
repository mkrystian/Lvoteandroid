package pl.edu.agh.student.mprezes.lvoteandroid.model.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Krystian Majewski
 * @since 30.05.2017
 */

public abstract class ConverterDTO<T, D> {

    public abstract T convert(D dto);

    public List<T> convert(List<D> dtoList) {
        List<T> result = new ArrayList<>();

        if (dtoList == null) {
            return result;
        }

        for (D dto : dtoList) {
            result.add(convert(dto));
        }

        return result;
    }

    public Set<T> convert(Set<D> dtoSet) {
        Set<T> result = new HashSet<>();

        if (dtoSet == null) {
            return result;
        }

        for (D dto : dtoSet) {
            result.add(convert(dto));
        }

        return result;
    }
}
