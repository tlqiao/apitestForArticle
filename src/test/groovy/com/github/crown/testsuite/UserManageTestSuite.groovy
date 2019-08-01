package com.github.crown.testsuite


import org.junit.experimental.categories.Categories
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Categories.class)
@Categories.IncludeCategory(CrownTest.class)
@Suite.SuiteClasses([com.github.crown.scenario.userManage.testCase.AddUserTest, com.github.crown.scenario.userManage.testCase.EditUserTest, com.github.crown.scenario.userManage.testCase.ResetPasswordTest, com.github.crown.scenario.userManage.testCase.SearchUserTest, com.github.crown.scenario.userManage.testCase.UpdateUserStatusTest])
class UserManageTestSuite {
}
