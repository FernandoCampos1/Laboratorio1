# Laboratorio 3
Laboratorio-Iron



Escenario 1: Pruebas de autenticación de usuarios

Caso de prueba original (pseudocódigo):

**TEST UserAuthentication
ASSERT_TRUE(authenticate("validUser", "validPass"), "Should succeed with correct credentials")
ASSERT_FALSE(authenticate("validUser", "wrongPass"), "Should fail with wrong credentials")
END TEST**

Se cambia el pseudocódigo a código java.
1. La prueba se ve mas clara y se centra en una unica funcionalidad.Se separa en dos métodos para que sea más claro el caso de prueba.
2. Se agregaran comentarios para la documentacion del codigo.
3. cada prueba verifica un comportamiento especifico.
4. se cambia el nombre para mejorar la comprension y la mantenibilidad del codigo.

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

**TEST DataProcessing
DATA data = fetchData()
TRY
processData(data)
ASSERT_TRUE(data.processedSuccessfully, "Data should be processed successfully")
CATCH error
ASSERT_EQUALS("Data processing error", error.message, "Should handle processing errors")
END TRY
END TEST**

Se cambia el pseudocódigo a código java.
1. La prueba se ve mas clara y se centra en una unica funcionalidad.Se separa en dos métodos para que sea más claro el caso de prueba.
2. Se agregaran comentarios para la documentacion del codigo.
3. cada prueba verifica un comportamiento especifico.
4. se cambia el nombre para mejorar la comprension y la mantenibilidad del codigo.
5. se cambia el nombre de las variables para que sea mas claro el proposito de cada una.

`
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataProcessingTest {

    @Test
    public void testDataProcessingSucceeds() {
        // This test verifies that the data is processed successfully
        Data data = fetchData();
        processData(data);
        assertTrue(data.processedSuccessfully, "Data should be processed successfully");
    }

    @Test
    public void testDataProcessingHandlesErrors() {
        // This test verifies that the data processing handles errors
        Data data = fetchData();
        try {
            processData(data);
        } catch (Exception error) {
            assertEquals("Data processing error", error.getMessage(), "Should handle processing errors");
        }
    }
}`




Escenario 3: capacidad de respuesta de la interfaz de usuario

Caso de prueba original (pseudocódigo):

**TEST UIResponsiveness
UI_COMPONENT uiComponent = setupUIComponent(1024)
ASSERT_TRUE(uiComponent.adjustsToScreenSize(1024), "UI should adjust to width of 1024 pixels")
END TEST**


Se cambia el pseudocódigo a código java.

1. Se cambia el nombre de la prueba para tener mas claridad en el proposito de la prueba.
2. Se agregaran comentarios para la documentacion del codigo.

`
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UIResponsivenessTest {

    @Test
    public void testUIComponentAdjustsToScreenWidth() {
        // This test verifies that the UI component adjusts to a screen width of 1024 pixels
        UIComponent uiComponent = setupUIComponent(1024);
        assertTrue(uiComponent.adjustsToScreenSize(1024), "UI should adjust to width of 1024 pixels");
    }
}`



