package github.com.crown.testsuite

import org.junit.experimental.categories.Categories
import org.junit.runner.RunWith
import org.junit.runners.Suite
import github.com.crown.scenario.userManage.testCase.AddUserTest
import github.com.crown.scenario.userManage.testCase.EditUserTest
import github.com.crown.scenario.userManage.testCase.ResetPasswordTest
import github.com.crown.scenario.userManage.testCase.SearchUserTest
import github.com.crown.scenario.userManage.testCase.UpdateUserStatusTest

@RunWith(Categories.class)
@Categories.IncludeCategory(CrownTest.class)
@Suite.SuiteClasses([AddUserTest, EditUserTest, ResetPasswordTest, SearchUserTest, UpdateUserStatusTest])
class UserManageTestSuite {
}
