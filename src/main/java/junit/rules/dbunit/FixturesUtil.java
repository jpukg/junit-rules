/**
 * junit-rules: JUnit Rules Library
 *
 * Copyright (c) 2009-2011 by Alistair A. Israel.
 * This software is made available under the terms of the MIT License.
 *
 * Created Oct 22, 2009
 */
package junit.rules.dbunit;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alistair A. Israel
 */
public final class FixturesUtil {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private FixturesUtil() {
        // noop
    }

    /**
     * @param elements
     *        the Class(es) or Method(s) to inspect for the {@link Fixtures} annotation
     * @return the list of fixture names
     */
    public static List<String> getFixtureNames(final AnnotatedElement... elements) {
        final List<String> fixtureNames = new ArrayList<String>();
        for (final AnnotatedElement element : elements) {
            if (element.isAnnotationPresent(Fixtures.class)) {
                for (final String fixtureName : element.getAnnotation(Fixtures.class).value()) {
                    fixtureNames.add(fixtureName);
                }
            }
        }
        return fixtureNames;
    }

}
