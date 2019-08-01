package com.github.eightCourse.testSuite

import org.junit.experimental.categories.Categories
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Categories.class)
@Categories.IncludeCategory(FirstCategory.class)
@Suite.SuiteClasses([TestClassA,TestClassB])
class FirstTestSuite {
}
