package org.pac4j.core.http.callback;

import org.junit.Test;
import org.pac4j.core.context.MockWebContext;
import org.pac4j.core.http.url.DefaultUrlResolver;
import org.pac4j.core.util.TestsConstants;

import static org.junit.Assert.*;

/**
 * Tests {@link PathParameterCallbackUrlResolver}.
 *
 * @author Jerome Leleu
 * @since 3.0.0
 */
public final class PathParameterCallbackUrlResolverTests implements TestsConstants {

    private static final PathParameterCallbackUrlResolver resolver = new PathParameterCallbackUrlResolver();

    @Test
    public void testCompute() {
        final var url = resolver.compute(new DefaultUrlResolver(), CALLBACK_URL, MY_CLIENT_NAME, MockWebContext.create());
        assertEquals(CALLBACK_URL + "/" + MY_CLIENT_NAME, url);
    }

    @Test
    public void testMatchesNoClientName() {
        assertFalse(resolver.matches(MY_CLIENT_NAME, MockWebContext.create()));
    }

    @Test
    public void testMatchesSimplePath() {
        final var context = MockWebContext.create();
        context.setPath(MY_CLIENT_NAME);
        assertTrue(resolver.matches(MY_CLIENT_NAME, context));
    }

    @Test
    public void testMatchesComplexPath() {
        final var context = MockWebContext.create();
        context.setPath(VALUE + "/" + MY_CLIENT_NAME);
        assertTrue(resolver.matches(MY_CLIENT_NAME, context));
    }
}
