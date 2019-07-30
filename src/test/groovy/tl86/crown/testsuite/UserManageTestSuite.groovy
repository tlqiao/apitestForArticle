package tl86.crown.testsuite

import org.junit.experimental.categories.Categories
import org.junit.runner.RunWith
import org.junit.runners.Suite
import tl86.crown.scenario.userManage.testCase.AddUserTest
import tl86.crown.scenario.userManage.testCase.EditUserTest
import tl86.crown.scenario.userManage.testCase.ResetPasswordTest
import tl86.crown.scenario.userManage.testCase.SearchUserTest
import tl86.crown.scenario.userManage.testCase.UpdateUserStatusTest

@RunWith(Categories.class)
@Categories.IncludeCategory(CrownTest.class)
@Suite.SuiteClasses([AddUserTest, EditUserTest, ResetPasswordTest, SearchUserTest, UpdateUserStatusTest])
class UserManageTestSuite {
}
