package de.gematik.ti.epa.vzd.gemClient.command.commandExecutions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import de.gematik.ti.epa.vzd.gemClient.invoker.GemApiClient;
import generated.CommandType;
import generated.DistinguishedNameType;
import org.junit.Test;

public class ReadDirEntryExecutionTest {

    private static GemApiClient gemApiClient = mock(GemApiClient.class);

    @Test
    public void checkValidationMissingArgumentTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        assertFalse(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveDnTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        DistinguishedNameType dn = new DistinguishedNameType();
        dn.setUid("cbca60fe-8ca7-4960-990d-ec526a200582");
        command.setDn(dn);
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveGivenNameTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.setGivenName("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveSnTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.setSn("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveCnTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.setCn("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveDisplayNameTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.setDisplayName("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveStreetAddressTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.setStreetAddress("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHavePostalCodeTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.setPostalCode("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveLocalityNameTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.setLocalityName("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveStateOrProvinceNameTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.setGivenName("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveTitleTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.setTitle("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveOrganizationTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.setOrganization("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveOtherNameTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.setOtherName("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveSpecializationTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.getSpecialization().add("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveDomainIDTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.getDomainID().add("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHavePersonalEntryTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.setPersonalEntry("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }

    @Test
    public void checkValidationHaveDataFromAuthorityTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(gemApiClient);
        CommandType command = new CommandType();
        command.setDataFromAuthority("TestString");
        assertTrue(readDirEntryExecution.checkValidation(command));
    }
}