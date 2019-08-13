package com.github.crown.testsuite

import com.github.crown.scenario.userManage.testCase.AddUserTest
import com.github.crown.scenario.userManage.testCase.EditUserTest
import com.github.crown.scenario.userManage.testCase.ResetPasswordTest
import com.github.crown.scenario.userManage.testCase.SearchUserTest
import com.github.crown.scenario.userManage.testCase.UpdateUserStatusTest
import org.junit.experimental.categories.Categories
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Categories.class)
@Categories.IncludeCategory(CrownTest.class)
@Suite.SuiteClasses([AddUserTest, EditUserTest, ResetPasswordTest, SearchUserTest, UpdateUserStatusTest])
class UserManageTestSuite {
}
