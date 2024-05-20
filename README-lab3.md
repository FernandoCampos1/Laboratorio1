# Laboratorio 3
Laboratorio-Iron

Escenario 1: Pruebas de autenticación de usuarios

Caso de prueba original (pseudocódigo):

TEST UserAuthentication
ASSERT_TRUE(authenticate("validUser", "validPass"), "Should succeed with correct credentials")
ASSERT_FALSE(authenticate("validUser", "wrongPass"), "Should fail with wrong credentials")
END TEST

Se cambia el pseudocódigo a código java.
Se separa en dos métodos para que sea más claro el caso de prueba.


`
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserAuthenticationTest {

    @Test
    public void testUserAuthenticationSucceedsWithValidCredentials() {
        // This test verifies that the authentication succeeds with valid credentials
        assertTrue(authenticate("validUser", "validPass"), "Should succeed with correct credentials");
    }

    @Test
    public void testUserAuthenticationFailsWithWrongCredentials() {
        // This test verifies that the authentication fails with wrong credentials
        assertFalse(authenticate("validUser", "wrongPass"), "Should fail with wrong credentials");
    }
}
`

Escenario 2: Funciones de procesamiento de datos

Caso de prueba original (pseudocódigo):
TEST DataProcessing
DATA data = fetchData()
TRY
processData(data)
ASSERT_TRUE(data.processedSuccessfully, "Data should be processed successfully")
CATCH error
ASSERT_EQUALS("Data processing error", error.message, "Should handle processing errors")
END TRY
END TEST


Escenario 3: capacidad de respuesta de la interfaz de usuario

Caso de prueba original (pseudocódigo):

TEST UIResponsiveness
UI_COMPONENT uiComponent = setupUIComponent(1024)
ASSERT_TRUE(uiComponent.adjustsToScreenSize(1024), "UI should adjust to width of 1024 pixels")
END TEST





