/**
 * junit-rules: JUnit Rules Library
 *
 * Copyright (c) 2009-2011 by Alistair A. Israel.
 * This software is made available under the terms of the MIT License.
 *
 * Created Feb 15, 2011
 */
package junit.rules.cdi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * The Class WeldTest.
 *
 * @author Alistair A. Israel
 */
public final class WeldTest {

    /**
     * Illustrates how one would use the Weld {@code @Rule}.
     *
     * @author Alistair A. Israel
     */
    public static final class UsesWeldTest {

        /** The weld. */
        @Rule
        // CHECKSTYLE:OFF
        public Weld weld = new Weld();
        // CHECKSTYLE:ON

        @Inject
        private ClassUnderTest classUnderTest;

        /**
         * Test injection.
         */
        @Test
        public void testInjection() {
            assertNotNull(classUnderTest);
            assertNotNull(classUnderTest.dependency);
            assertTrue(classUnderTest.dependency instanceof MockDependency);
        }
    }

    /**
     * Test Weld {@code @Rule} using {@link UsesWeldTest}.
     */
    @Test
    public void testWeld() {
        final Result result = JUnitCore.runClasses(UsesWeldTest.class);
        final int failureCount = result.getFailureCount();
        assertEquals(0, failureCount);
        if (failureCount != 0) {
            System.out.println("Encountered " + failureCount + " failures");
            for (final Failure failure : result.getFailures()) {
                System.out.println(failure);
                final Throwable e = failure.getException();
                if (e != null) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * The class ClassUnderTest.
     */
    public static class ClassUnderTest {
        @Inject
        private Dependency dependency;

    }

    /**
     * The interface Dependency.
     */
    public interface Dependency {

    }

    /**
     * The default implementation of Dependency.
     */
    public static class DependencyImpl implements Dependency {

    }

    /**
     * The mock implementation of Dependency, which is what we're interested in.
     */
    @Mock
    public static class MockDependency implements Dependency {

    }
}
