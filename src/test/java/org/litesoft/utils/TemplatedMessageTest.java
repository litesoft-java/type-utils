package org.litesoft.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TemplatedMessageTest {
    int applyTemplatingCalled = 0;

    class OurTemplatedMessage extends TemplatedMessage {
        public OurTemplatedMessage( String fmtString, String... indexedFmtData ) {
            super( fmtString, indexedFmtData );
        }

        @Override
        protected String applyTemplating() {
            applyTemplatingCalled++;
            return super.applyTemplating();
        }
    }

    @Test
    void test_ToString() {
        String expectedMsg = "error on 'besty' of: Fred";
        TemplatedMessage tm = new OurTemplatedMessage( expectedMsg );
        assertEquals( 0, tm.getIndexedFmtData().length );
        assertEquals( expectedMsg, tm.getFmtString() );
        assertEquals( 0, applyTemplatingCalled );
        assertEquals( expectedMsg, tm.toString() );
        assertEquals( 1, applyTemplatingCalled );
        assertEquals( expectedMsg, tm.toString() );
        assertEquals( 1, applyTemplatingCalled );

        expectedMsg = "error.| on '.?1?.' of: .?0?.";
        assertEquals( expectedMsg, tm.replaceFmtString( "error.| on '.|1|.' of: .|0|." ).toString() );
        assertEquals( 2, applyTemplatingCalled );
        assertEquals( expectedMsg, tm.toString() );
        assertEquals( 2, applyTemplatingCalled );

        expectedMsg = "error.| on 'Freddy' of: besty";
        assertEquals( expectedMsg, tm.replaceIndexedFmtData( "besty", "Freddy" ).toString() );
        assertEquals( 3, applyTemplatingCalled );
        assertEquals( expectedMsg, tm.toString() );
        assertEquals( 3, applyTemplatingCalled );
    }

    @Test
    void constructor() {
        expectedError( "" );
        expectedError( null );
        assertEquals( "Fred.?dy?.", new TemplatedMessageException( "Fred.|dy|." ).getMessage() );
        TemplatedMessageException tme = new TemplatedMessageException( "error on '.|1|.'" );
        assertEquals( "error on '.?1?.'", tme.getMessage() );
        tme.getTemplatedMessage().replaceIndexedFmtData( "Freddy" );
        assertEquals( "error on '.?1?.'", tme.getMessage() );
        tme.getTemplatedMessage().replaceFmtString( "error on '.|0|.'" );
        assertEquals( "error on 'Freddy'", tme.getMessage() );
    }

    void expectedError( String fmtString ) {
        try {
            TemplatedMessage tm = new TemplatedMessage( fmtString );
            fail( "Expected exception, but got: " + tm );
        }
        catch ( IllegalArgumentException expected ) {
            String msg = expected.getMessage();
            if ( !msg.contains( "fmtString" ) ) {
                throw expected;
            }
        }
    }
}