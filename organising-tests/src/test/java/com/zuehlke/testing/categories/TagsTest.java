package com.zuehlke.testing.categories;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TagsTest {

	@Test
	void normal() {
	}

	@Tag("fast")
	@Test
	void fastTag() {
	}

	@Fast
	@Test
	void fastAnnotation() {
	}

	@Slow
	@Test
	void slowAnnotation() {
	}

	@SuperSlow
	@Test
	void superSlowAnnotation() {
	}
}
