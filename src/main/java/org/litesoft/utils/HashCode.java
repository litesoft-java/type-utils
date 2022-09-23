package org.litesoft.utils;

@SuppressWarnings("unused")
public class HashCode {
    public static class Builder {
        private int hashCode;

        private Builder( int hashCode ) {
            this.hashCode = hashCode;
        }

        public int toHashCode() {
            return hashCode;
        }

        public Builder and( Object value ) {
            return addHashCode( calc( value ) );
        }

        public Builder and( boolean value ) {
            return addHashCode( calc( value ) );
        }

        public Builder and( int value ) {
            return addHashCode( calc( value ) );
        }

        public Builder and( long value ) {
            return addHashCode( calc( value ) );
        }

        private Builder addHashCode( int hashCode ) {
            this.hashCode = em( this.hashCode, hashCode );
            return this;
        }
    }

    public static Builder from( Object value ) {
        return new Builder( calc( value ) );
    }

    public static Builder from( boolean value ) {
        return new Builder( calc( value ) );
    }

    public static Builder from( int value ) {
        return new Builder( calc( value ) );
    }

    public static Builder from( long value ) {
        return new Builder( calc( value ) );
    }

    public static int calc( Object value ) {
        return (value == null) ? 0 : value.hashCode();
    }

    public static int calc( boolean value ) {
        return Boolean.valueOf( value ).hashCode();
    }

    public static int calc( int value ) {
        return value; // 'lifted' from Integer
    }

    public static int calc( long value ) {
        return (int) (value ^ (value >>> 32)); // 'lifted' from Long
    }

    public static int em( int hashCode1, int hashCode2 ) {
        return (hashCode1 * 31) + hashCode2;
    }

    public static int em( int hashCode1, int hashCode2, int hashCode3 ) {
        return em( em( hashCode1, hashCode2 ), hashCode3 );
    }

    public static int em( int hashCode1, int hashCode2, int hashCode3, int hashCode4 ) {
        return em( em( hashCode1, hashCode2, hashCode3 ), hashCode4 );
    }

    public static int em( int hashCode1, int hashCode2, int hashCode3, int hashCode4, int hashCode5 ) {
        return em( em( hashCode1, hashCode2, hashCode3, hashCode4 ), hashCode5 );
    }
}