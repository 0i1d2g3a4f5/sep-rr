package utility;

import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashFunctionTest {
    HashFunction hashFunction;
    @BeforeProperty
    void setup(){
        hashFunction = new HashFunction(3486767820354631532L);

    }

    @Example
    void key(){

    }


    @Property
    void hash(@ForAll("validStrings") String string) {
        /*
        for (char c:string.toCharArray()) {
            System.out.println(c);
            Assume.that(hashFunction.chars.contains(String.valueOf(c)));
        }

         */

        String hash1 = hashFunction.hash(string);
        String hash2 = hashFunction.hash(string);
        assertEquals(hash1,hash2);

    }
    @Provide
    Arbitrary<String> validStrings(){
        Arbitrary<String> stringArbitrary = Arbitraries.strings().withChars(hashFunction.chars);
        return stringArbitrary;
    }
}