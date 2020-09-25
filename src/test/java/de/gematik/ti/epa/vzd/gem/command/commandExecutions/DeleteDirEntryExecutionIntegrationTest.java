package de.gematik.ti.epa.vzd.gem.command.commandExecutions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import de.gematik.ti.epa.vzd.client.invoker.ApiException;
import de.gematik.ti.epa.vzd.gem.command.CommandsBuilder;
import de.gematik.ti.epa.vzd.gem.command.ExecutionCollection;
import de.gematik.ti.epa.vzd.gem.invoker.ConfigHandler;
import de.gematik.ti.epa.vzd.gem.invoker.ConnectionPool;
import generated.CommandType;
import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeleteDirEntryExecutionIntegrationTest {

    private static final String[] TEST_ARGS = new String[]{"-p",
        "src" + File.separator + "test" + File.separator + "resources" + File.separator
            + "config" + File.separator + "IntegrationConfig.txt", "-c",
        "src" + File.separator + "test" + File.separator + "resources" + File.separator
            + "config" + File.separator + "Credentials.txt", "-b", "src" + File.separator + "test" + File.separator + "resources" + File.separator
        + "config" + File.separator + "commands" + File.separator + "delDir.xml"};
    public static List<CommandType> commands;

    @Before
    public void initConfigHandler() {
        ConfigHandler.setConfigHandler(null);
        ConfigHandler.init(TEST_ARGS);
        ExecutionCollection.init(ConnectionPool.createConnectionPool(1));
        commands = new CommandsBuilder().buildCommands();
    }

    @After
    public void unsetConfigHandler() {
        ConfigHandler.setConfigHandler(null);
        ExecutionCollection.getInstance().setExecutionCollection(null);
    }

    @Test
    public void delCommandSuccessTest() throws Exception {
        DeleteDirEntryExecution deleteDirEntryExecution = new DeleteDirEntryExecution(ConnectionPool.createConnectionPool(1));
        assertTrue(deleteDirEntryExecution.createCallable(commands.get(0)).call());
    }

    @Test
    public void delCommandFailTest() throws Exception {
        DeleteDirEntryExecution deleteDirEntryExecution = new DeleteDirEntryExecution(ConnectionPool.createConnectionPool(1));
        assertFalse(deleteDirEntryExecution.createCallable(commands.get(1)).call());
    }

    @Test
    public void executeCommandEntryNotPresentTest() {
        ReadDirEntryExecution readDirEntryExecution = new ReadDirEntryExecution(ConnectionPool.createConnectionPool(1));
        ApiException exception = assertThrows(ApiException.class,
            () -> readDirEntryExecution.executeCommand(commands.get(1), readDirEntryExecution.connectionPool.getConnection()));
        assertEquals(404, exception.getCode());
    }

}
