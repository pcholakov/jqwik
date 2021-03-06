package examples.docs;

import java.util.*;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

class ShrinkingExamples {

	@Property(reporting = Reporting.FALSIFIED)
	boolean stringShouldBeShrunkToAA(@ForAll @AlphaChars String aString) {
		return aString.length() > 5 || aString.length() < 2;
	}

	@Property
	boolean shrinkingCanTakeLong(@ForAll("first") String first, @ForAll("second") String second) {
		String aString = first + second;
		return aString.length() > 5 || aString.length() < 2;
	}

	@Provide
	Arbitrary<String> first() {
		return Arbitraries.strings() //
				.withCharRange('a', 'z') //
				.ofMinLength(1) //
				.ofMaxLength(10) //
				.filter(string -> string.endsWith("h"));
	}

	@Provide
	Arbitrary<String> second() {
		return Arbitraries.strings().withCharRange('0', '9').ofMinLength(0).ofMaxLength(10).filter(string -> string.length() >= 1);
	}

	@Property(shrinking = ShrinkingMode.OFF)
	void aPropertyWithLongShrinkingTimes(@ForAll List<Set<String>> list1, @ForAll List<Set<String>> list2) {
	}
}
