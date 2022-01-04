package TestSuite;

import TestCase.Invitation.InvitationTestCase;
import TestCase.user.UserTestCase;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * @author Riza Nansuri
 * @version $Id: InvitationTestSuite.java, v 0.1 2022‐01‐04 10.18 Riza Nansuri Exp $$
 */

@Suite
@IncludeTags("Invitation")
@SelectClasses( {InvitationTestCase.class, UserTestCase.class} )
public class InvitationTestSuite {

}
