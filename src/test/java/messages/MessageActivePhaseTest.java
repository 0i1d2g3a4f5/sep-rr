package messages;

import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

import static org.junit.jupiter.api.Assertions.*;

class MessageActivePhaseTest {

    @Example
    void exampleConstructorTest(){
        MessageActivePhase messageActivePhase = new MessageActivePhase(1);
        int phaseAfter = messageActivePhase.phase;
        System.out.println("Phase after: "+phaseAfter);
        assertEquals(1,phaseAfter);
        System.out.println(messageActivePhase);
    }
    @Property
    void constructorTest(@ForAll @IntRange(min = 0, max = 3) int phase) {
        MessageActivePhase messageActivePhase = new MessageActivePhase(phase);
        int phaseAfter = messageActivePhase.phase;
        System.out.println("Phase after: "+phaseAfter);
        assertEquals(phase,phaseAfter);
        System.out.println(messageActivePhase);
    }
}